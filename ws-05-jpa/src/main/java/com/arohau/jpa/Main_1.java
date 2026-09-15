package com.arohau.jpa;

import com.arohau.jpa.entity.Company;
import com.arohau.jpa.entity.Employee;
import jakarta.persistence.*;

import java.util.List;

// Understanding JPA
public class Main_1 {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");

        saveCompany(); // create
        getCompany(); // read
        updateCompany(); // update
        getCompany();
        deleteCompany(); // delete
        getCompany();

        entityManagerFactory.close();
    }

    // create
    private static void saveCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Company company = new Company("adidas", "NA"); // transient state
        entityManager.persist(company);
        transaction.commit();

        System.out.println("Result save: " + company);
        entityManager.close();
    }

    // read
    private static void getCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Company> companies = (List<Company>) entityManager.createNativeQuery("SELECT * FROM companies;", Company.class).getResultList();

        Company company = entityManager.find(Company.class, 1);
        if (company != null) {
            System.out.println("employees list is not loaded from database at this point");
            List<Employee> employees = company.getEmployees(); // hibernate sees that data is still not loaded due to fetch-type=lazy, and runs a query to a database to get all employees for this company
        } else {
            System.out.println("No company found with id 1");
        }
        transaction.commit();

//        System.out.println("Result get one by id: " + entityManager.find(Company.class, 1));
        System.out.println("Result: get all" + companies);
        entityManager.close();
    }

    // update
    private static void updateCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Company company = entityManager.find(Company.class, 1);
        company.setName("PUMA");
        transaction.commit();

        System.out.println("Result Update: " + company);
        entityManager.close();
    }

    // delete
    private static void deleteCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Company company = entityManager.find(Company.class, 1);
        entityManager.remove(company);
        transaction.commit();

        System.out.println("Result: delete" + company);
        entityManager.close();
    }
}
