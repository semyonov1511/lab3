package FileReaders;

import Interface.Reactor;
import Interface.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;

public class JSONFileReader extends FileReader {

    @Override
    public void read(File file) {
        if ("json".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            try {
                Repository.getInstance().setList(readYAML(file), "JSON");
            } catch (IOException e) {
            }
        } else if (nextFileReader != null) {
            nextFileReader.read(file);
        }
    }

    public ArrayList<Reactor> readYAML(File file) throws FileNotFoundException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Reactor> list = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Reactor.class));
        return list;
    }
}
