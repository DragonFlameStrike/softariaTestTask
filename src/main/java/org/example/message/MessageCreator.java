package org.example.message;

import org.example.html.HtmlRepository;
import org.example.html.PagesComparator;

public class MessageCreator {

    HtmlRepository htmlRepository;
    PagesComparator pagesComparator;

    public String createMessage(String targetName){
        MessageDto dto = pagesComparator.comparePages(htmlRepository.getYesterdayPages(),htmlRepository.getTodayPages());
        dto.setTargetName(targetName);
        return MessageFormatter.createMessageFromDto(dto);
    }

    public void setHtmlRepository(HtmlRepository htmlRepository) {
        this.htmlRepository = htmlRepository;
    }

    public void setPagesComparator(PagesComparator pagesComparator) {
        this.pagesComparator = pagesComparator;
    }
}
