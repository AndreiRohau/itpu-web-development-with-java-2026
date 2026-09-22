package com.arohau.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;
import com.arohau.hibernate.entity.User;

public class MainSimple {
    public static void main(String[] args) {
        // configure from properties
        Configuration configure = new Configuration().configure();

        // instantiate SessionFactory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // get link to stats
        Statistics stats = sessionFactory.getStatistics();

        // save a users
        Integer userId = saveOneUser(sessionFactory);

        // clean up all cache
        sessionFactory.getCache().evictAllRegions();

        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
        stats.setStatisticsEnabled(true);
        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
        printStats(stats, 0); // 0-0-0

        // flow U-1: check in session, check in 2LVL, mark missing in 2LVL, select from DB, PUT to 2LVL, PUT to SESSION
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            User user1FromDatabase = session.get(User.class, userId);
            printData(user1FromDatabase, stats, 1); // 0-1-1

            // get U-1: from session (1st lvl cache), 2lvl cache is not touched
            User user1FromSession = session.get(User.class, userId);
            printData(user1FromSession, stats, 2); // 0-1-1
            session.getTransaction().commit();

            // clearing 1LVL cache
            session.clear();
        }

        // flow U-1: check Session, check 2LVL, mark Hit entity from 2Lvl cache
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            User hitFrom2LvlUser1 = session.get(User.class, userId);
            session.getTransaction().commit();
            printData(hitFrom2LvlUser1, stats, 3); // 1-1-1
        }

        // clean up all caches
        sessionFactory.getCache().evictAllRegions();

        // flow U-1: check in session, check in 2LVL, mark missing in 2LVL, select from DB, PUT to 2LVL, PUT to SESSION
        try (Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            User fromDbUser1 = session.get(User.class, userId);
            session.getTransaction().commit();
            printData(fromDbUser1, stats, 6); // 1-2-2
        }

        System.out.println("EXIT");
        sessionFactory.close();
    }

    private static Integer saveOneUser(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User("U-1");
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        return user.getId();
    }

    private static void printStats(Statistics stats, int i) {
        System.out.println("***** " + i + " *****");
        System.out.println("Fetch Count=" + stats.getEntityFetchCount());
        System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
        System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
        System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());
        System.out.println("");
    }

    private static void printData(User user, Statistics stats, int count) {
        System.out.println(count + ":: Id=" + user.getId());
        printStats(stats, count);
    }
}
