package Object;

import java.io.*;
import java.util.*;

import Utils.Utils;
import Impl.SubjectInterface;

public class Subject implements SubjectInterface {
    private String subject_id;
    private String name;
    private String description;
    private String link;
    private String class_id;

    private static final Scanner scanner = new Scanner(System.in);

    public Subject() {
    }

    public Subject(String subject_id, String name, String description, String link, String class_id) {
        this.subject_id = subject_id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.class_id = class_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    @Override
    public Subject typeSubject() {
        Subject su = new Subject();
        System.out.print("Nhap ma mon hoc: ");
        su.setSubject_id(scanner.nextLine());
        System.out.print("Nhap ten: ");
        su.setName(scanner.nextLine());
        System.out.print("Nhap mo ta: ");
        su.setDescription(scanner.nextLine());
        System.out.print("Nhap link xem gioi thieu mon hoc: ");
        su.setLink(scanner.nextLine());
        System.out.print("Nhap ma lop: ");
        su.setClass_id(scanner.nextLine());
        return su;
    }

    @Override
    public boolean check(Subject su) {
        return !su.getLink().isEmpty()
                && !su.getClass_id().isEmpty()
                && !su.getSubject_id().isEmpty()
                && !su.getName().isEmpty()
                && !su.getDescription().isEmpty();
    }

    @Override
    public void addSubject() {
        System.out.println("Them mon hoc");
        try (FileWriter file = new FileWriter("data/subjects.csv", true)) {
            Subject newSubject;
            do {
                newSubject = typeSubject();
                if (!check(newSubject)) {
                    System.out.println("Thong tin khong duoc de trong. Vui long nhap lai.");
                    scanner.nextLine();
                }
            } while (!check(newSubject));
            writeSubject(newSubject, file);
            System.out.println("\nXong!\n");
        } catch (IOException e) {
            System.out.println("Khong the mo file subjects.csv.");
        }
    }

    @Override
    public void printSubject(Subject su) {
        System.out.println("--------------------------");
        System.out.println("Ma mon hoc : " + su.getSubject_id());
        System.out.println("Ten mon hoc : " + su.getName());
        System.out.println("Mo ta : " + su.getDescription());
        System.out.println("Link gioi thieu mon hoc : " + su.getLink());
        System.out.println("Ma lop hoc : " + su.getClass_id());
        System.out.println("--------------------------");
    }

    @Override
    public void printAllSubject() {
        try (BufferedReader file = new BufferedReader(new FileReader("data/subjects.csv"))) {
            String row;
            while ((row = file.readLine()) != null) {
                String[] rowList = row.split(",");
                printSubject(new Subject(rowList[0], rowList[1], rowList[2], rowList[3], rowList[4]));
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file subjects.csv.");
        }
    }

    @Override
    public void writeSubject(Subject su, FileWriter file) throws IOException {
        file.write(su.getSubject_id() + "," + su.getName() + "," + su.getDescription() + "," +
                su.getLink() + "," + su.getClass_id() + "\n");
    }

    @Override
    public Subject findSubject(String id) {
        try (BufferedReader file = new BufferedReader(new FileReader("data/subjects.csv"))) {
            String row;
            while ((row = file.readLine()) != null) {
                if (row.contains(id)) {
                    String[] rowList = row.split(",");
                    return new Subject(rowList[0], rowList[1], rowList[2], rowList[3], rowList[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file subjects.csv.");
        }
        return null;
    }

    @Override
    public void deleteSubject(String id) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("data/subjects.csv"));
            List<String> temp = new ArrayList<>();
            String row;
            while ((row = file.readLine()) != null) {
                if (!row.contains(id)) {
                    temp.add(row);
                }
            }
            file.close();

            try (FileWriter fileWriter = new FileWriter("data/subjects.csv")) {
                for (String line : temp) {
                    fileWriter.write(line + "\n");
                }
            }

            System.out.println("\nDa xoa thanh cong!\n");
        } catch (IOException e) {
            System.out.println("Khong the mo file subjects.csv.");
        }
    }

    @Override
    public void editSubject(String id) {
        Subject existingSubject = findSubject(id);
        if (existingSubject != null) {
            System.out.println("Thong tin mon hoc hien tai:");
            printSubject(existingSubject);
            System.out.println("Nhap thong tin moi cho mon hoc (de blank neu muon giu nguyen):");
            Subject editedSubject = typeSubject();

            if (!editedSubject.getSubject_id().isEmpty()) {
                existingSubject.setSubject_id(editedSubject.getSubject_id());
            }
            if (!editedSubject.getName().isEmpty()) {
                existingSubject.setName(editedSubject.getName());
            }
            if (!editedSubject.getDescription().isEmpty()) {
                existingSubject.setDescription(editedSubject.getDescription());
            }
            if (!editedSubject.getLink().isEmpty()) {
                existingSubject.setLink(editedSubject.getLink());
            }
            if (!editedSubject.getClass_id().isEmpty()) {
                existingSubject.setClass_id(editedSubject.getClass_id());
            }

            try (BufferedReader fileReader = new BufferedReader(new FileReader("data/subjects.csv"))) {
                List<String> fileContent = new ArrayList<>();
                String row;
                while ((row = fileReader.readLine()) != null) {
                    if (row.contains(id)) {
                        row = existingSubject.getSubject_id() + "," + existingSubject.getName() + "," +
                                existingSubject.getDescription() + "," + existingSubject.getLink() + "," +
                                existingSubject.getClass_id();
                    }
                    fileContent.add(row);
                }

                try (FileWriter fileWriter = new FileWriter("data/subjects.csv")) {
                    for (String line : fileContent) {
                        fileWriter.write(line + "\n");
                    }
                }

                System.out.println("\nHoan tat!\n");
            } catch (IOException e) {
                System.out.println("Khong the mo file subjects.csv.");
            }
        } else {
            System.out.println("Khong tim thay ID mon hoc nay: " + id);
        }
    }

    @Override
    public void showSubjectMenu() {
        System.out.println("*******************************************");
        System.out.println("* Ban hay chon 1 lua chon:                *");
        System.out.println("* 1. Tao mon hoc moi                      *");
        System.out.println("* 2. Hien thi toan bo mon hoc             *");
        System.out.println("* 3. Hien thi mon hoc can tim             *");
        System.out.println("* 4. Xoa mon hoc                          *");
        System.out.println("* 5. Sua thong tin mon hoc                *");
        System.out.println("* 6. Quay ve menu chinh                   *");
        System.out.println("*******************************************");
    }

    @Override
    public void subjectMain() {
        while (true) {
            Utils.clearScreen();
            this.showSubjectMenu();
            System.out.print("Ban chon (1-6): ");
            String customerChoice = scanner.nextLine();

            switch (customerChoice) {
                case "1":
                    addSubject();
                    System.out.println("Hoan tat!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "2":
                    printAllSubject();
                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "3":
                    System.out.print("Ban hay nhap ma mon hoc muon tim kiem: ");
                    String id = scanner.nextLine();
                    Subject foundSubject = findSubject(id);
                    if (foundSubject != null) {
                        printSubject(foundSubject);
                    } else {
                        System.out.println("Khong tim thay ma mon hoc nay");
                    }
                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "4":
                    System.out.print("Ban hay nhap ma mon hoc ban muon xoa: ");
                    String deleteId = scanner.nextLine();
                    deleteSubject(deleteId);
                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "5":
                    System.out.print("Ban hay nhap ma mon hoc muon chinh sua: ");
                    String editId = scanner.nextLine();
                    editSubject(editId);
                    System.out.println("Nhan enter de tiep tuc!");
                    scanner.nextLine();
                    Utils.clearScreen();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Ban da nhap sai cu phap! Nhan enter de nhap lai!");
                    scanner.nextLine();
                    Utils.clearScreen();
            }
        }
    }
}

