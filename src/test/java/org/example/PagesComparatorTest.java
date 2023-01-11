package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.html.Pages;
import org.example.html.PagesComparator;
import org.example.html.PagesComparatorImp;
import org.example.message.MessageDto;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class PagesComparatorTest
{
    @Test
    public void ifNothingChanged() throws IOException {
        PagesComparator pagesComparator = new PagesComparatorImp();
        Pages oldPages = new Pages();
        Pages newPages = new Pages();
        File stableFile = new File("stable.html");
        FileWriter myWriter = new FileWriter("stable.html");
        myWriter.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Stable</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        myWriter.close();
        oldPages.insert(new URL("https://d.pankov/stable.html"),stableFile);
        newPages.insert(new URL("https://d.pankov/stable.html"),stableFile);

        MessageDto result = pagesComparator.comparePages(oldPages,newPages);

        assertTrue(result.getCreatedURLs().isEmpty());
        assertTrue(result.getDeletedURLs().isEmpty());
        assertTrue(result.getEditedURLs().isEmpty());

        stableFile.delete();
    }

    @Test
    public void ifFileEdited() throws IOException {
        PagesComparator pagesComparator = new PagesComparatorImp();
        Pages oldPages = new Pages();
        Pages newPages = new Pages();
        File firstDir = new File("dir1");
        File secondDir = new File("dir2");
        firstDir.mkdir();
        secondDir.mkdir();
        File editedFileOld = new File("dir1/edited.html");
        File editedFileNew = new File("dir2/edited.html");
        FileWriter myWriterFirst = new FileWriter(editedFileOld.getPath());
        myWriterFirst.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>First</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        myWriterFirst.close();
        FileWriter myWriterSecond = new FileWriter(editedFileNew.getPath());
        myWriterSecond.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Second</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        myWriterSecond.close();

        oldPages.insert(new URL("https://d.pankov/edited.html"),editedFileOld);
        newPages.insert(new URL("https://d.pankov/edited.html"),editedFileNew);

        MessageDto result = pagesComparator.comparePages(oldPages,newPages);

        assertTrue(result.getCreatedURLs().isEmpty());
        assertTrue(result.getDeletedURLs().isEmpty());
        assertEquals(1, result.getEditedURLs().size());

        editedFileOld.delete();
        editedFileNew.delete();
        firstDir.delete();
        secondDir.delete();

    }

    @Test
    public void ifNewHtmlCreated() throws IOException {
        PagesComparator pagesComparator = new PagesComparatorImp();
        Pages oldPages = new Pages();
        Pages newPages = new Pages();
        File newFile = new File("new.html");
        newPages.insert(new URL("https://d.pankov/new.html"),newFile);

        MessageDto result = pagesComparator.comparePages(oldPages,newPages);

        assertEquals(1, result.getCreatedURLs().size());
        assertTrue(result.getDeletedURLs().isEmpty());
        assertTrue(result.getEditedURLs().isEmpty());

        newFile.delete();
    }

    @Test
    public void ifOldHtmlDeleted() throws IOException {
        PagesComparator pagesComparator = new PagesComparatorImp();
        Pages oldPages = new Pages();
        Pages newPages = new Pages();
        File oldFile = new File("deleted.html");
        oldPages.insert(new URL("https://d.pankov/deleted.html"),oldFile);

        MessageDto result = pagesComparator.comparePages(oldPages,newPages);

        assertEquals(1, result.getDeletedURLs().size());
        assertTrue(result.getCreatedURLs().isEmpty());
        assertTrue(result.getEditedURLs().isEmpty());

        oldFile.delete();
    }
}
