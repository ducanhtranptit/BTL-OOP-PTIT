package Impl;

public interface DocumentInterface {
    String getDocument_id();

    String getName();

    String getLink();

    String getSubject_id();

    void setDocument_id(String id);

    void setName(String name);

    void setLink(String link);

    void setSubject_id(String subjectId);
}
