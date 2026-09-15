package com.arohau.jpa.repository;

import com.arohau.jpa.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

//isolate the persistence logic for each entity using the Repository pattern
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private EntityManager entityManager;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Employee> save(Employee employee) {
        try {
            entityManager.getTransaction().begin();
            if (employee.getId() == null) {
                if (employee.getProfile() != null) {
                    entityManager.persist(employee.getProfile());
                }
                entityManager.persist(employee);
            } else {
                employee = entityManager.merge(employee);
            }
            entityManager.getTransaction().commit();

            return Optional.of(employee);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee != null ? Optional.of(employee) : Optional.empty();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        entityManager.getTransaction().begin(); //uncomment if not using @Transactional

        if (entityManager.contains(employee)) {
            entityManager.remove(employee);
        } else {
            throw new RuntimeException("There is no one to delete");
        }

        entityManager.getTransaction().commit(); //uncomment if not using @Transactional
    }

}
