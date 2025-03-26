package Impl;

import java.io.FileWriter;

import Object.Document;

public interface DocumentInterface {
    String getDocument_id();

    String getName();

    String getLink();

    String getSubject_id();

    void setDocument_id(String id);

    void setName(String name);

    void setLink(String link);

    void setSubject_id(String subjectId);

    Document typeDocument();

    boolean check(Document dc);

    void addDocument();

    void printDocument(Document dc);

    void printAllDocument();

    void writeDocument(Document dc, FileWriter file);

    Document findDocument(String id);

    void deleteDocument(String id);

    void editDocument(String id);

    void showDocumentMenu();

    void documentMain();
}
