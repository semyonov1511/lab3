package FileReaders;

import Interface.Reactor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

public class YAMLFileReader extends FileReader {

    @Override
    public ArrayList<Reactor> read(File file) {
        if ("xml".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            return readXML(file);
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

    private InputStream findFile(String path) throws FileNotFoundException{
        InputStream inputStream = new FileInputStream(path);
        return inputStream;
    }

    private void handleFile(Iterable<Event> data) throws Exception  {
        for (Event event : data) {
            parseEvent(event);
        }
    }

    private void parseEvent(Event event) throws Exception {
        switch(event.getEventId()){
            case StreamStart:
                break;
            case StreamEnd:
                break;
            case DocumentStart:
                createCurrent();
                break;
            case DocumentEnd:
                addCurrentToList();break;
            case MappingStart:
                parseMapping((MappingStartEvent) event);break;
            case MappingEnd:
                collectionTag = "none";mappingLevel--;
                break;
            case Scalar:
                parseScalarEvent((ScalarEvent) event);break;
            default:
                 throw new Exception("UnknownEvent");
               
        }
    }

    private void parseScalarEvent(ScalarEvent event) {
        if(keyCounter==0) {
            key = event.getValue();
            keyCounter++;
        } else {
            String value = event.getValue();
            currentReaded.addCharacteristic(key, value, collectionTag);
            keyCounter = 0;
        }
        
    }
    

    private void createCurrent() {
        this.currentReaded = KindredCreator.create();
    }

    private void addCurrentToList() {
        this.readedList.add(currentReaded);
    }

    private void parseMapping(MappingStartEvent mappingStartEvent) {
        if (mappingLevel > 0){
            keyCounter = 0;
            collectionTag = key;
        }
        mappingLevel++;
    }

}
