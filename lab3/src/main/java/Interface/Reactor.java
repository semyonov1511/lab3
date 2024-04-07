package Interface;

public class Reactor {

    private String filetype;
    private String Class;
    private int burnup;
    private double kpd;
    private double enrichment;
    private int TCapacity;
    private int ECapacity;
    private int lifetime;
    private double firstload;

    public Reactor() {
    }
    
    public void setFiletype(String filetype){
        this.filetype = filetype;
    }
    public String getFiletype(){
        return this.filetype;
    }
    
    public void setClass(String Class){
        this.Class = Class;
    }
    public String getsetClass(){
        return this.Class;
    }
    
    public void setBurnup(int burnup){
        this.burnup = burnup;
    }
    public int getBurnup(){
        return this.burnup;
    }
    
    public void setKPD(double kpd){
        this.kpd = kpd;
    }
    public double getKPD(){
        return this.kpd;
    }
    
    public void setEnrichment(double enrichment){
        this.enrichment = enrichment;
    }
    public double getEnrichment(){
        return this.enrichment;
    }
    
    public void setTCapacity(int TCapacity){
        this.TCapacity = TCapacity;
    }
    public int getTCapacity(){
        return this.TCapacity;
    }
    
    public void setECapacity(int ECapacity){
        this.ECapacity = ECapacity;
    }
    public int getECapacity(){
        return this.ECapacity;
    }
    
    public void setLifetime(int lifetime){
        this.lifetime = lifetime;
    }
    public int getLifetime(){
        return this.lifetime;
    }
    
    public void setFirstload(double firstload){
        this.firstload = firstload;
    }
    public double getFirstload(){
        return this.firstload;
    }
    
}
