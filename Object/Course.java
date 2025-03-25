package Object;

import Impl.CourseInterface;
import Utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Course implements CourseInterface {
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Subject> subjects;

    private static final Scanner scanner = new Scanner(System.in);

    public Course() {
        teachers = loadTeachersFromFile("data/teachers.csv");
        students = loadStudentsFromFile("data/students.csv");
        subjects = loadSubjectsFromFile("data/subjects.csv");
    }

    private List<Teacher> loadTeachersFromFile(String filename) {
        List<Teacher> teacherList = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String row;
            while ((row = fileReader.readLine()) != null) {
                String[] rowData = row.split(",");
                if (rowData.length >= 4) {
                    teacherList.add(new Teacher(rowData[0], rowData[1], rowData[2], rowData[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file " + filename);
        }
        return teacherList;
    }

    private List<Student> loadStudentsFromFile(String filename) {
        List<Student> studentList = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String row;
            while ((row = fileReader.readLine()) != null) {
                String[] rowData = row.split(",");
                if (rowData.length >= 5) {
                    studentList.add(new Student(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file " + filename);
        }
        return studentList;
    }

    private List<Subject> loadSubjectsFromFile(String filename) {
        List<Subject> subjectList = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String row;
            while ((row = fileReader.readLine()) != null) {
                String[] rowData = row.split(",");
                if (rowData.length >= 5) {
                    subjectList.add(new Subject(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file " + filename);
        }
        return subjectList;
    }

    @Override
    public void displayClassInformation() {
        Set<String> classIds = new HashSet<>();

        for (Teacher teacher : teachers) {
            classIds.add(teacher.getClass_id());
        }

        for (String classId : classIds) {
            System.out.println("=======================================");
            System.out.println("Class ID: " + classId);

            System.out.println("GIAO VIEN:");
            for (Teacher teacher : teachers) {
                if (teacher.getClass_id().equals(classId)) {
                    System.out.println("  - Ten: " + teacher.getName());
                    System.out.println("  - Email: " + teacher.getEmail());
                }
            }

            System.out.println("HOC SINH:");
            for (Student student : students) {
                if (student.getClass_id().equals(classId)) {
                    System.out.println("  - Ten: " + student.getName());
                    System.out.println("  - Email: " + student.getEmail());
                    System.out.println("  - Student ID: " + student.getStu_id());
                    System.out.println("  - Diem: " + student.getMark());
                }
            }

            System.out.println("MON HOC:");
            for (Subject subject : subjects) {
                if (subject.getClass_id().equals(classId)) {
                    System.out.println("  - Subject ID: " + subject.getSubject_id());
                    System.out.println("  - Mon hoc: " + subject.getName());
                    System.out.println("  - Mo ta: " + subject.getDescription());
                    System.out.println("  - Link: " + subject.getLink());
                }
            }
        }
    }

    @Override
    public void classManagerMain() {
        while (true) {
            displayClassInformation();
            System.out.println("Nhan Enter de quay ve menu chinh.");
            scanner.nextLine();
            Utils.clearScreen();
            break;
        }
    }
}
