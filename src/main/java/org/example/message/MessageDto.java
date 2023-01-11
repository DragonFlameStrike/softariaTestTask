package org.example.message;

import java.net.URL;
import java.util.ArrayList;

public class MessageDto {
    private ArrayList<URL> deletedURLs;
    private ArrayList<URL> createdURLs;
    private ArrayList<URL> editedURLs;
    private String targetName;

    public ArrayList<URL> getDeletedURLs() {
        return deletedURLs;
    }

    public void setDeletedURLs(ArrayList<URL> deletedURLs) {
        this.deletedURLs = deletedURLs;
    }

    public ArrayList<URL> getCreatedURLs() {
        return createdURLs;
    }

    public void setCreatedURLs(ArrayList<URL> createdURLs) {
        this.createdURLs = createdURLs;
    }

    public ArrayList<URL> getEditedURLs() {
        return editedURLs;
    }

    public void setEditedURLs(ArrayList<URL> editedURLs) {
        this.editedURLs = editedURLs;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
