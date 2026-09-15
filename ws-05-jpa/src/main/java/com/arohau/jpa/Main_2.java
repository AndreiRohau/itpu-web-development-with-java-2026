package com.arohau.jpa;

import com.arohau.jpa.entity.ActiveEmployee;
import com.arohau.jpa.entity.Company;
import com.arohau.jpa.entity.Employee;
import com.arohau.jpa.entity.Salary;
import com.arohau.jpa.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// Managing entities
public class Main_2 {
    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");

        check("[]");
        createEmployees();
        check("Employee(id=1, fName=Mary, lName=Doe ~~&&~~&&~~&&~~ Employee(id=2, fName=James, lName=Doe");
        updateFirstEmployee();
        check("Employee(id=1, fName=Mary, lName=Johnson ~~&&~~&&~~&&~~ Employee(id=2, fName=James, lName=Doe");
        deleteSecondEmployee();
        check("Employee(id=1, fName=Mary, lName=Johnson ~~&&~~&&~~&&~~ null");

        entityManagerFactory.close();
    }

    private static void createEmployees() {
        System.out.println("createEmployees");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

        //create a new Employee
        ActiveEmployee employee1 = new ActiveEmployee();
        employee1.setFName("Mary");
        employee1.setLName("Doe");
        employee1.setYearsExperience(20);

        //set salary
        employee1.setSalaries(new ArrayList<>(Arrays.asList(new Salary(54000.00, true))));

        //set company
        employee1.setCompanies(new ArrayList<>(Arrays.asList(new Company("MyCompany", "NA"))));

        Employee employee2 = new Employee();
        employee2.setFName("James");
        employee2.setLName("Doe");
        employee2.setYearsExperience(5);

        //save Employees
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        entityManager.close();
    }

    private static void updateFirstEmployee() {
        System.out.println("updateFirstEmployee");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

        //update Employee
        Optional<Employee> retrievedEmployee = employeeRepository.getEmployeeById(1L);
        retrievedEmployee.get().setLName("Johnson");
        employeeRepository.save(retrievedEmployee.get());

        entityManager.close();
    }

    private static void deleteSecondEmployee() {
        System.out.println("deleteSecondEmployee");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

        //delete Employee
        Optional<Employee> retrievedEmployee2 = employeeRepository.getEmployeeById(2L);
        employeeRepository.deleteEmployee(retrievedEmployee2.get());

        entityManager.close();
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