package FileReaders;

import Interface.Manager;
import ReactorsRelated.Reactor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import org.apache.commons.io.FilenameUtils;

public class XMLFileReader extends FileReader{
    @Override
    public ArrayList<Reactor> read(File file) {
        ArrayList<Reactor> list;
        if ("xml".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            list = readXML(file);
            for (Reactor reactor : list){
                reactor.setFiletype("XML");
            }
            return list;
        } else if (nextFileReader != null) {
            return nextFileReader.read(file);
        }
        return null;
    }
    public ArrayList<Reactor> readXML(File file) {
        ArrayList<Reactor> list = new ArrayList<>();
        Reactor reactor = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file.getAbsolutePath())); // проходим по всем элементам xml файла
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "Reactor" -> {
                            reactor = new Reactor();
                        }
                        case "class" -> {
                            xmlEvent = reader.nextEvent();
                            reactor.setType(xmlEvent.asCharacters().getData());
                        }
                        case "burnup" -> {
                            xmlEvent = reader.nextEvent();
                            reactor.setBurnup(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        }
                        case "kpd" -> {
                            xmlEvent = reader.nextEvent();
                            reactor.setKPD(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        }
                        case "enrichment" -> {
                            xmlEvent = reader.nextEvent();
                            reactor.setEnrichment(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        }
                        case "termal_capacity" -> {
                            xmlEvent = reader.nextEvent();
                            reactor.setTCapacity(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        }
                        case "electrical_capacity" -> {
                            xmlEvent = reader.nextEvent();
                            reactor.setECapacity(Double.valueOf(xmlEvent.asCharacters().getData()));
                        }
                        case "life_time" -> { 
                            xmlEvent = reader.nextEvent();
                            reactor.setLifetime(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        }
                        case "first_load" -> {
                            xmlEvent = reader.nextEvent();
                            reactor.setFirstload(Double.parseDouble(xmlEvent.asCharacters().getData()));
                        }
                        default -> {
                        }
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Reactor")) {
                        list.add(reactor);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException exc) {
        }
        return list;
    }

}
