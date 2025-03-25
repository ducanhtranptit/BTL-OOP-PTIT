package Impl;

import Object.Teacher;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface TeacherInterface {
    Teacher readTeacher();

    List<Teacher> readManyTeachers();

    boolean check(Teacher teacher);

    void printTeacher(Teacher teacher);

    void writeTeacher(Teacher teacher, FileWriter file) throws IOException;

    void writeManyTeachers(List<Teacher> teachers);

    Teacher findTeacher(String id);

    void printAllDataTeachers();

    void addTeacher();

    void deleteTeacher(String id);

    void editTeacher(String id);

    void showTeacherMenu();

    void teacherMain();
}
