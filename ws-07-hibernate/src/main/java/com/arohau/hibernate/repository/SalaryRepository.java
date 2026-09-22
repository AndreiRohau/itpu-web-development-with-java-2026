package com.arohau.hibernate.repository;

import com.arohau.hibernate.entity.Salary;

import java.util.Optional;

public interface SalaryRepository {
    Optional<Salary> save(Salary salary);
    Optional<Salary> getSalaryById(Long id);
    void deleteSalary(Salary salary);
}
