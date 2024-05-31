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

    ExcelHandler ehandler = new ExcelHandler();
    Repository repository = new Repository();
    FileReader XMLfilereader = new XMLFileReader();
    SQLhandler handler = new SQLhandler();

    public void read() {
        if (!repository.getList().isEmpty()) {
            repository.setDBList(handler.readDataBase(repository.getList()));
            handler.calculateFuelLoad(repository.getDBList());
        } else {
            System.out.println("Сначала импортируйте информацию о реакторах");
        }
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

    public void calculate(ArrayList<DBReactor> reactors) {
        handler.calculateFuelLoad(repository.getDBList());
    }

    public void export(int i) {
        if (!repository.getDBList().isEmpty()) {
            switch (i) {
                case 1 ->
                    ehandler.createTable("Оператор", handler.link(repository.getDBList(), DBReactor::getOperator));
                case 2 ->
                    ehandler.createTable("Страна", handler.link(repository.getDBList(), DBReactor::getCountry));
                case 3 ->
                    ehandler.createTable("Регион", handler.link(repository.getDBList(), DBReactor::getRegion));
            }
        }
        else{
            System.out.println("Сначала импортируйте информацию о загрузке");
        }
    }

}
