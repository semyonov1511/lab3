package Interface;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

public class FileReader {

    public static ArrayList<Reactor> parseXMLfile(String fileName) {
        ArrayList<Reactor> reactorList = new ArrayList<>();
        Reactor reactor = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            // инициализируем reader и скармливаем ему xml файл
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName)); // проходим по всем элементам xml файла
            while (reader.hasNext()) {
                // получаем событие (элемент) и разбираем его по атрибутам XMLEvent xmlEvent reader.nextEvent();
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("MKER")) {
                        reactor = new Reactor();
                        System.out.println("бебебе");
                        // Получаем атрибут id для каждого элемента Student
                        //Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        //if (idAttr != null) {
                        //}
                        //reactor.setId(Integer.parseInt(idAttr.getValue()));
                    } else if (startElement.getName().getLocalPart().equals("class")) {
                        xmlEvent = reader.nextEvent();
                        System.out.println("гыгы " + xmlEvent.asCharacters().getData());
                        reactor.setClass(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        //reactor.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("language")) {
                        xmlEvent = reader.nextEvent();
                        //reactor.setLanguage(xmlEvent.asCharacters().getData());
                    }
                }
                // если цикл дошел до закрывающего элемента Student,
                // то добавляем считанного из файла студента в список
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Reactor")) {
                        System.out.println("бебебе");
                        reactorList.add(reactor);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }

        return reactorList;
    }

}
