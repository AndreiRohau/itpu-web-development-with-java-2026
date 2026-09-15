package com.arohau.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_profiles")
public class EmployeeProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="employee_profile_id")
    private Long id;

// composite key
//    @EmbeddedId
//    private EmployeeProfileCompositePrimaryKey id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String title;

    @ToString.Exclude
    @OneToOne(mappedBy="profile")
    private Employee employee;

    public EmployeeProfile(String userName, String password, String email, String title, Employee employee) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.title = title;
        this.employee = employee;
    }
}
