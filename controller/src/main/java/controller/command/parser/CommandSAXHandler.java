package controller.command.parser;

import controller.command.Command;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandSAXHandler extends DefaultHandler {
    // TODO USEQCZ
    private static final String COMMAND_TAG = "command";
    private static final String PATH = "controller.controller.command.impl";
    private StringBuilder text;
    private CommandObj command;
    private Map<String, Command> commandMap = new HashMap<>();

    @Override
    public void startDocument() {
        System.out.println("starts parsing...");
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if (qName.equals(COMMAND_TAG)) {
            command = new CommandObj();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        text.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        CommandTags commandTag = CommandTags.valueOf(qName.toUpperCase(Locale.ROOT).replace("-","_"));

        switch (commandTag)
        {
            case WEBNAME:
                command.setWebName(text.toString());
                break;
            case COMMAND:
                try{
                    commandMap.put(command.getWebName(),
                            (Command) Class.forName(PATH + command.getClassName()).newInstance());
                } catch (ClassNotFoundException |InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }
                break;
            case CLASSNAME:
                command.setClassName(text.toString());
                break;
        }
    }
}


