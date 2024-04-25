
package FileReaders;

import Interface.Reactor;
import java.io.File;
import java.util.ArrayList;

public abstract class FileReader {
    
    public FileReader nextFileReader;
    
    public void setNextFileReader(FileReader nextFileReader){
        this.nextFileReader = nextFileReader;
    }
    
    public abstract void read(File file);
    
}
