package Object;

import Impl.TeacherInterface;
import Utils.Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Teacher implements TeacherInterface {
    private String name;
    private String email;
    private String teacher_id;
    private String class_id;

    private static final Scanner scanner = new Scanner(System.in);

    public Teacher() {
    }

    public Teacher(String name, String email, String teacher_id, String class_id) {
        this.name = name;
        this.email = email;
        this.teacher_id = teacher_id;
        this.class_id = class_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    @Override
    public Teacher readTeacher() {
        Teacher teacher = new Teacher();
        System.out.print("Nhap ten: ");
        teacher.name = scanner.nextLine();
        System.out.print("Nhap email: ");
        teacher.email = scanner.nextLine();
        System.out.print("Nhap ma giao vien: ");
        teacher.teacher_id = scanner.nextLine();
        System.out.print("Nhap ma lop: ");
        teacher.class_id = scanner.nextLine();
        return teacher;
    }

    @Override
    public List<Teacher> readManyTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        System.out.print("Nhap so luong giao vien trong danh sach: ");
        int total = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < total; i++) {
            Teacher teacher;
            do {
                System.out.println("\nGiao vien " + (i + 1));
                teacher = readTeacher();
                if (!check(teacher)) {
                    System.out.println("Thong tin khong duoc de trong. Vui long nhap lai.");
                    scanner.nextLine();
                }
            } while (!check(teacher));
            teachers.add(teacher);
        }
        return teachers;
    }

    @Override
    public boolean check(Teacher teacher) {
        return !teacher.name.isEmpty() &&
                !teacher.email.isEmpty() &&
                !teacher.teacher_id.isEmpty() &&
                !teacher.class_id.isEmpty();
    }

    @Override
    public void printTeacher(Teacher teacher) {
        System.out.println("---");
        System.out.println("Ten giao vien : " + teacher.name);
        System.out.println("Email giao vien : " + teacher.email);
        System.out.println("Ma giao vien : " + teacher.teacher_id);
        System.out.println("Ma lop : " + teacher.class_id);
        System.out.println("---");
    }

    @Override
    public void writeTeacher(Teacher teacher, FileWriter file) throws IOException {
        file.write(teacher.name + "," + teacher.email + "," + teacher.teacher_id + "," + teacher.class_id + "\n");
    }

    @Override
    public void writeManyTeachers(List<Teacher> teachers) {
        try (FileWriter file = new FileWriter("data/teachers.csv")) {
            for (Teacher teacher : teachers) {
                writeTeacher(teacher, file);
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file teachers.csv.");
        }
    }

    @Override
    public Teacher findTeacher(String id) {
        try (BufferedReader file = new BufferedReader(new FileReader("data/teachers.csv"))) {
            String row;
            while ((row = file.readLine()) != null) {
                if (row.contains(id)) {
                    String[] rowList = row.split(",");
                    return new Teacher(rowList[0], rowList[1], rowList[2], rowList[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file teachers.csv.");
        }
        return null;
    }

    @Override
    public void printAllDataTeachers() {
        try (BufferedReader file = new BufferedReader(new FileReader("data/teachers.csv"))) {
            String row;
            while ((row = file.readLine()) != null) {
                String[] rowList = row.split(",");
                printTeacher(new Teacher(rowList[0], rowList[1], rowList[2], rowList[3]));
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file teachers.csv.");
        }
    }

    @Override
    public void addTeacher() {
        System.out.println("Them giao vien");
        Teacher newTeacher;
        try (FileWriter file = new FileWriter("data/teachers.csv", true)) {
            do {
                newTeacher = readTeacher();
                if (!check(newTeacher)) {
                    System.out.println("Thong tin khong duoc de trong. Vui long nhap lai.");
                    scanner.nextLine();
                }
            } while (!check(newTeacher));
            writeTeacher(newTeacher, file);
            System.out.println("\nXong!\n");
        } catch (IOException e) {
            System.out.println("Khong the mo file teachers.csv.");
        }
    }

    @Override
    public void deleteTeacher(String id) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("data/teachers.csv"));
            List<String> fileContent = new ArrayList<>();
            String row;
            boolean found = false;
            while ((row = fileReader.readLine()) != null) {
                if (row.contains(id)) {
                    found = true;
                    continue;
                }
                fileContent.add(row);
            }
            fileReader.close();

            if (!found) {
                System.out.println("Không tìm thấy giáo viên với mã số " + id);
                return;
            }

            FileWriter fileWriter = new FileWriter("data/teachers.csv");
            for (String line : fileContent) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
            System.out.println("\nXong!\n");
        } catch (IOException e) {
            System.out.println("Không thể mở file teachers.csv.");
        }
    }

    @Override
    public void editTeacher(String id) {
        Teacher existingTeacher = findTeacher(id);
        if (existingTeacher != null) {
            System.out.println("Thông tin giáo viên hiện tại:");
            printTeacher(existingTeacher);
            System.out.println("Nhập thông tin mới cho giáo viên (để trống nếu muốn giữ nguyên):");
            Teacher editedTeacher = readTeacher();

            if (!editedTeacher.name.isEmpty()) {
                existingTeacher.name = editedTeacher.name;
            }
            if (!editedTeacher.email.isEmpty()) {
                existingTeacher.email = editedTeacher.email;
            }
            if (!editedTeacher.teacher_id.isEmpty()) {
                existingTeacher.teacher_id = editedTeacher.teacher_id;
            }
            if (!editedTeacher.class_id.isEmpty()) {
                existingTeacher.class_id = editedTeacher.class_id;
            }

            try {
                BufferedReader fileReader = new BufferedReader(new FileReader("data/teachers.csv"));
                List<String> fileContent = new ArrayList<>();
                String row;
                while ((row = fileReader.readLine()) != null) {
                    if (row.contains(id)) {
                        row = existingTeacher.name + "," + existingTeacher.email + "," +
                                existingTeacher.teacher_id + "," + existingTeacher.class_id;
                    }
                    fileContent.add(row);
                }
                fileReader.close();
                FileWriter fileWriter = new FileWriter("data/teachers.csv");
                for (String line : fileContent) {
                    fileWriter.write(line + "\n");
                }
                fileWriter.close();
                System.out.println("\nXong!\n");
            } catch (IOException e) {
                System.out.println("Không thể mở file teachers.csv.");
            }
        } else {
            System.out.println("Không tìm thấy giáo viên với mã số " + id);
        }
    }

    @Override
    public void showTeacherMenu() {
        System.out.println("*******************************************");
        System.out.println("* Ban hay chon 1 lua chon:                *");
        System.out.println("* 1. Tao mot danh sach giao vien moi      *");
        System.out.println("* 2. Hien thi toan bo danh sach giao vien *");
        System.out.println("* 3. Them giao vien                       *");
        System.out.println("* 4. Xoa giao vien                        *");
        System.out.println("* 5. Sua thong tin giao vien              *");
        System.out.println("* 6. Quay ve menu chinh                   *");
        System.out.println("*******************************************");
    }

    @Override
    public void teacherMain() {
        while (true) {
            Utils.clearScreen();
            showTeacherMenu();
            System.out.print("Ban chon (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Ban co muon xoa het danh sach giao vien hien tai khong? (y/n)");
                    String confirmTeacher = scanner.nextLine().trim().toLowerCase();

                    if (confirmTeacher.equals("y")) {
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
                            List<Teacher> teachers = readManyTeachers();
                            writeManyTeachers(teachers);
                            System.out.println("Danh sach giao vien da duoc cap nhat!");
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
                    printAllDataTeachers();
                    break;
                case "3":
                    addTeacher();
                    break;
                case "4":
                    System.out.print("Nhap ma giao vien muon xoa: ");
                    deleteTeacher(scanner.nextLine());
                    break;
                case "5":
                    System.out.print("Nhap ma giao vien muon sua: ");
                    editTeacher(scanner.nextLine());
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
            System.out.println("Nhan Enter de tiep tuc...");
            scanner.nextLine();
            Utils.clearScreen();
        }
    }
}
