/**
 *
 * @author Sayantan
 */
public class Sections {
    private int year;
    private char section;
    private String labSection;
    private Sections[] labSec;
    private String sectionName;
    private int enrolment;
    private Courses[] yearCourses;
    private int[] courseSlots;
    private int[] allocated;
    private CI[] sectionInstructors;
    //private int[] labSlots = {0,4};
    private int[][] slotAllocationTable = {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0}};
    private SCIR[][] allocationTable = new SCIR[5][7]; 
    private int numLabCourses;
    private int[] courseSequence=null;
    private int[] ySequence = null;
    private int[] daySequence=null;
    private Instructors[] instructorSequence=null;
    private int[] slots=null;
    private Rooms[] roomSequence=null;
    private int[] roomCount = null;
    private int count=-1;
    private Period p[];
    
    public int getLabRoomCount(int j){
        return this.sectionInstructors[j].getLabRoomCount();
    }
    public int getLectureInstructorSlots(int j,Instructors ins){
        return this.sectionInstructors[j].lectureInstructorSlots(ins);
    }
    
    public Instructors getLectureInstructor(int j){
        return this.sectionInstructors[j].getLectureInstructor();
    }
    
    public Instructors getLabInstructor(int j, int day){
        return this.sectionInstructors[j].getLabInstructor(day);
    }
    
    public int getCount(){
        return count;
    }
    public void sortInstructors(int j){
        this.sectionInstructors[j].sortInstructors();
    }
    public void deAllocated(){
        if(count>=0){
            if(instructorSequence[count]!=null)
                instructorSequence[count].instructorDeAllocate(daySequence[count], slots[count], 3);
            if(courseSequence[count]>=0){
                this.allocated[courseSequence[count]] += 3;
            }
            for(int i=0;i<3;i++){
                if(daySequence[count]<0 || (slots[count]+i)<0)
                    continue;
                this.slotAllocationTable[daySequence[count]][slots[count]+i] = 0;
                this.allocationTable[daySequence[count]][slots[count]+i] = null;
            }
            if(instructorSequence[count]!=null)
                this.sectionInstructors[courseSequence[count]].deallocateInstructor(instructorSequence[count]);
            if(roomSequence[count]!=null)
                roomSequence[count].roomDeAllocate(daySequence[count], slots[count], 3);
            /*
            ySequence[count] = -1;
            daySequence[count] = -1;
            slots[count] = -1;
            courseSequence[count] = -1;
            roomCount[count] = -1;
            */
            count--;
        }
    }
    /*
    public int getIndex2(){
        return course2Sequence[count];
    }
    */
    public void courseDeAllocation(int index2){
        this.allocated[index2] = 3;
    }
    /*
    public int getPrevCourse2(int j){
            return course2Sequence[j];
    }
    */
    public int getPrevY(int j){
            return ySequence[j];
    }
    /*
    public int getPrevCourse1(int j){
            return course2Sequence[j];
    }
    */
    public boolean getPrevLecture(int day, int slot, int j){
        if(slot==0){
            return true;
        }
        else if(this.slotAllocationTable[day][slot-1]==0){
            return true;
        }
        else{
            if(this.allocationTable[day][slot-1].getCourse().equals(this.yearCourses[j])){
                return false;
            }
        }
        return false;
    }
    
    public void setSequenceArray(int index, int day, int y,Instructors ins, int slot, Rooms room, int roomCount){
        count++;
        this.courseSequence[count] = index;
        this.ySequence[count] = y;
        this.daySequence[count] = day;
        this.instructorSequence[count] = ins;
        this.slots[count] = slot;
        this.roomSequence[count] = room;
        this.roomCount[count] = roomCount;
    }
    public void setPrevDay(int j){
        daySequence[j] = -1;
    }
    public void setPrevSlot(int j){
        slots[j] = -1;
    }
    public void setPrevRoom(int j){
        roomCount[j] = -1;
    }
    public void setPrevCourse(int j){
        courseSequence[j] = -1 ;
    }
    
    public int getPrevDay(int j){
        return daySequence[j];
    }
    public int getPrevSlot(int j){
        return slots[j];
    }
    public int getPrevRoom(int j){
        return roomCount[j];
    }
    public int getPrevCourse(int j){
        return courseSequence[j];
    }
    /*
    public int getPrevRoom2(int j){
        return room2Count[j];
    }
    */
    public boolean getCourseAllocatedStatus(int j){
        if(this.allocated[j]!=0)
            return true;
        return false;
    }
    
    public void updateInstructorsSlots(int j, Instructors instructor, int unit){
        this.sectionInstructors[j].updateInstructors(instructor, unit);
    }
    
    public boolean checkPrevCourse(int j, int day, int slot){
       if(slot==0 || slot==0){
           return true;
       } 
       if(slotAllocationTable[day][slot-1]==0){
           return true;
       }
       if(allocationTable[day][slot-1].getCourse().equals(yearCourses[j])){
           return false;
       }
       return true;
    }
    
    public Instructors selectLectureInstructor(int j, int day){
        return this.sectionInstructors[j].selectLectureInstructor(day);
    }
    
    public CI[] getSectionInstructors(){
        return sectionInstructors;
    }
    public int getAllocatedStatus(int day, int slot){
        if(this.slotAllocationTable[day][slot]==0)  
            return 0;
        else
            return 1;
    }
    public void updateAllocatedStatus(int j, int day, int slot, int unit){
        this.allocated[j] -= unit;
        for(int i=slot,temp=0;temp<unit;i++,temp++){
            this.slotAllocationTable[day][i] = 1;
        }
    } 
    
    public void printAllocationTable(){
        for(int i=0;i<5;i++){
            for(int j=0;j<7;j++){
                System.out.print(slotAllocationTable[i][j]);
            }
            System.out.println();
        }
        for(int i=0;i<yearCourses.length;i++){
            System.out.print(allocated[i]);
        }
        System.out.println();
    }
    
    public String getlabSection(){
        return this.labSection;
    }
    public Rooms getLectureRoom(int j){
        return this.sectionInstructors[j].getLectureRoom();
    }

    public Instructors selectInstructor(int day, int j,int unit){
        return sectionInstructors[j].selectInstructor(day,unit);
    }
    public void testPrint(int j){
        this.sectionInstructors[j].testPrint();
    }
    public SCIR getSCIR(int i, int j){
        if(allocationTable[i][j]!=null){
            return allocationTable[i][j];
        }
        else
            return null;
    }
    
    public Rooms getRoom(int day, int slot, int unit,int j,int temp){
        return sectionInstructors[j].selectRoom(enrolment,day,slot,unit,temp);
    }
    
    public boolean getRoomDay(int day, int unit,int j){
        return sectionInstructors[j].selectRoomDay(enrolment,day,unit);
    }
    
    public void setSCIR(int day, int slot, int j, Instructors instructor, Rooms room,int unit){
        SCIR obj = new SCIR();
        obj.setCourse(yearCourses[j]);
        obj.setInstructor(instructor);
        obj.setRoom(room);
        obj.setSection(this);
        for(int i=0;i<unit;i++){
            allocationTable[day][slot+i] = obj;
        }
    }
    
    public void setNumLabCourses(int n){
        this.numLabCourses = n;
        this.courseSequence = new int[n];
        this.daySequence = new int[n];
        this.ySequence = new int[n];
        this.instructorSequence = new Instructors[n];
        this.roomSequence = new Rooms[n];
        this.roomCount = new int[n];
        this.slots = new int[n];        
        for(int i=0;i<n;i++){
            courseSequence[i]=-1;
            roomCount[i] = -1;
            daySequence[i]= -1;
            slots[i]=-1;
            ySequence[i] = -1;
        }
    }
    /*
    public int getLabSlot(int i){
        return this.labSlots[i];
    }
    */
    public int getAllocated(int i){
        return this.allocated[i];
    }
    public int getEnrolment(){
        return this.enrolment;
    }
    //set instructors for the courses
    public void setSectionInstructos(CI obj, int i){
        this.sectionInstructors[i] = obj;
    }
    //set room for the lecture classes
    public void setRoom(Rooms room,int i){
        this.sectionInstructors[i].setRoom(room);
    }
    /**
    *set all possible rooms for the labClass purpose
    */
    public void setLabRoom(Rooms[] rooms,int i){
        this.sectionInstructors[i].setLabRooms(rooms);
    }
    /**
    *Constructor of this class
    */
     public Sections(){
        
    }
    public Sections(int year, String labSection, int enrolment){
        this.year = year;
        this.labSection = labSection;
        this.enrolment = enrolment;
    }
    public Sections(int year,String sectionName,Sections[] labSec,int enrol){
        this.year=year;
        this.sectionName=sectionName;
        this.labSec=labSec;
        this.enrolment=enrol;
    }
    /**
    *Returns year of this section
    */
    public void setPeriods(){
        int k=0;
        
        p=new Period[35];
        for(int i=0;i<35;i++)
            p[i]=new Period();          
        for(int i=0;i<5;i++){
            for(int j=0;j<7;j++){
                if(allocationTable[i][j]==null){
                    p[k]=(null);
                }
                else{
                    p[k].setCourse(allocationTable[i][j].getCourse());
                    p[k].setInstructors(allocationTable[i][j].getInstructor());
                    p[k].setRooms(allocationTable[i][j].getRoom());
                    p[k].setSection(allocationTable[i][j].getSection());
                }
                k++;
            }
        }
        //return this.p;
        //System.out.println("******************************************");
    }
    public Period[] getPeriod(){
        return this.p;
    }
    public int getYear(){
        return this.year;
    }
    /**
    *returns all courses for this section
    */
    public Courses[] getCourses(){
        return this.yearCourses;
    } 
    /**
    *Determines all Courses to be taught in this section and stores it in the array @yearCourses
    */
    public void setCourses(Courses[] courses){
        this.yearCourses = courses;
        this.sectionInstructors = new CI[courses.length];
        this.allocated = new int[yearCourses.length];
        for(int i=0;i<courses.length;i++){
            this.allocated[i]=courses[i].getContactSlots();
        }       
        this.courseSlots = new int[yearCourses.length];
        for(int i=0;i<yearCourses.length;i++){
            courseSlots[i] = yearCourses[i].getContactSlots();
        }
    }
    /**
     * Prints all Course Codes along with respective Instructors and Rooms for this Section
     */
    public void details(){
        
        System.out.println(year + " " + labSection + " ");
        for(int i=0;i<yearCourses.length;i++){
            if(yearCourses[i].getResourceType().equals("Lecture Room")){    
                System.out.println(yearCourses[i].getCode());
                sectionInstructors[i].lectureDetails();
                System.out.println();
            }    
        }
        for(int i=0;i<yearCourses.length;i++){
            if(yearCourses[i].getResourceType().equals("Lecture Room"))
                continue;
            System.out.println(yearCourses[i].getCode());
            sectionInstructors[i].labDetails();
            System.out.println();
            
        }
    }
    /**
     * Prints codes of all courses taught in this section
     */
    public void sectionCourseDetails(){
        System.out.print(year + " " + labSection + " ");
        for(int i=0;i<yearCourses.length;i++){
            System.out.print(yearCourses[i].getCode() + "  ");
        }  
    }
    /**
    *Prints the final allocation table of this section
    * Allocation table consists of CourseCode, Instructors Code, Day, Slot, Room
    */
    /*
    public void printFinalDetails(Timeslots[] tArr){
        DataManager dm = new DataManager();
        Timeslots[] time=tArr;
        System.out.println("Year = "+year + "Lab Section = "+labSection);
        System.out.println("******************************************");
        System.out.println("DAY\t"+"PERIOD\t"+"START\tEND\t"+"COURSE\t\t"+"INSTRUCTOR\t"+"ROOM");
        //String days[]={"Tue","Wed","Thu","Fri","Sat"};
        int k=0;
        for(int i=0;i<5 && k<time.length;i++){
            
            for(int j=0;j<7 && k<time.length;j++){
                //System.out.println(k);
                if(allocationTable[i][j]==null){
                    System.out.println(time[k].getDay()+"\t" + time[k].getSlot()+"\t"+time[k].getStart()+"\t"+time[k].getEnd()+"\t"+"FREE PERIOD  ");
                }
                else{
                    System.out.println(time[k].getDay()+"\t" + time[k].getSlot() +"\t"+time[k].getStart()+"\t"+time[k].getEnd()+"\t"+ allocationTable[i][j].getCourse().getCode()+"\t\t" +allocationTable[i][j].getInstructor().getName()+"\t\t"
                    + allocationTable[i][j].getRoom().getResource()+"\t");
                }
                k++;
            }
            k++;
        }
        System.out.println("******************************************");
    }   
    */
    public void printFinalDetails(){
        System.out.println("Year = "+year + "Lab Section = "+labSection);
        System.out.println("******************************************");
        for(int i=0;i<5;i++){
            for(int j=0;j<7;j++){
                if(allocationTable[i][j]==null){
                    System.out.println(i+" " + j+" "+"FREE PERIOD  ");
                }
                else{
                    System.out.println(i+" " + j+" "+ allocationTable[i][j].getCourse().getCode()+"  " +allocationTable[i][j].getInstructor().getName()+"  "
                    + allocationTable[i][j].getRoom().getResource()+"  ");
                }
            }
        }
        System.out.println("******************************************");
    }      
} 
