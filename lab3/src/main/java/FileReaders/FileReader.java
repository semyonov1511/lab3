package FileReaders;

import java.io.File;

public abstract class FileReader {
    
    public FileReader nextFileReader;
    
    public void setNextFileReader(FileReader nextFileReader){
        this.nextFileReader = nextFileReader;
    }
    
    public abstract void read(File file);
    
}
