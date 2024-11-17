package com.example.lab5_student.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String id;
    private String name;
    private String degree;
    private int age;
    private double GPA;

}
