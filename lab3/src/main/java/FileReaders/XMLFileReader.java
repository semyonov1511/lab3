package FileReaders;

import Interface.Reactor;
import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import org.apache.commons.io.FilenameUtils;

public class XMLFileReader extends FileReader{
    @Override
    public ArrayList<Reactor> read(File file) {
        if ("xml".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            return readXML(file);
        } else if (nextFileReader != null) {
            nextFileReader.read(file);
        }
        return null;
    }
    
    public ArrayList<Reactor> readXML(File file) {
        ArrayList<Reactor> reactorList = new ArrayList<>();
        Reactor reactor = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            // инициализируем reader и скармливаем ему xml файл
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file.getAbsolutePath())); // проходим по всем элементам xml файла
            while (reader.hasNext()) {
                // получаем событие (элемент) и разбираем его по атрибутам XMLEvent xmlEvent reader.nextEvent();
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Reactor")) {
                        //System.out.println("Название реактора - " + startElement.getAttributeByName(new QName("name")).getValue());
                        reactor = new Reactor();
                        // Получаем атрибут id для каждого элемента Student
                        /* Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                        }
                        reactor.setId(Integer.parseInt(idAttr.getValue()));
                        */
                    } else if (startElement.getName().getLocalPart().equals("class")) {
                        xmlEvent = reader.nextEvent();
                        reactor.setClass(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("burnup")) {
                        xmlEvent = reader.nextEvent();
                        reactor.setBurnup(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("kpd")) {
                        xmlEvent = reader.nextEvent();
                        reactor.setKPD(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    }else if (startElement.getName().getLocalPart().equals("enrichment")) {
                        xmlEvent = reader.nextEvent();
                        reactor.setEnrichment(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("termal_capacity")) {
                        xmlEvent = reader.nextEvent();
                        reactor.setTCapacity(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("electrical_capacity")) {
                        xmlEvent = reader.nextEvent();
                        reactor.setECapacity(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    }else if (startElement.getName().getLocalPart().equals("life_time")) {
                        xmlEvent = reader.nextEvent();
                        reactor.setLifetime(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("first_load")) {
                        xmlEvent = reader.nextEvent();
                        reactor.setFirstload(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    } 
                }
                // если цикл дошел до закрывающего элемента Student,
                // то добавляем считанного из файла студента в список
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Reactor")) {
                        reactor.setFiletype("XML");
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
