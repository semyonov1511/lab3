package FileReaders;

import Interface.Reactor;
import Interface.ReactorType;
import Interface.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.ScalarEvent;

public class YAMLFileReader extends FileReader {

    @Override
    public void read(File file) {
        if ("yaml".equals(FilenameUtils.getExtension(file.getAbsolutePath()))) {
            try {
                Repository.getInstance().setList(readYAML(file));
            } catch (IOException e) {
            }
        } else if (nextFileReader != null) {
            nextFileReader.read(file);
        }
    }

    public ArrayList<Reactor> readYAML(File file) throws FileNotFoundException, IOException {
        Map<String, Reactor> reactorMap = (new YAMLMapper()).readValue(file, new TypeReference<Map<String, Reactor>>() {
        });
        ArrayList<Reactor> reactorlist = new ArrayList<>(reactorMap.values());
        return reactorlist;
    }
}