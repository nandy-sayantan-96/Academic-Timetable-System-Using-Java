/**
 *
 * @author Sayantan
 */
public class LectureStack {
    //private Instructors instructors[];
    private int[] daySequence;
    private int[] slotSequence;
    private Instructors[] insSequence;
    private int[] instructorsIndexSequence;
    private int slotsRemaining;
    private int count = -1;
    
    public int getCount(){
        return count;
    }
    public Instructors getInstructor(int c){
        return insSequence[c];
    }
    public int getDay(int c){
        return daySequence[c];
    }
    public int getSlot(int c){
        return slotSequence[c];
    }
    public void decrementCounter(){
        count--;
        System.out.println("Count = " + count);
    }
    public void setObject(int day, int slot, Instructors ins){
        count++;
        System.out.println("Count = " + count);
        daySequence[count] = day;
        slotSequence[count] = slot;
        insSequence[count] = ins;
    }
    public int getRemainingLectureSlots(){
        return slotsRemaining;
    }
    public void setRemainingLectureSlots(int val){
        slotsRemaining += val;
    }
    public int getLastLectureDay(){
        if (count==-1)
            return -1;
        else
            return daySequence[count];
    }
    public int getLastInsIndex(){
        if (count==-1)
            return -1;
        else
            return instructorsIndexSequence[count];
    }
    public int getLastLectureSlot(){
        if (count==-1)
            return -1;
        else
            return slotSequence[count];
    }
    public void initialize(int n){
        //System.out.println("N = " + n);
        daySequence = new int[n];
        slotSequence = new int[n];
        insSequence = new Instructors[n];
        instructorsIndexSequence = new int[n];
        slotsRemaining = n;
    }
}
