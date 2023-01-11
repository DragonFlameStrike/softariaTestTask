package org.example.html;

import org.example.message.MessageDto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;

public class PagesComparatorImp implements PagesComparator {
    @Override
    public MessageDto comparePages(Pages oldPages, Pages newPages) {
        MessageDto dto = new MessageDto();
        dto.setDeletedURLs(getDeletedPageURLs(oldPages,newPages));
        dto.setCreatedURLs(getCreatedPageURLs(oldPages,newPages));
        dto.setEditedURLs(getEditedPageURLs(oldPages,newPages));
        return dto;
    }

    private ArrayList<URL> getDeletedPageURLs(Pages oldPages, Pages newPages) {
        ArrayList<URL> out = new ArrayList<>();
        for (Map.Entry<URL, File> entry : oldPages.getPages().entrySet()) {
            URL key = entry.getKey();
            if (isPageDeleted(newPages, key)) {
                out.add(key);
            }
        }
        return out;
    }

    private boolean isPageDeleted(Pages pages, URL key) {
        return !pages.getPages().containsKey(key);
    }

    private ArrayList<URL> getCreatedPageURLs(Pages oldPages, Pages newPages) {
        ArrayList<URL> out = new ArrayList<>();
        for (Map.Entry<URL, File> entry : newPages.getPages().entrySet()) {
            URL key = entry.getKey();
            if (isPageNotExist(oldPages, key)) {
                out.add(key);
            }
        }
        return out;
    }

    private boolean isPageNotExist(Pages pages, URL key) {
        return !pages.getPages().containsKey(key);
    }

    private ArrayList<URL> getEditedPageURLs(Pages oldPages, Pages newPages) {
        ArrayList<URL> out = new ArrayList<>();
        for (Map.Entry<URL, File> entry : oldPages.getPages().entrySet()) {
            URL key = entry.getKey();
            File oldHtml = entry.getValue();
            if (isPageExist(newPages, key)) {
                File newHtml = newPages.getPages().get(key);
                if (isDifferentFiles(oldHtml, newHtml)) {
                    out.add(key);
                }
            }
        }
        return out;
    }

    private boolean isPageExist(Pages pages, URL key) {
        return pages.getPages().containsKey(key);
    }

    private boolean isDifferentFiles(File first, File second) {
        try {
            return Files.mismatch(first.toPath(), second.toPath()) != -1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
