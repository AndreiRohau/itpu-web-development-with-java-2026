package com.arohau.jpa.repository;

import com.arohau.jpa.entity.Salary;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SalaryRepositoryImpl implements SalaryRepository {
    private EntityManager entityManager;

    public SalaryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Salary> save(Salary salary) {
        try {
            entityManager.getTransaction().begin();
            if (salary.getId() == null) {
                entityManager.persist(salary);
            } else {
                salary = entityManager.merge(salary);
            }
            entityManager.getTransaction().commit();

            return Optional.of(salary);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Salary> getSalaryById(Long id) {
        Salary salary = entityManager.find(Salary.class, id);
        return salary != null ? Optional.of(salary) : Optional.empty();
    }

    @Override
    public void deleteSalary(Salary salary) {
        entityManager.getTransaction().begin(); //uncomment if not using @Transactional

        if (entityManager.contains(salary)) {
            entityManager.remove(salary);
        } else {
            entityManager.merge(salary);
        }

        entityManager.getTransaction().commit(); //uncomment if not using @Transactional
    }
}
