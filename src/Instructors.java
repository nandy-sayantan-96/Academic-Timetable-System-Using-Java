import java.util.*;
/**
 *
 * @author Sayantan
 */
public class Instructors {
    public static final int maxSlots = 15;  
    private String name;
    private Courses[] expertise; 
    private int slots = 0; 
    private int[] dailySlot = {0,0,0,0,0};
    private int[][] dailyAllocation = {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0}};
    private int tempCount = 0;
    
    public int[] sortDays(){
        int[][] days = new int[2][5];
        for(int i=0;i<5;i++){
            days[0][i]= i;
            days[1][i] = dailySlot[i];
        }
        for(int i=0;i<4;i++){
            for(int j=i;j<5;j++){
                if(days[1][i]>days[1][j]){
                    int temp = days[1][i];
                    days[1][i] = days[1][j];
                    days[1][j] = temp;
                    temp = days[0][i];
                    days[0][i] = days[0][j];
                    days[0][j] = temp;
                }
            }
        }
        int[] temp = new int[5];
        for(int i=0;i<5;i++){
            temp[i] = days[0][i];
        }
        return temp;
    }
    
    
    public void details(){
        int sum = 0;
        System.out.print(this.name + "  ");
        for(int i=0;i<5;i++){
            System.out.print(dailySlot[i]+" ");
            sum+= dailySlot[i];
        }
        System.out.println(sum);
    }
    
    public void instructorDeAllocate(int day, int slot,int unit){
        this.dailySlot[day] -=  unit;
        for(int i=0;i<unit;i++){
            this.dailyAllocation[day][slot+i]=0;
        }
    }
    
    public boolean checkSlot(int day, int slot){
        if(dailyAllocation[day][slot]==0){
            return true;
        }
        else{
            return false;
        }
    }
    public int getTempCount(){
        return this.tempCount;
    }
    public void resetTempCount(){
        this.tempCount = 0;
    }
    public void setTempCount(int temp){
        this.tempCount += temp;
    }
    //constructor for the class Instructors
    public Instructors(String name, Courses[] expertiseArray){
        this.name = name;
        this.expertise = expertiseArray;
    }
    public Instructors(){
        this.name="";
        this.expertise=null;
    }
    public int getDailySlot(int i){
        return dailySlot[i];
    }
    
    public void updateDialySlot(int unit, int i, int slot){
        this.dailySlot[i] += unit;
        for(int j=0;j<unit;j++){
            this.dailyAllocation[i][slot+j] = 1;
        }
    }
    
    public String getName(){
        return this.name;
    }
    

    //getting theexpertise vector
    public Courses[] getExpertiseList(){
        return this.expertise;
    }
    
    
    public int getSlots(){
        return this.slots;
    }
    
    public void incrementSlots(int unit){
        this.slots += unit;
    }
    
    public void printDetails(){
        System.out.println(name + " : " + slots);
    }
    
    public void printDetails1(){
        for(int i=0;i<5;i++){
            for(int j=0;j<7;j++){
                System.out.print(dailyAllocation[i][j]);
            }
            System.out.println();
        }
    }
}
