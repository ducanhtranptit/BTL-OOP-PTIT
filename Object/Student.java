package Object;

import java.util.Scanner;

import Utils.Utils;
import Impl.StudentInterface;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Student implements StudentInterface {
    private String name;
    private String email;
    private String stu_id;
    private String mark;
    private String class_id;

    public Student() {
    }

    public Student(String name, String email, String stu_id, String mark, String class_id) {
        this.name = name;
        this.email = email;
        this.stu_id = stu_id;
        this.mark = mark;
        this.class_id = class_id;
    }

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getStu_id() {
        return stu_id;
    }

    @Override
    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    @Override
    public String getMark() {
        return mark;
    }

    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String getClass_id() {
        return class_id;
    }

    @Override
    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    @Override
    public Student readStudent() {
        System.out.println("---------------------------------");
        System.out.println("Nhap thong tin sinh vien:");
        Student sv = new Student();
        System.out.print("Nhap ten: ");
        sv.name = scanner.nextLine();
        System.out.print("Nhap email: ");
        sv.email = scanner.nextLine();
        System.out.print("Nhap ma sinh vien: ");
        sv.stu_id = scanner.nextLine();
        System.out.print("Nhap diem: ");
        sv.mark = scanner.nextLine();
        System.out.print("Nhap ma lop: ");
        sv.class_id = scanner.nextLine();
        System.out.println("---------------------------------");
        return sv;
    }

    @Override
    public List<Student> readManyStudents() {
        List<Student> students = new ArrayList<>();
        System.out.print("Nhap so luong sinh vien co trong danh sach: ");
        int total = scanner.nextInt();
        scanner.nextLine();
        Student stu = new Student();
        for (int i = 0; i < total; i++) {
            do {
                stu = readStudent();
                if (!check(stu)) {
                    System.out.println("Thong tin khong duoc de trong. Vui long nhap lai.");
                    scanner.nextLine();
                }
            } while (!check(stu));
            students.add(stu);
        }
        return students;
    }

    @Override
    public boolean check(Student sv) {
        if (!sv.name.isEmpty() && !sv.email.isEmpty()
                && !sv.stu_id.isEmpty() && !sv.mark.isEmpty() && !sv.class_id.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printStudent(Student sv) {
        System.out.println("---");
        System.out.println("Ten sinh vien : " + sv.name);
        System.out.println("Email sinh vien : " + sv.email);
        System.out.println("Ma sinh vien : " + sv.stu_id);
        System.out.println("Diem sinh vien : " + sv.mark);
        System.out.println("Ma lop : " + sv.class_id); // In ra mã lớp
        System.out.println("---");
    }

    @Override
    public void writeStudent(Student sv, FileWriter file) throws IOException {
        file.write(sv.name + "," + sv.email + "," + sv.stu_id + "," + sv.mark + "," + sv.class_id + "\n");
    }

    @Override
    public void writeManyStudents(List<Student> students) {
        try {
            FileWriter file = new FileWriter("data/students.csv");
            for (Student stu : students) {
                writeStudent(stu, file);
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Khong the mo file students.csv.");
        }
    }

    @Override
    public Student findStudent(String id) {
        try {
            @SuppressWarnings("resource")
            BufferedReader file = new BufferedReader(new FileReader("data/students.csv"));
            String row;
            while ((row = file.readLine()) != null) {
                if (row.contains(id)) {
                    String[] rowList = row.split(",");
                    return new Student(rowList[0], rowList[1], rowList[2], rowList[3], rowList[4]);
                }
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Khong the mo file students.csv.");
        }
        return null;
    }

    @Override
    public void printAllDataStudents() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("data/students.csv"));
            String row;
            while ((row = file.readLine()) != null) {
                String[] rowList = row.split(",");
                printStudent(new Student(rowList[0], rowList[1], rowList[2], rowList[3], rowList[4]));
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Khong the mo file students.csv.");
        }
    }

    @Override
    public void addStudent() {
        System.out.println("Them sinh vien");
        Student newStudent = new Student();
        try {
            FileWriter file = new FileWriter("data/students.csv", true);
            do {
                newStudent = readStudent();
                if (!check(newStudent)) {
                    System.out.println("Thong tin khong duoc de trong. Vui long nhap lai.");
                    scanner.nextLine();
                }
            } while (!check(newStudent));
            writeStudent(newStudent, file);
            file.close();
            System.out.println("\nXong!\n");
        } catch (IOException e) {
            System.out.println("Khong the mo file students.csv.");
        }
    }

    @Override
    public void deleteStudent(String id) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("data/students.csv"));
            List<String> fileContent = new ArrayList<>();
            String row;
            while ((row = fileReader.readLine()) != null) {
                if (!row.contains(id)) {
                    fileContent.add(row);
                }
            }
            fileReader.close();

            FileWriter fileWriter = new FileWriter("data/students.csv");
            for (String line : fileContent) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
            System.out.println("\nXong!\n");
        } catch (IOException e) {
            System.out.println("Khong the mo file students.csv.");
        }
    }

    @Override
    public void editStudent(String id) {
        Student existingStudent = this.findStudent(id);
        if (existingStudent != null) {
            System.out.println("Thong tin sinh vien hien tai:");
            printStudent(existingStudent);
            System.out.println("Nhap thong tin moi cho sinh vien (de trong neu muon giu nguyen):");
            Student editedStudent = readStudent();

            if (!editedStudent.name.isEmpty()) {
                existingStudent.name = editedStudent.name;
            }
            if (!editedStudent.email.isEmpty()) {
                existingStudent.email = editedStudent.email;
            }
            if (!editedStudent.mark.isEmpty()) {
                existingStudent.mark = editedStudent.mark;
            }
            if (!editedStudent.stu_id.isEmpty()) {
                existingStudent.stu_id = editedStudent.stu_id;
            }
            if (!editedStudent.class_id.isEmpty()) {
                existingStudent.class_id = editedStudent.class_id;
            }
            try {
                BufferedReader fileReader = new BufferedReader(new FileReader("data/students.csv"));
                List<String> fileContent = new ArrayList<>();
                String row;
                while ((row = fileReader.readLine()) != null) {
                    if (row.contains(id)) {
                        row = existingStudent.name + "," + existingStudent.email + "," + existingStudent.stu_id + ","
                                + existingStudent.mark + "," + existingStudent.class_id;
                    }
                    fileContent.add(row);
                }
                fileReader.close();

                FileWriter fileWriter = new FileWriter("data/students.csv");
                for (String line : fileContent) {
                    fileWriter.write(line + "\n");
                }
                fileWriter.close();
                System.out.println("\nXong!\n");
            } catch (IOException e) {
                System.out.println("Khong the mo file students.csv.");
            }
        } else {
            System.out.println("Khong tim thay sinh vien voi ma so " + id);
        }
    }

    @Override
    public void showStudentMenu() {
        System.out.println("*******************************************");
        System.out.println("* Ban hay chon 1 lua chon:                *");
        System.out.println("* 1. Tao mot danh sach sinh vien moi      *");
        System.out.println("* 2. Hien thi toan bo danh sach sinh vien *");
        System.out.println("* 3. Tim kiem sinh vien theo MSV          *");
        System.out.println("* 4. Them sinh vien                       *");
        System.out.println("* 5. Xoa sinh vien theo MSV               *");
        System.out.println("* 6. Sua thong tin sinh vien theo MSV     *");
        System.out.println("* 7. Quay ve menu chinh                   *");
        System.out.println("*******************************************");
    }

    @Override
    public void studentMain() {
        while (true) {
            Utils.clearScreen();
            this.showStudentMenu();
            System.out.print("Ban chon (1-7): ");
            String customerChoice = scanner.nextLine();

            switch (customerChoice) {
                case "1":
                    System.out.println("Ban co muon xoa het danh sach sinh vien hien tai khong? (y/n)");
                    String confirm = scanner.nextLine().trim().toLowerCase();

                    if (confirm.equals("y")) {
                        String correctPassword = "";
                        try {
                            List<String> lines = Files.readAllLines(Paths.get("const/const.json"));
                            for (String line : lines) {
                                line = line.trim();
                                if (line.startsWith("\"createListPassword\"")) {
                                    int colonIndex = line.indexOf(":");
                                    if (colonIndex != -1) {
                                        correctPassword = line.substring(colonIndex + 1)
                                                .replaceAll("[\",]", "")
                                                .trim();
                                        break;
                                    }
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Loi khi doc file const.json: " + e.getMessage());
                            break;
                        }

                        System.out.print("Nhap mat khau de xac nhan: ");
                        String inputPassword = scanner.nextLine();

                        if (inputPassword.equals(correctPassword)) {
                            List<Student> students = readManyStudents();
                            writeManyStudents(students);
                            System.out.println("Danh sach sinh vien da duoc cap nhat!");
                        } else {
                            System.out.println("Mat khau sai. Huy bo thao tac.");
                        }
                    } else {
                        System.out.println("Huy bo thao tac. Khong co gi thay doi.");
                    }

                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;

                case "2":
                    this.printAllDataStudents();
                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "3":
                    System.out.print("Ban hay nhap ma sinh vien muon tim kiem: ");
                    String id = scanner.nextLine();
                    Student foundStudent = this.findStudent(id);
                    if (foundStudent != null) {
                        this.printStudent(foundStudent);
                    } else {
                        System.out.println("Khong tim thay sinh vien voi ma so " + id);
                    }
                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "4":
                    this.addStudent();
                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "5":
                    System.out.print("Ban hay nhap ma sinh vien ban muon xoa: ");
                    String line = scanner.nextLine();
                    this.deleteStudent(line);
                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "6":
                    System.out.print("Ban hay nhap ma sinh vien ban muon sua thong tin: ");
                    String editId = scanner.nextLine();
                    this.editStudent(editId);
                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Ban da nhap sai! Nhan enter de nhap lai!");
                    scanner.nextLine();
                    Utils.clearScreen();
            }

            if (customerChoice.equals("7")) {
                Utils.clearScreen();
                break;
            }
        }
    }

}
