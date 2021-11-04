package controller.command.parser;


import controller.command.Command;
import jdk.internal.org.xml.sax.ContentHandler;
import jdk.internal.org.xml.sax.InputSource;
import jdk.internal.org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ParserSAX {

    private static final String COMMANDS_XML = "Commands.xml";

    public Map<String, Command> getCommandMap() throws SAXException, IOException, jdk.internal.org.xml.sax.SAXException {

        XMLReader reader = (XMLReader) XMLReaderFactory.createXMLReader();
        CommandSAXHandler handler = new CommandSAXHandler();
        reader.setContentHandler((ContentHandler) handler);
        InputStream is = getClass().getClassLoader().getResourceAsStream(COMMANDS_XML);
        reader.parse(new InputSource(is));
        return handler.getCommandMap();

    }

}
