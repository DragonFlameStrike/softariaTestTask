package org.example.html;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;


public class HtmlRepositoryImp implements HtmlRepository{
    private static final String todayDirPath = "pages/todayPages";
    private static final String yesterdayDirPath = "pages/yesterdayPages";
    private static final String URLsFileName = "HtmlURLs.txt";
    private Pages yesterdayPages;
    private Pages todayPages;

    @Override
    public void init() {
        yesterdayPages = getPagesFromDir(yesterdayDirPath);
        todayPages = getPagesFromDir(todayDirPath);
    }

    private Pages getPagesFromDir(String dirPath){
        Pages out = new Pages();
        try {
            File htmlURLs = getFileFromResource(dirPath + "/" + URLsFileName);
            Scanner htmlURLsReader = new Scanner(htmlURLs);
            while (htmlURLsReader.hasNextLine()) {
                URL url = new URL(htmlURLsReader.nextLine());
                String filename = getLastSegmentPathFromURL(url);
                File htmlPage = getFileFromResource(dirPath + "/" + filename);
                out.insert(url,htmlPage);
            }
            htmlURLsReader.close();
        } catch (URISyntaxException | FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("Bad URL format.");
            e.printStackTrace();
        }
        return out;
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    private String getLastSegmentPathFromURL(URL url){
        String path = url.getPath();
        String[] segments = path.split("/");
        return segments[segments.length-1];
    }

    @Override
    public Pages getYesterdayPages() {
        return yesterdayPages;
    }
    @Override
    public Pages getTodayPages() {
        return todayPages;
    }
}
