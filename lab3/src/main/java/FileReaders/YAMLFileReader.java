package FileReaders;

import Interface.Reactor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.ScalarEvent;

public class YAMLFileReader extends FileReader {

    @Override
    public ArrayList<Reactor> read(File file) {
        if ("yaml".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            try {
                loadFile(file.getAbsolutePath());
                return getReadedList();
            } catch (Exception e) {
                System.out.println("чичигага");
                e.printStackTrace();
            }
        } else if (nextFileReader != null) {
            nextFileReader.read(file);
        }
        return null;
    }

    private Reactor currentReaded;
    private ArrayList<Reactor> readedList;
    private String key;
    private int keyCounter = 0;
    private String collectionTag = "none";
    private int counter = 0;
    private int mappingLevel;

    public ArrayList<Reactor> getReadedList() {
        return readedList;
    }

    public void loadFile(String path) throws FileNotFoundException, IOException, Exception {
        Yaml yaml = new Yaml();
        InputStream inputStream = findFile(path);
        Iterable<Event> data = yaml.parse(new InputStreamReader(inputStream));
        readedList = new ArrayList<>();
        handleFile(data);
        inputStream.close();
    }

    private InputStream findFile(String path) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(path);
        return inputStream;
    }

    private void handleFile(Iterable<Event> data) throws Exception {
        for (Event event : data) {
            parseEvent(event);
        }
    }

    private void parseEvent(Event event) throws Exception {
        switch (event.getEventId()) {
            case StreamStart:
                break;
            case StreamEnd:
                break;
            case DocumentStart:
                createCurrent();
                break;
            case DocumentEnd:
                addCurrentToList();
                break;
            case MappingStart:
                parseMapping((MappingStartEvent) event);
                break;
            case MappingEnd:
                collectionTag = "none";
                mappingLevel--;
                break;
            case Scalar:
                parseScalarEvent((ScalarEvent) event);
                break;
            default:
                throw new Exception("UnknownEvent");

        }
    }

    private void parseScalarEvent(ScalarEvent event) {
        if (keyCounter == 0) {
            key = event.getValue();
            keyCounter++;
        } else {
            String value = event.getValue();
            switch (key) {
                case "class" -> currentReaded.setClass(value);
                case "burnup" -> currentReaded.setBurnup(Double.parseDouble(value));
                case "kpd" -> currentReaded.setKPD(Double.parseDouble(value));
                case "enrichment" -> currentReaded.setEnrichment(Double.parseDouble(value));
                case "termal_capacity" -> currentReaded.setTCapacity(Integer.parseInt(value));
                case "electrical_capacity" -> currentReaded.setECapacity(Double.parseDouble(value));
                case "life_time" -> currentReaded.setLifetime(Integer.parseInt(value));
                case "first_load" -> currentReaded.setFirstload(Double.parseDouble(value));
                default -> {
                }
            }
            keyCounter = 0;
        }

    }

    private void createCurrent() {
        this.currentReaded = new Reactor();
    }

    private void addCurrentToList() {
        this.readedList.add(currentReaded);
    }

    private void parseMapping(MappingStartEvent mappingStartEvent) {
        if (mappingLevel > 0) {
            keyCounter = 0;
            collectionTag = key;
        }
        mappingLevel++;
    }

}
