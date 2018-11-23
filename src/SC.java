/**
 *
 * @author Sayantan
 */
import java.util.*;
public class SC {
    private int yr;
    private Courses[] courses = null;
    private Vector coursesVector = new Vector();
    
    /*public int test(){
        return this.coursesVector.size();
    }*/
    
    public SC(int year){
        yr = year;
    }
    
    public int getYear(){
        return yr;
    }
    
    public void addCourses(Courses course){
        this.coursesVector.addElement(course);
    }
    
    public void setCoursesArray(){
        this.courses = new Courses[coursesVector.size()];
        this.coursesVector.copyInto(courses);
    }
    
    public Courses[] getCourses(){
        return this.courses;
    }
    
    public void details(){
        int count = courses.length;
        System.out.print("YEAR : " + yr + " COURSES : ");
        for(int i=0;i<count;i++){
            System.out.print(courses[i].getCode() + " ");
        }
    }
    
}
