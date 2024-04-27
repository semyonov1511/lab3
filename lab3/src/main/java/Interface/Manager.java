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

    public void setList(ArrayList<Reactor> rlist, String type) {
        repository.setList(rlist, type);
    }

    public ArrayList<Reactor> getList() {
        return repository.getList();
    }

    public Object Returner(int i, int j) {
        return repository.Returner(i, j);
    }

    public void read(File file) {
        setList(XMLfilereader.read(file), "yaml");

    }
}
