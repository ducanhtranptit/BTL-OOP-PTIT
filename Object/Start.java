package Object;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Utils.Utils;

public class Start {
    public static Scanner scanner = new Scanner(System.in);

    static void showLoginMenu() {
        System.out.println("*********************************************");
        System.out.println("*  CHUONG TRINH QUAN LY THONG TIN KHOA HOC  *");
        System.out.println("*                                           *");
        System.out.println("*                                           *");
        System.out.println("*                                           *");
        System.out.println("*                                           *");
        System.out.println("*  1. Dang nhap                2. Dang ky   *");
        System.out.println("*                                           *");
        System.out.println("*********************************************");
    }

    static void showStartedMenu() {
        System.out.println("*********************************************");
        System.out.println("*  CHUONG TRINH QUAN LY THONG TIN KHOA HOC  *");
        System.out.println("*                                           *");
        System.out.println("*                                           *");
        System.out.println("*                                           *");
        System.out.println("*                                           *");
        System.out.println("*  1. Bat dau                    2. Thoat   *");
        System.out.println("*                                           *");
        System.out.println("*********************************************");
    }

    public static void showChoiceMenu() {
        System.out.println("*******************************************");
        System.out.println("* Ban hay chon 1 lua chon:                *");
        System.out.println("* 1. Quan ly thong tin hoc sinh           *");
        System.out.println("* 2. Quan ly thong tin giao vien          *");
        System.out.println("* 3. Quan ly thong tin mon hoc            *");
        System.out.println("* 4. Quan ly thong tin tai lieu mon hoc   *");
        System.out.println("* 5. Hien thi thong tin khoa hoc          *");
        System.out.println("* 6. Hien thi thong tin tung mon hoc      *");
        System.out.println("* 7. Thoat ra man hinh chinh              *");
        System.out.println("*******************************************");
    }

    // Phương thức để đọc dữ liệu từ file CSV
    public static List<String[]> readCSV(String fileName) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        }
        return data;
    }

    // Phương thức để ghi dữ liệu vào file CSV
    public static void writeCSV(String fileName, List<String[]> data) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String[] row : data) {
                writer.append(String.join(",", row));
                writer.append("\n");
            }
        }
    }

    // Phương thức để kiểm tra đăng nhập
    public static boolean login(String username, String password) throws IOException {
        List<String[]> users = readCSV("data/user.csv");
        for (String[] user : users) {
            if (user[0].equals(username) && user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Phương thức để đăng ký
    public static void register(String username, String password) throws IOException {
        List<String[]> users = readCSV("data/user.csv");
        users.add(new String[] { username, password });
        writeCSV("data/user.csv", users);
    }

    public static void mainStart() {
        while (true) {
            Utils.clearScreen();
            showLoginMenu();
            System.out.print("Ban chon (1-2): ");
            String loginMenuChoice = scanner.nextLine();

            switch (loginMenuChoice) {
                case "2":
                    Utils.clearScreen();
                    boolean validInput = false;
                    String newUsername = "";
                    String newPassword = "";

                    while (!validInput) {
                        Utils.clearScreen();
                        showLoginMenu();
                        System.out.println("Nhap username: ");
                        newUsername = scanner.nextLine();
                        System.out.println("Nhap password: ");
                        newPassword = scanner.nextLine();

                        if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
                            validInput = true;
                        } else {
                            System.out.println("Username hoac password khong duoc de trong. Vui long nhap lai.");
                            scanner.nextLine();
                            Utils.clearScreen();
                        }
                    }

                    try {
                        register(newUsername, newPassword);
                        System.out.println("Dang ky thanh cong!");
                        scanner.nextLine();
                        Utils.clearScreen();
                    } catch (IOException e) {
                        System.out.println("Loi khi ghi file nguoi dung!");
                        scanner.nextLine();
                        Utils.clearScreen();
                    }
                    break;
                case "1":
                    Utils.clearScreen();
                    showLoginMenu();
                    System.out.println("Nhap username: ");
                    String username = scanner.nextLine();
                    System.out.println("Nhap password: ");
                    String password = scanner.nextLine();
                    try {
                        if (login(username, password)) {
                            while (true) {
                                Utils.clearScreen();
                                showStartedMenu();
                                System.out.print("Ban chon (1-2): ");
                                String startMenuChoice = scanner.nextLine();
                                switch (startMenuChoice) {
                                    case "2":
                                        Utils.clearScreen();
                                        return;
                                    case "1":
                                        while (true) {
                                            Utils.clearScreen();
                                            showChoiceMenu();
                                            System.out.print("Ban chon (1-7): ");
                                            String choiceMenuChoice = scanner.nextLine();
                                            switch (choiceMenuChoice) {
                                                case "1":
                                                    Student student = new Student();
                                                    student.studentMain();
                                                    break;
                                                case "2":
                                                    Teacher teacher = new Teacher();
                                                    teacher.teacherMain();
                                                    break;
                                                case "3":
                                                    Subject subject = new Subject();
                                                    subject.subjectMain();
                                                    break;
                                                case "4":
                                                    Document.documentMain();
                                                    break;
                                                case "5":
                                                    Course course = new Course();
                                                    course.classManagerMain();
                                                    break;
                                                case "6":
                                                    SubDoc.subDocMain();
                                                    break;
                                                case "7":
                                                    break;
                                                default:
                                                    System.out.println("Ban da nhap sai! Nhan enter de nhap lai!");
                                                    scanner.nextLine();
                                                    Utils.clearScreen();
                                            }
                                            if (choiceMenuChoice.equals("7")) {
                                                Utils.clearScreen();
                                                break;
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println("Ban da nhap sai! Nhan enter de nhap lai!");
                                        scanner.nextLine();
                                        Utils.clearScreen();
                                }
                            }
                        } else {
                            System.out.println("Dang nhap that bai. Vui long thu lai!");
                            scanner.nextLine();
                        }
                    } catch (IOException e) {
                        System.out.println("Loi khi doc file nguoi dung!");
                    }
                    break;
                default:
                    System.out.println("Ban da nhap sai! Nhan enter de nhap lai!");
                    scanner.nextLine();
                    Utils.clearScreen();
            }
        }
    }
}
