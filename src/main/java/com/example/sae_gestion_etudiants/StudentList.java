package com.example.sae_gestion_etudiants;

import models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentList
{
    private static List<Student> studentsList = new ArrayList<>();

    public static List<Student> getStudentList()
    {
        return studentsList;
    }

    public static void setStudentList(List<Student> studentList)
    {
        studentsList = studentList;
    }
}
