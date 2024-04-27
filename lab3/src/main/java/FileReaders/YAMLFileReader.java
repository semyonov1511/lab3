package FileReaders;

import Interface.Manager;
import ReactorsRelated.Reactor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

public class YAMLFileReader extends FileReader {

    @Override
    public ArrayList<Reactor> read(File file) {
        if ("yaml".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            try {
                return readYAML(file);
            } catch (IOException e) {
                System.out.println("на ямле баг");
            }
        } else if (nextFileReader != null) {
            return nextFileReader.read(file);
        }
        return null;
    }

    public ArrayList<Reactor> readYAML(File file) throws FileNotFoundException {
        Map<String, Reactor> map = null;
        try {
            map = (new YAMLMapper()).readValue(file, new TypeReference<Map<String, Reactor>>() {});
        } catch (IOException ex) {
            Logger.getLogger(YAMLFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Reactor> list = new ArrayList<>(map.values());
        return list;
    }
}