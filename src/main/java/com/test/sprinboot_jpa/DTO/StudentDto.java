package com.test.sprinboot_jpa.DTO;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private String ismi;
    private String familyasi;
    private String telRaqami;
    private String viloyati;
    private String tumani;
    private String koshasi;
    private Integer guruhId;
    private List<Integer> fanId;
}
