/**
 *
 * @author Puskar
 */
public class Period {
    //private int year;
    private Sections sec;
    private Instructors inst;
    private Courses course;
    private Timeslots slots;
    private Rooms room;
    private String s;
    public void setSection(Sections sec){
        this.sec=sec;
    }
    public void setInstructors(Instructors inst){
        this.inst=inst;
    }
    public void setCourse(Courses course){
        this.course=course;
    }
    public void setTimeslots(Timeslots slots){
        this.slots=slots;
    }
    public void setRooms(Rooms room){
        this.room=room;
    }
    public Sections getSection(){
        return this.sec;
    }
    public Instructors getInstructors(){
        return this.inst;
    }
    public Courses getCourse(){
        return this.course;
    }
    public Timeslots getTimeslots(){
        return this.slots;
    }
    public Rooms getRooms(){
        return this.room;
    }
    public void setLSection(String s){
        this.s=s;
    }
    public String getLSection(){
        return this.s;
    }
   
}

