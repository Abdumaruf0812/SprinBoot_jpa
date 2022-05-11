package com.test.sprinboot_jpa.Talaba_malumotlari;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class StudentManzil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String viloyati;

    @Column(nullable = false)
    private String tumani;

    @Column(nullable = false)
    private String koshasi;
}
