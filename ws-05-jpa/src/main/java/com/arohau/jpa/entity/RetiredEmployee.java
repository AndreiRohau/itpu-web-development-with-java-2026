package com.arohau.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "retired_employees")
public class RetiredEmployee extends Employee {

    private Boolean pension;
    private Integer yearsOfService;

    public RetiredEmployee(Long id, String fName, String lName, Integer yearsExperience, List<Company> companies, Boolean pension, Integer yearsOfService) {
        super(id, fName, lName, yearsExperience, companies);
        this.pension = pension;
        this.yearsOfService = yearsOfService;
    }
}
