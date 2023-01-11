package org.example.message;

public class MessageFormatter {
    public static String createMessageFromDto(MessageDto dto){
        String HEADER = "Здравствуйте, дорогая " + dto.getTargetName() + "\n";
        String BODY = "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n" +
                "\n" +
                "Исчезли следующие страницы: " + dto.getDeletedURLs() + "\n" +
                "Появились следующие новые страницы: " + dto.getCreatedURLs() + "\n" +
                "Изменились следующие страницы: " + dto.getEditedURLs() + "\n";
        String FOOTER = "С уважением,\n" +
                "автоматизированная система\n" +
                "мониторинга.";
        return HEADER+"\n"+BODY+"\n"+FOOTER;
    }
}
