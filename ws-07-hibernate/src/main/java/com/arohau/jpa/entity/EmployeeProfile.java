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

    public EmployeeProfile(Long id, String userName, String password, String email, Employee employee, String title) {
        this(userName, password, email, employee, title);
        this.id = id;
    }

    public EmployeeProfile(String userName, String password, String email, Employee employee, String title) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.employee = employee;
        this.title = title;
    }
}
