package com.test.sprinboot_jpa.Talaba_malumotlari;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Guruh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String guruhNomi;

    @ManyToOne
    Fakulteti fakulteti;

    //    @OneToMany
    //    List<Student> studentList;
}
