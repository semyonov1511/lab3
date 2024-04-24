
package FileReaders;

import Interface.Reactor;
import java.io.File;
import java.util.ArrayList;

public abstract class FileReader {
    
    protected FileReader nextFileReader;
    
    public void setNextFileReader(FileReader nextFileReader){
        this.nextFileReader = nextFileReader;
    }
    
    public abstract ArrayList<Reactor> read(File file);
    
}
