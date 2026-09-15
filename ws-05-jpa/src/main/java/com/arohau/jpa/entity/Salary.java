package com.arohau.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "salaries")
public class Salary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="salary_id")
    private Long id;

    @Column
    private Company company;

    @Column
    private Integer level;

    @Column
    private Integer bonusPercentage;

    @Column
    private Double startingSalary;

    @NonNull
    @Column
    private Double currentSalary;

    @NonNull
    @Column
    private Boolean activeFlag;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
}
