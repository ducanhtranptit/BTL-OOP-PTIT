package Impl;

import Object.Subject;

public interface SubjectInterface {
    void addSubject();

    void printSubject(Subject su);

    void printAllSubject();

    void deleteSubject(String id);

    void editSubject(String id);

    Subject findSubject(String id);

    boolean check(Subject su);

    Subject typeSubject();

    void showSubjectMenu();
}
