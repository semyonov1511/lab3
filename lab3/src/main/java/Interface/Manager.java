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

    private static Manager INSTANCE;

    Repository repository = new Repository();

    public static Manager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Manager();
        }
        return INSTANCE;
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
        FileReader XMLfilereader = new XMLFileReader();
        FileReader YAMLfilereader = new YAMLFileReader();
        FileReader JSONfilereader = new JSONFileReader();
        XMLfilereader.setNextFileReader(YAMLfilereader);
        YAMLfilereader.setNextFileReader(JSONfilereader);
        XMLfilereader.read(file);
    }

}
