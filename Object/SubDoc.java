package Object;

import java.io.*;
import java.util.*;

import Utils.Utils;

public class SubDoc {
    private List<Subject> subjects;
    private List<Document> documents;

    public SubDoc() {
        subjects = loadSubjectsFromFile("data/subjects.csv");
        documents = loadDocumentsFromFile("data/documents.csv");
    }

    private List<Document> loadDocumentsFromFile(String filename) {
        List<Document> documentList = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            fileReader.readLine();
            String row;
            while ((row = fileReader.readLine()) != null) {
                String[] rowData = row.split(",");
                if (rowData.length >= 4) {
                    documentList.add(new Document(rowData[0], rowData[1], rowData[2], rowData[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the mo file " + filename);
        }
        return documentList;
    }

    private List<Subject> loadSubjectsFromFile(String filename) {
        List<Subject> subjectList = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            fileReader.readLine();
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

    public void displayEachSubjectInformation() {
        Set<String> subIds = new HashSet<>();

        for (Subject subject : subjects) {
            subIds.add(subject.getSubject_id());
        }

        for (String subId : subIds) {
            System.out.println("=======================================");
            System.out.println("MON HOC:");
            for (Subject subject : subjects) {
                if (subject.getSubject_id().equals(subId)) {
                    System.out.println("  - Mon hoc: " + subject.getName());
                    System.out.println("  - Subject ID: " + subId);
                }
            }

            System.out.println("TAI LIEU:");
            for (Document document : documents) {
                if (document.getSubject_id().equals(subId)) {
                    System.out.println("  - Document ID: " + document.getDocument_id());
                    System.out.println("  - Ten tai lieu: " + document.getName());
                    System.out.println("  - Link tai lieu: " + document.getLink());
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void subDocMain() {
        while (true) {
            SubDoc subdoc = new SubDoc();
            subdoc.displayEachSubjectInformation();
            System.out.println("Nhan Enter de quay ve menu chinh.");
            scanner.nextLine();
            Utils.clearScreen();
            break;
        }
    }
}
