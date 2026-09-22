package com.arohau.jpa;

import com.arohau.jpa.entity.*;
import com.arohau.jpa.repository.EmployeeRepositoryImpl;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

// Understanding transactions
public class Main_4 {

    private static EntityManagerFactory entityManagerFactory;
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        EntityManagerFactory entityManagerFactory = sessionFactory;
        Session session = sessionFactory.openSession();
        EntityManager entityManager = session;

        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(session, session);

        ActiveEmployee employee1 = new ActiveEmployee();
        employee1.setFName("Rick");
        employee1.setLName("King");
        employee1.setYearsExperience(20);

        ActiveEmployee employee2 = new ActiveEmployee();
        employee2.setFName("Mary");
        employee2.setLName("Johnson");
        employee2.setYearsExperience(5);

        //set employment history
        employee1.setCompanies(generateCompanies());
        employee2.setCompanies(generateCompanies());

        //create an EmployeeProfile and associate it to an Employee
        employee1.setProfile(new EmployeeProfile("rKing", "password!", "email@email.com", employee1, "Software Engineer"));
        employee2.setProfile(new EmployeeProfile("mJohns", "password234", "johndoe@email.com", employee2, "Project Manager"));

        //set salaries
        employee1.setSalaries(generateSalaries());
        employee2.setSalaries(generateSalaries());

        //save Employee
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        session.close();
        sessionFactory.close();
    }

    private static List<Company> generateCompanies() {
        Company company1 = new Company("Facebook", "USA");
        Company company2 = new Company("Oracle", "USA");

        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);

        return companies;
    }

    private static List<Salary> generateSalaries() {
        //create the Salaries and associate to Employee
        Salary currentSalary = new Salary(34000.00, true);
        Salary historicalSalary1 = new Salary(10000.00, false);
        Salary historicalSalary2 = new Salary(5000.00, false);

        List<Salary> salaries = new ArrayList<>();
        salaries.add(currentSalary);
        salaries.add(historicalSalary1);
        salaries.add(historicalSalary2);

        return salaries;
    }

}