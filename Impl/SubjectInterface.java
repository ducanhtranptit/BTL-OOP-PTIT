package Impl;

import java.io.FileWriter;
import java.io.IOException;

import Object.Subject;

public interface SubjectInterface {
    void addSubject();

    void printSubject(Subject su);

    void printAllSubject();

    void deleteSubject(String id);

    void editSubject(String id);

    Subject findSubject(String id);

    boolean check(Subject su);

    void writeSubject(Subject su, FileWriter file) throws IOException;

    Subject typeSubject();

    void showSubjectMenu();

    void subjectMain();
}
