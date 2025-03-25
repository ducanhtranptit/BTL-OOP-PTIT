package Object;

import java.io.*;
import java.util.*;
import Utils.Utils;
import Impl.DocumentInterface;

public class Document implements DocumentInterface {
    private String document_id;
    private String name;
    private String link;
    private String subject_id;

    private static final Scanner scanner = new Scanner(System.in);

    public Document() {
    }

    public Document(String document_id, String name, String link, String subject_id) {
        this.document_id = document_id;
        this.name = name;
        this.link = link;
        this.subject_id = subject_id;
    }

    public String getDocument_id() {
        return document_id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public static Document typeDocument() {
        Document dc = new Document();
        System.out.print("Nhap ma tai lieu : ");
        dc.setDocument_id(scanner.nextLine());
        System.out.print("Nhap ten tai lieu : ");
        dc.setName(scanner.nextLine());
        System.out.print("Nhap link tai lieu : ");
        dc.setLink(scanner.nextLine());
        System.out.print("Nhap ma mon hoc cua tai lieu nay : ");
        dc.setSubject_id(scanner.nextLine());
        return dc;
    }

    public static boolean check(Document dc) {
        return !dc.getLink().isEmpty() &&
                !dc.getDocument_id().isEmpty() &&
                !dc.getSubject_id().isEmpty() &&
                !dc.getName().isEmpty();
    }

    public static void addDocument() {
        System.out.println("Them tai lieu");
        try (FileWriter file = new FileWriter("data/documents.csv", true)) {
            Document newDocument;
            do {
                newDocument = typeDocument();
                if (!check(newDocument)) {
                    System.out.println("Thong tin khong duoc de trong. Vui long nhap lai.");
                    scanner.nextLine();
                }
            } while (!check(newDocument));
            writeDocument(newDocument, file);
            System.out.println("\nXong!\n");
        } catch (IOException e) {
            System.out.println("Khong the mo file documents.csv.");
        }
    }

    public static void printDocument(Document dc) {
        System.out.println("--------------------------");
        System.out.println("Ma tai lieu : " + dc.getDocument_id());
        System.out.println("Ten tai lieu : " + dc.getName());
        System.out.println("Link tai lieu : " + dc.getLink());
        System.out.println("Ma mon hoc : " + dc.getSubject_id());
        System.out.println("--------------------------");
    }

    public static void printAllDocument() {
        try (BufferedReader file = new BufferedReader(new FileReader("data/documents.csv"))) {
            String row;
            while ((row = file.readLine()) != null) {
                String[] rowList = row.split(",");
                if (rowList.length >= 4) {
                    printDocument(new Document(rowList[0], rowList[1], rowList[2], rowList[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file documents.csv!");
        }
    }

    public static void writeDocument(Document dc, FileWriter file) {
        try {
            file.write(dc.getDocument_id() + "," + dc.getName() + "," + dc.getLink() + "," + dc.getSubject_id() + "\n");
        } catch (IOException e) {
            System.out.println("Khong the viet vao file documents.csv!");
        }
    }

    public static Document findDocument(String id) {
        try (BufferedReader file = new BufferedReader(new FileReader("data/documents.csv"))) {
            String row;
            while ((row = file.readLine()) != null) {
                if (row.contains(id)) {
                    String[] rowList = row.split(",");
                    if (rowList.length >= 4) {
                        return new Document(rowList[0], rowList[1], rowList[2], rowList[3]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file documents.csv.");
        }
        return null;
    }

    public static void deleteDocument(String id) {
        try (BufferedReader file = new BufferedReader(new FileReader("data/documents.csv"))) {
            List<String> temp = new ArrayList<>();
            String row;
            while ((row = file.readLine()) != null) {
                if (!row.contains(id)) {
                    temp.add(row);
                }
            }

            try (FileWriter fileWriter = new FileWriter("data/documents.csv")) {
                for (String line : temp) {
                    fileWriter.write(line + "\n");
                }
            }

            System.out.println("\nDa xoa thanh cong!\n");
        } catch (IOException e) {
            System.out.println("Khong the mo file documents.csv!");
        }
    }

    public static void editDocument(String id) {
        Document existingDocument = findDocument(id);
        if (existingDocument != null) {
            System.out.println("Thong tin tai lieu hien tai:");
            printDocument(existingDocument);
            System.out.println("Nhap thong tin moi cho tai lieu (de blank neu muon giu nguyen):");
            Document editedDocument = typeDocument();

            if (!editedDocument.getDocument_id().isEmpty()) {
                existingDocument.setDocument_id(editedDocument.getDocument_id());
            }
            if (!editedDocument.getName().isEmpty()) {
                existingDocument.setName(editedDocument.getName());
            }
            if (!editedDocument.getLink().isEmpty()) {
                existingDocument.setLink(editedDocument.getLink());
            }
            if (!editedDocument.getSubject_id().isEmpty()) {
                existingDocument.setSubject_id(editedDocument.getSubject_id());
            }

            try (BufferedReader fileReader = new BufferedReader(new FileReader("data/documents.csv"))) {
                List<String> fileContent = new ArrayList<>();
                String row;
                while ((row = fileReader.readLine()) != null) {
                    if (row.contains(id)) {
                        row = existingDocument.getDocument_id() + "," + existingDocument.getName() + "," +
                                existingDocument.getLink() + "," + existingDocument.getSubject_id();
                    }
                    fileContent.add(row);
                }

                try (FileWriter fileWriter = new FileWriter("data/documents.csv")) {
                    for (String line : fileContent) {
                        fileWriter.write(line + "\n");
                    }
                }

                System.out.println("\nHoan tat!\n");
            } catch (IOException e) {
                System.out.println("Khong the mo file documents.csv.");
            }
        } else {
            System.out.println("Khong tim thay tai lieu voi ID: " + id);
        }
    }

    public static void showDocumentMenu() {
        System.out.println("*******************************************");
        System.out.println("* Ban hay chon 1 lua chon:                *");
        System.out.println("* 1. Them tai lieu                        *");
        System.out.println("* 2. Hien thi toan bo tai lieu            *");
        System.out.println("* 3. Hien thi tai lieu qua id             *");
        System.out.println("* 4. Xoa tai lieu                         *");
        System.out.println("* 5. Sua thong tin tai lieu               *");
        System.out.println("* 6. Quay ve menu chinh                   *");
        System.out.println("*******************************************");
    }

    public static void documentMain() {
        while (true) {
            Utils.clearScreen();
            showDocumentMenu();
            System.out.print("Ban chon (1-6): ");
            String customerChoice = scanner.nextLine();

            switch (customerChoice) {
                case "1":
                    addDocument();
                    break;
                case "2":
                    printAllDocument();
                    break;
                case "3":
                    System.out.print("Nhap ma tai lieu muon tim: ");
                    printDocument(findDocument(scanner.nextLine()));
                    break;
                case "4":
                    System.out.print("Nhap ma tai lieu muon xoa: ");
                    deleteDocument(scanner.nextLine());
                    break;
                case "5":
                    System.out.print("Nhap ma tai lieu muon chinh sua: ");
                    editDocument(scanner.nextLine());
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }

            System.out.println("Nhan Enter de tiep tuc!");
            scanner.nextLine();
            Utils.clearScreen();
        }
    }
}
