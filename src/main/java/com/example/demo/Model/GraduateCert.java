package com.example.demo.Model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class GraduateCert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String birthday;
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "graduation_date")
    private String graduationDate;
}
