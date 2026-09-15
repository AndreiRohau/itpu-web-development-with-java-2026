package com.arohau.jpa;

import com.arohau.jpa.entity.*;
import com.arohau.jpa.repository.EmployeeRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

// Exploring Entity Relationships
public class Main_3 {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        run();
        check("[]");
        entityManagerFactory.close();
    }

    private static void run() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

        ActiveEmployee employee1 = new ActiveEmployee();
        employee1.setFName("Mary");
        employee1.setLName("Johnson");
        employee1.setYearsExperience(20);

        ActiveEmployee employee2 = new ActiveEmployee();
        employee2.setFName("John");
        employee2.setLName("Doe");
        employee2.setYearsExperience(5);

        //set employment history
        employee1.setCompanies(generateCompanies());
        employee2.setCompanies(generateCompanies());

        //create an EmployeeProfile and associate it to an Employee
//        employee1.setProfile(new EmployeeProfile(new EmployeeProfileCompositePrimaryKey("111", "111"),"userName", "password!", "email@email.com", "Software Engineer", employee1));
//        employee2.setProfile(new EmployeeProfile(new EmployeeProfileCompositePrimaryKey("222", "222"),"jDoe", "password234", "johndoe@email.com", "Project Manager", employee2));
        employee1.setProfile(new EmployeeProfile("userName", "password!", "email@email.com", "Software Engineer", employee1));
        employee2.setProfile(new EmployeeProfile("jDoe", "password234", "johndoe@email.com", "Project Manager", employee2));

        //set salaries
        employee1.setSalaries(generateSalaries());
        employee2.setSalaries(generateSalaries());

        //save Employee
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        entityManager.close();
    }

    private static List<Company> generateCompanies() {
        Company company1 = new Company("Google", "USA");
        Company company2 = new Company("Amazon", "USA");

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

    private static void check(String expected) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query getAllEmployeesQuery = entityManager.createNativeQuery("SELECT * FROM employees;", Employee.class);
        List<Employee> employees = (List<Employee>) getAllEmployeesQuery.getResultList();
        System.out.println("State: " + employees);
        System.out.println("Expected: " + expected);
        System.out.println();
        entityManager.close();
    }

}