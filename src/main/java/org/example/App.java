package org.example;


import org.example.html.HtmlRepository;
import org.example.html.HtmlRepositoryImp;
import org.example.html.PagesComparator;
import org.example.html.PagesComparatorImp;
import org.example.message.MessageCreator;

public class App
{
    public static void main( String[] args )
    {
        MessageCreator messageCreator = new MessageCreator();
        HtmlRepository htmlRepository = new HtmlRepositoryImp();
        PagesComparator pagesComparator = new PagesComparatorImp();

        messageCreator.setHtmlRepository(htmlRepository);
        messageCreator.setPagesComparator(pagesComparator);

        htmlRepository.init();

        String message = messageCreator.createMessage("Кристина Москаленко");
        System.out.println(message);
    }
}
