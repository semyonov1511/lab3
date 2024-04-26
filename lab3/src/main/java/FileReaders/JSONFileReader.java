package FileReaders;

import Interface.Manager;
import ReactorsRelated.Reactor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

public class JSONFileReader extends FileReader {

    @Override
    public void read(File file) {
        if ("json".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            try {
                Manager.getInstance().setList(readJSON(file), "JSON");
            } catch (IOException e) {
            }
        } else if (nextFileReader != null) {
            nextFileReader.read(file);
        }
    }

        public ArrayList<Reactor> readJSON(File file) throws FileNotFoundException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Reactor> list = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Reactor.class));
        return list;
    }
}
