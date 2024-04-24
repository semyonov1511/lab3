
package FileReaders;

import Interface.Reactor;
import java.util.ArrayList;

public abstract class FileReader {
    private String type;
    private FileReader nextFileReader;
    
    public FileReader(String type){
        this.type = type;
    }
    
    public void setNextFileReader(FileReader nextFileReader){
        this.nextFileReader = nextFileReader;
    }
    
    public void FileReaderManager(){
        if (nextFileReader != null){
            nextFileReader.FileReaderManager();
        }
    }
    
    public abstract ArrayList<Reactor> read(String filename);
}
