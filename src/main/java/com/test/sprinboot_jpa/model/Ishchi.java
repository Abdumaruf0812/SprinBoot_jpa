package com.test.sprinboot_jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Ishchi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String ism;

    @Column(nullable = false)
    private String familyasi;

    @Column(nullable = false , unique = true)
    private String phonenumber;

    @Column(nullable = false)
    private Double maoshi;
}
