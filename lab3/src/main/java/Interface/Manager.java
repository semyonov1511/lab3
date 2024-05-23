package Interface;

import DBrelated.SQLconnector;
import DBrelated.SQLhandler;
import FileReaders.FileReader;
import FileReaders.JSONFileReader;
import FileReaders.XMLFileReader;
import FileReaders.YAMLFileReader;
import ReactorsRelated.DBReactor;
import ReactorsRelated.Reactor;
import ReactorsRelated.Repository;
import java.io.File;
import java.util.ArrayList;

public class Manager {

    Repository repository = new Repository();
    FileReader XMLfilereader = new XMLFileReader();
    SQLconnector connector = new SQLconnector();
    SQLhandler reader = new SQLhandler();

    public void connect() {
        connector.setConnection();
    }

    public void read() {
        reader.readDataBase(repository.getList());
    }

    public Manager() {
        FileReader YAMLfilereader = new YAMLFileReader();
        FileReader JSONfilereader = new JSONFileReader();
        XMLfilereader.setNextFileReader(YAMLfilereader);
        YAMLfilereader.setNextFileReader(JSONfilereader);
    }

    public void setList(File file) {
        repository.setList(XMLfilereader.read(file));
        for (Reactor reactor : repository.getList()) {
            reactor.setParameters();
        }
    }

    public ArrayList<Reactor> getList() {
        return repository.getList();
    }

    public void calculateFuelLoad(ArrayList<DBReactor> reactors) {
        double fuelLoad;
        for (DBReactor reactor : reactors) {
            for (int year = 2014; year < 2025; year++) {
                fuelLoad = reactor.getLoadFactor().containsKey(year)
                        ? reactor.getThermalCapacity() * reactor.getLoadFactor().get(year) / 100 / reactor.getReactor().getBurnup()
                        : 0;
                reactor.getFuelLoad().put(year, fuelLoad);
            }
        }
    }
}
