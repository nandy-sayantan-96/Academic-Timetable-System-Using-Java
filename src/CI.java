/**
 *
 * @author Sayantan
 */
import java.util.*;
public class CI {
    private Instructors[] instructors;
    private int[] instructorsSlots;
    private Rooms lectureRoom;
    private Rooms[] labRooms = null;
    private static int count = 0;
    private int[] instructorsAllocationSequence;
    private int[] roomsAllocationSequence;
    private int counterRoom = 0;
    private int lectureInstructorCounter = 0;
    private int counterInstructor = 0;
    
    public int lectureInstructorSlots(Instructors ins){
        for(int i=0;i<instructors.length;i++){
            if(instructors[i].equals(ins)){
                int temp = instructorsSlots[i];
                instructorsSlots[i] = 0;
                return temp;
            }
        }
        return 0;
    }
    
    public void sortInstructors(){
        int len = instructors.length;
        for(int i=0;i<len-1;i++){
            for(int j=i;j<len;j++){
                if(instructors[i].getSlots()<instructors[j].getSlots()){
                    Instructors temp = instructors[i];
                    instructors[i] = instructors[j];
                    instructors[j] = temp;
                }
            }
        }
        /*
        //temporary test printing
        for(int i=0;i<len;i++){
            System.out.print(instructors[i].getName() + "  ");
        }
        System.out.println();
        */
    }
    
    public Instructors getLectureInstructor(){
        for(int i=0;i<instructors.length;i++){
            if(instructorsSlots[i]!=0){
                return instructors[i];
            }
        }
        return null;
    }
    
    public Instructors getLabInstructor(int day){
        for(int i=0;i<instructors.length;i++){
            if(instructorsSlots[i]!=0 && (instructors[i].getDailySlot(day)+3)<5){
                return this.instructors[i];
            }
        }
        return null;
    }
    
    public Rooms getLectureRoom(){
        return this.lectureRoom;
    }

    public void deallocateInstructor(Instructors ins){
        for(int i=0;i<instructors.length;i++){
            if(ins.equals(instructors[i])){
                this.instructorsSlots[i] += 3;
            }
        }
    }
    
    public void testPrint(){
        System.out.print("Instructor allocation sequence  :   ");
        for(int i=0;i<counterInstructor;i++){
            System.out.print(instructorsAllocationSequence[i] + " ");
        }
    }
    public boolean deallocateRoom(){
        //deallocate everything related to yearCourses[courseSequenceCount]
        counterRoom--;
        if(counterRoom<0){
            counterRoom =0;
            return false;
        }
        else{
            return true;
        }
            
    }
    public Instructors selectLectureInstructor(int day){
        for(int i=0;i<instructors.length;i++){
            if(instructorsSlots[i]!=0){
                if(instructors[i].getDailySlot(day)<5){
                    return instructors[i];
                }
            }
        }
        return null;
    }
    
    public void updateInstructors(Instructors instructor, int unit){
        for(int i=0;i<instructors.length;i++){
            if(instructors[i].getName().equals(instructor.getName())){
                instructorsSlots[i] -= unit;
                break;
            }
                
        }
    }
    public int getLabRoomCount(){
        if(labRooms ==null){
            return 1;
        }
        return labRooms.length;
    }
    public Rooms selectRoom(int enrolment, int day, int slot, int unit, int temp){
        if(this.labRooms==null)
            return this.lectureRoom;
        if(temp>(labRooms.length-1))
            return null;
        if(labRooms[temp].getCapacity()>=enrolment){
            if(labRooms[temp].getAllocation(day, slot, unit)){                 
                return labRooms[temp];
            }
        }
        return null;
    }
    
    public boolean selectRoomDay(int enrolment, int day, int unit){
        if(this.labRooms==null)
            return true;
        for (Rooms labRoom : labRooms) {
            if (labRoom.getCapacity() >= enrolment) {
                if (labRoom.getAllocationDay(day, unit)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Instructors selectInstructor(int day, int unit){
        for(int i=0;i<instructors.length;i++){
            if(instructorsSlots[i]!=0){
                if(instructors[i].getDailySlot(day)+unit <=5){
                    return instructors[i];
                }
            }    
        }
        return null;   
    }
    
    public void setLabRooms(Rooms[] tempRooms){
        this.labRooms = tempRooms;
        this.roomsAllocationSequence = new int[tempRooms.length];
        for(int i=0;i<tempRooms.length;i++){
            this.roomsAllocationSequence[i] = -1;
        }
    }
    
    public void lectureDetails(){
        for(int i=0;i<instructors.length;i++){
            if(instructorsSlots[i]!=0)
                System.out.print(instructors[i].getName() + " " + instructorsSlots[i] + "  ");
        }
        if(lectureRoom!=null)
            System.out.print(lectureRoom.getResource()+" ");
    }
    
    public void labDetails(){
        for(int i=0;i<instructors.length;i++){
            if(instructorsSlots[i]!=0)
                System.out.print(instructors[i].getName() + " " + instructorsSlots[i] + "  ");
        }
        if(labRooms!=null){
            for(int i=0;i<labRooms.length;i++){
                System.out.print(labRooms[i].getResource() + "  ");
            }
        }
    }
    
    public CI(Instructors[] instructors){
        this.instructors = instructors;
        //this.instructorsAllocationSequence = new int[instructors.length];
        if(this.instructors!=null){
            this.instructorsSlots = new int[instructors.length];
            for(int i=0;i<instructors.length;i++){
                instructorsSlots[i]=0;
            }   
        }
        this.instructorsAllocationSequence=new int[instructors.length];
        for(int i=0;i<instructors.length;i++){
            this.instructorsAllocationSequence[i] = -1;
        }
    }
    
    public void setInstructorsSlots(int unit, int i){
        instructorsSlots[i] += unit;
    }
    
    public void setRoom(Rooms room){
        this.lectureRoom = room;
    }
}
