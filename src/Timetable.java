
/**
 *
 * @author Puskar
 */
public class Timetable {
    private Period p1[];
    private Period p2[];
    private int c=0;
    private Period arr[]=new Period[300];  
    public void getTimetable(Sections sArr[],Timeslots tArr[]){
        
        for(int i=0;i<300;i++){
            //for(int k=0;k<10;k++)
                arr[i]=new Period();
         }
        for(int j=0,t=1;j<sArr.length;j+=2,t+=2){
//            System.out.println(sArr[j].getYear()+"   "+sArr[j].getlabSection().charAt(0));
            sArr[j].setPeriods();
            p1=sArr[j].getPeriod();
            sArr[t].setPeriods();
            p2=sArr[t].getPeriod();          
            for(int i=0,k=0;i<p1.length && k<p2.length;i++,k++){
                if(p1[i]==p2[k] && p1[i]==null){
//                    System.out.println(tArr[i].getDay()+" "+tArr[i].getSlot()+" "+"FREE");
                    arr[c].setSection(sArr[j]);
                    arr[c].setTimeslots(tArr[i]);
                    arr[c].setCourse(null);
                    arr[c].setInstructors(null);
                    arr[c].setRooms(null);
                    arr[c].setLSection("");
                    c++;
                }
                else if(p1[i].getCourse().getCourseType().equals(p2[k].getCourse().getCourseType()) && p1[i].getCourse().getCourseType().equals("Lecture")){
//                    System.out.println(tArr[i].getDay()+" "+tArr[i].getSlot()+" "+p1[i].getCourse().getCode()+" "+p1[i].getInstructors().getName()+" "+p1[i].getRooms().getResource());
                    arr[c].setSection(sArr[j]);
                    arr[c].setTimeslots(tArr[i]);
                    arr[c].setCourse(p1[i].getCourse());
                    arr[c].setInstructors(p1[i].getInstructors());
                    arr[c].setRooms(p1[i].getRooms());
                    arr[c].setLSection("");
                    c++;
                }
                else{
//                    System.out.println(tArr[i].getDay()+" "+tArr[i].getSlot()+" "+p1[i].getCourse().getCode()+" "+p1[i].getInstructors().getName()+" "+p1[i].getRooms().getResource()+" "+sArr[j].getlabSection());
//                    System.out.println(tArr[k].getDay()+" "+tArr[k].getSlot()+" "+p2[k].getCourse().getCode()+" "+p2[k].getInstructors().getName()+" "+p2[k].getRooms().getResource()+" "+sArr[t].getlabSection());
                    arr[c].setSection(sArr[j]);
                    arr[c].setTimeslots(tArr[i]);
                    arr[c].setCourse(p1[i].getCourse());
                    arr[c].setInstructors(p1[i].getInstructors());
                    arr[c].setRooms(p1[i].getRooms());
                    arr[c].setLSection(sArr[j].getlabSection());
                    c++;
                    arr[c].setSection(sArr[t]);
                    arr[c].setTimeslots(tArr[k]);
                    arr[c].setCourse(p2[k].getCourse());
                    arr[c].setInstructors(p2[k].getInstructors());
                    arr[c].setRooms(p2[k].getRooms());
                    arr[c].setLSection(sArr[t].getlabSection());
                    c++;
                }
                //
            }
            //System.out.println(c);
           
//            for(int i=0;i<c;i++){
//                if(arr[i][1].getCourse()==null)
//                    System.out.println(arr[i][0].getSlot().getDay()+" "+arr[i][0].getSlot().getStart()+" "+arr[i][0].getSlot().getEnd()+" FREE");
//                else
//                    System.out.println(arr[i][0].getSlot().getDay()+" "+arr[i][0].getSlot().getStart()+" "+arr[i][0].getSlot().getEnd()+" "+arr[i][1].getCourse().getCode()+" "+arr[i][2].getInstructor().getName()+" "+arr[i][3].getRoom().getResource()+" "+arr[i][4].getLSection());
//            }
        }
    }
    public Period[] getTimetable(){
        return this.arr;
    }
    public int lengthArr(){
        return this.c;
    }
    public void print(){
       
        for(int i=0;i<this.c;i++){
            if(arr[i].getCourse()==null)
                System.out.println(arr[i].getSection().getYear()+" "+arr[i].getSection().getlabSection().charAt(0)+" "+arr[i].getTimeslots().getDay()+" "+arr[i].getTimeslots().getStart()+" "+arr[i].getTimeslots().getEnd()+" FREE");
            else
                System.out.println(arr[i].getSection().getYear()+" "+arr[i].getSection().getlabSection().charAt(0)+" "+arr[i].getTimeslots().getDay()+" "+arr[i].getTimeslots().getStart()+" "+arr[i].getTimeslots().getEnd()+" "+arr[i].getCourse().getCode()+" "+arr[i].getInstructors().getName()+" "+arr[i].getRooms().getResource()+" "+arr[i].getLSection());
        }
    }
}