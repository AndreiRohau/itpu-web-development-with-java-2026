package com.arohau.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "active_employees")
public class ActiveEmployee extends Employee {

    @NonNull
    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Salary> salaries = new ArrayList<>();

    @NonNull
    @Transient
    private Double totalCompensation;

    public void setSalaries(@NonNull List<Salary> salaries) {
        this.salaries = salaries;
        totalCompensation = salaries.stream().mapToDouble(Salary::getCurrentSalary).sum();
    }
}
