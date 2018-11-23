/**
 *
 * @author Puskar
 */
public class Timeslots {
    private String day;
    private int slot;
    private String start;
    private String end;
    public void setDay(String day){
    	this.day=day;
    }
    public void setSlot(int slot){
    	this.slot=slot;
    }
    public void setStart(String start){
    	this.start=start;
    }
    public void setEnd(String end){
    	this.end=end;
    }
    public String getDay(){
    	return this.day;
    }
    public int getSlot(){
    	return this.slot;
    }
    public String getStart(){
    	return this.start;
    }
    public String getEnd(){
    	return this.end;
    }
}
