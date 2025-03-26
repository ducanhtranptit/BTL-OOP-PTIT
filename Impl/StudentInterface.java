package Impl;

import Object.Student;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface StudentInterface {
    String getName();

    void setName(String name);

    Student readStudent();

    String getEmail();

    void setEmail(String email);

    String getStu_id();

    void setStu_id(String stu_id);

    String getMark();

    void setMark(String mark);

    String getClass_id();

    void setClass_id(String class_id);

    List<Student> readManyStudents();

    boolean check(Student sv);

    void printStudent(Student sv);

    void writeStudent(Student sv, FileWriter file) throws IOException;

    void writeManyStudents(List<Student> students);

    Student findStudent(String id);

    void printAllDataStudents();

    void addStudent();

    void deleteStudent(String id);

    void editStudent(String id);

    void showStudentMenu();

    void studentMain();
}
