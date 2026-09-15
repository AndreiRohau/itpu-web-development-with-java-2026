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
@Table(name = "companies")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="company_id")
    private Long id;

    @NonNull
    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String zipcode;

    @NonNull
    @Column
    private String country;

    @ToString.Exclude
    @ManyToMany(mappedBy = "companies", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();
}
