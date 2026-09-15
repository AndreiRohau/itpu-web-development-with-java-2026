package com.arohau.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "employees")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy=InheritanceType.JOINED)
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="employee_id")
    private Long id;

    @NonNull
    @Column
    private String fName;

    @NonNull
    @Column
    private String lName;

    @NonNull
    @Column
    private Integer yearsExperience;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "employee_company",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<Company> companies = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "employee_profile_id")
//    @JoinColumns({
//            @JoinColumn(name="personalIdNumber"),
//            @JoinColumn(name="phoneNumber")
//    })
    private EmployeeProfile profile;

    public Employee(Long id, String fName, String lName, Integer yearsExperience, List<Company> companies) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.yearsExperience = yearsExperience;
        this.companies = companies;
    }
}
