package org.example.html;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class Pages {
    private HashMap<URL, File> pages;

    public Pages() {
        this.pages = new HashMap<>();
    }

    public void insert(URL url, File file){
        pages.put(url,file);
    }

    public HashMap<URL, File> getPages() {
        return pages;
    }
}
