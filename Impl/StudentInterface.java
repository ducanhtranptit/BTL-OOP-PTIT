package Impl;

import Object.Student;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface StudentInterface {
    Student readStudent();

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
