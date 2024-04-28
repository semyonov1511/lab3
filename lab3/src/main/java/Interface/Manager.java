package Interface;

import FileReaders.FileReader;
import FileReaders.JSONFileReader;
import FileReaders.XMLFileReader;
import FileReaders.YAMLFileReader;
import ReactorsRelated.Reactor;
import ReactorsRelated.Repository;
import java.io.File;
import java.util.ArrayList;

public class Manager {

    Repository repository = new Repository();
    FileReader XMLfilereader = new XMLFileReader();
    
    public Manager() {
        FileReader YAMLfilereader = new YAMLFileReader();
        FileReader JSONfilereader = new JSONFileReader();
        XMLfilereader.setNextFileReader(YAMLfilereader);
        YAMLfilereader.setNextFileReader(JSONfilereader);
    }

    public void setList(File file) {
        repository.setList(XMLfilereader.read(file));
        for (Reactor reactor : repository.getList()){
            reactor.setParameters();
        }
    }

    public ArrayList<Reactor> getList() {
        return repository.getList();
    }
    
}
