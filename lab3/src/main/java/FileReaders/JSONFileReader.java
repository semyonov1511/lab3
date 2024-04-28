package FileReaders;

import Interface.Manager;
import ReactorsRelated.Reactor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

public class JSONFileReader extends FileReader {

    @Override
    public ArrayList<Reactor> read(File file) {
        ArrayList<Reactor> list;
        if ("json".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            try {
                list = readJSON(file);
                for (Reactor reactor : list) {
                    reactor.setFiletype("JSON");
                }
                return list;
            } catch (IOException e) {
            }
        } else if (nextFileReader != null) {
            return nextFileReader.read(file);
        }
        return null;
    }

    public ArrayList<Reactor> readJSON(File file) throws FileNotFoundException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Reactor> list = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Reactor.class));
        return list;
    }
}
