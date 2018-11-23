/**
 *
 * @author Sayantan
 */
import java.util.*;
import java.lang.Math.*;
public class Courses {
    private String code;
    private String name;
    private int contactSlots;
    private String courseType;
    private String resourceType;
    /**This variable is used to store how many continuous slots is to be provided for this course */
    private int unit; 
    private Courses association = null;
    private Instructors[] expertInstructors=null;
    private int[] expertInstructorsSlots=null;
    private Instructors[] associatedInstructors=null;
    private int[] associatedInstructorsSlots=null;
    private Instructors[] notExpertInstructors=null;
    private int[] notExpertInstructorsSlots=null;
    private int totalSlots;
    private Instructors[] allInstructors;
    private int[] allInstructorsSlots;
    
    public String getResourceType(){
        return this.resourceType;
    }
    
    public int getUnit(){
        return this.unit;
    }
    
    public void updateSlots(int unit, int i){
        if(allInstructorsSlots!=null){
            allInstructorsSlots[i] -= unit; 
        }
    }
    
    public Instructors[] getAllInstructors(){
        return this.allInstructors;
    }
    public int[] getAllInstructosSlots(){
        return this.allInstructorsSlots;
    }
    public int getTotalSlots(){
        return this.totalSlots;
    }
    public void updateTotalSlots(int slotsRequired){
        this.totalSlots = slotsRequired;
    }
    public void setExpertInstructors(Instructors[] expertInstructors){
        this.expertInstructors = expertInstructors;
    }
    
    public void setExpertInstructorsSlots(int[] expertInstructorsSlots){
        this.expertInstructorsSlots = expertInstructorsSlots;
    }
    
    
    public void setAssociatedInstructors(Instructors[] associatedInstructors){
        this.associatedInstructors = associatedInstructors;
    }
    public void setAssociation(Courses partner){
        association = partner;
    }
    public void setAssociatedInstructorsSlots(int[] associatedInstructorsSlots){
        this.associatedInstructorsSlots = associatedInstructorsSlots;
    }
    
    public void setNotExpertInstrutors(Instructors[] notExpertList){
        int len = notExpertList.length;
        this.notExpertInstructors = notExpertList;
        notExpertInstructorsSlots = new int[len];
        //int unit = (courseType.equals("Lecture"))?1:contactSlots;
        for(int i=0;i<len;i++){
            notExpertInstructorsSlots[i] = notExpertInstructors[i].getTempCount();
            notExpertInstructors[i].resetTempCount();
        }
    }
    
    
    //constructor of the class Courses where the course is associated with another course
    public Courses(String code, String name, int contactSlots, String courseType, String resourceType, Courses association){
        this.code = code;
        this.name = name;
        this.contactSlots = contactSlots;
        this.courseType = courseType;
        this.resourceType = resourceType;
        this.association = association;
        totalSlots = (courseType.equals("Lecture"))?(contactSlots*3):(contactSlots*6);
        unit = this.courseType.equals("Lecture")?1:this.contactSlots;
    }
    
    //constructor of the class Courses where the course is not associated with any other course
    public Courses(String code, String name, int contactSlots, String courseType, String resourceType){
        this.code = code;
        this.name = name;
        this.contactSlots = contactSlots;
        this.courseType = courseType;
        this.resourceType = resourceType;
        totalSlots = (courseType.equals("Lecture"))?(contactSlots*3):(contactSlots*6);
        unit = this.courseType.equals("Lecture")?1:this.contactSlots;
    }
    public Courses(){
        this.code="";
        this.name="";
        this.contactSlots=0;
        this.courseType="";
        this.resourceType="";
    }
    public String getCourseType(){
        return courseType;
    }
    
    public int getContactSlots(){
        return contactSlots;
    }
    
    public String getCode(){
        return code;
    }
    
    public Courses getAssociation(){
        return association;
    }
     public String getName(){
        return this.name;
    }  
    public void printDetails(){
        System.out.print(code + " : ");       
        for(int i=0;i<this.allInstructors.length;i++){
            System.out.print(allInstructors[i].getName() + " -> " + allInstructorsSlots[i] + " ");
        }
        System.out.println();
    }
    
    public void courseInstructors(){
        int temp = 0;
        if(this.associatedInstructors!=null)
            temp += this.associatedInstructors.length;
        if(this.expertInstructors!=null)
            temp+= this.expertInstructors.length;
        if(this.notExpertInstructors!=null)
            temp += this.notExpertInstructors.length;
        this.allInstructors = new Instructors[temp];
        this.allInstructorsSlots = new int[temp];
        temp=0;
        if(this.associatedInstructors!=null){
            for(int i=0;i<this.associatedInstructors.length;i++){
                this.allInstructors[temp] = this.associatedInstructors[i];
                this.allInstructorsSlots[temp] = this.associatedInstructorsSlots[i];
                temp++;
            }
        }
        if(this.expertInstructors!=null){
            for(int i=0;i<this.expertInstructors.length;i++){
                this.allInstructors[temp] = this.expertInstructors[i];
                this.allInstructorsSlots[temp] = this.expertInstructorsSlots[i];
                temp++;
            }
        }
        if(this.notExpertInstructors!=null){
            for(int i=0;i<this.notExpertInstructors.length;i++){
                this.allInstructors[temp] = this.notExpertInstructors[i];
                this.allInstructorsSlots[temp] = this.notExpertInstructorsSlots[i];
                temp++;
            }
        }
    }
    
    public int getYear(){
        int i=0;
        int temp = 0;
        while(i<this.code.length()){
            temp = (int)this.code.charAt(i);
            if(temp>=48 && temp<=57)
                break;
            i++;
        }
        temp -= 48;
        temp = (int)Math.ceil(temp/2.0);
        return temp;
    }
    public void setCode(String code){
        this.code=code;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setContactSlots(int contactSlots){
        this.contactSlots=contactSlots;
    }
    public void setCourseType(String courseType){
        this.courseType=courseType;
    }
    public void setResourceType(String resourceType){
        this.resourceType=resourceType;
    }
    
}

