package Impl;

import Object.Teacher;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface TeacherInterface {
    String getName();

    void setName(String name);

    String getEmail();

    void setEmail(String email);

    String getTeacher_id();

    void setTeacher_id(String teacher_id);

    String getClass_id();

    void setClass_id(String class_id);

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
