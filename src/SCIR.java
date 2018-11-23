/**
 *
 * @author Sayantan
 */
public class SCIR {
    private Sections section;
    private Rooms room;
    private Courses course;
    private Instructors instructor;
    private Timeslots timeslots;
    private String s;
    public void setSection(Sections section){
        this.section = section;
    }
    
    public void setRoom(Rooms room){
        this.room = room;
    }
     
    public void setCourse(Courses course){
        this.course = course;
    }
    public void setTimeslots(Timeslots timeslots){
        this.timeslots=timeslots;
    }
    public void setInstructor(Instructors instructor){
        this.instructor = instructor;
    }
    
    public Sections getSection(){
        return this.section;
    }
    
    public Rooms getRoom(){
        return this.room;
    }
    
    public Courses getCourse(){
        return this.course;
    }
    public Timeslots getSlot(){
        return this.timeslots;
    }
    public Instructors getInstructor(){
        return this.instructor;
    }
}
