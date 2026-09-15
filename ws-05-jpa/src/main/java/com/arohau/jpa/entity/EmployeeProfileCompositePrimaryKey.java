package com.arohau.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EmployeeProfileCompositePrimaryKey {
    private String personalIdNumber;
    private String phoneNumber;
}
