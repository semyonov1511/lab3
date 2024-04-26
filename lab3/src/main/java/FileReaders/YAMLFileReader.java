package FileReaders;

import Interface.Reactor;
import Interface.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;

public class YAMLFileReader extends FileReader {

    @Override
    public void read(File file) {
        if ("yaml".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            try {
                Repository.getInstance().setList(readYAML(file),"YAML");
            } catch (IOException e) {
            }
        } else if (nextFileReader != null) {
            nextFileReader.read(file);
        }
    }

    public ArrayList<Reactor> readYAML(File file) throws FileNotFoundException, IOException {
        Map<String, Reactor> map = (new YAMLMapper()).readValue(file, new TypeReference<Map<String, Reactor>>() {});
        ArrayList<Reactor> list = new ArrayList<>(map.values());
        return list;
    }
}