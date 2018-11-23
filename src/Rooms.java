/**
 *
 * @author Sayantan
 */
public class Rooms {
    private String resource;
    private String type;
    private int capacity;
    private int[][] roomAllocationTable = {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0}};
    
    public boolean getAllocation(int day, int slot,int unit){
        if(roomAllocationTable[day][slot]==0){
            for(int i=slot,t=0;t<unit;i++,t++){
                if(roomAllocationTable[day][i]!=0)
                    return false;
            }
            return true;
        }
        else
            return false;
    }
    public void printDailyDetails(){
        System.out.println("Room = "+resource);
        for(int i=0;i<5;i++){
            for(int j=0;j<7;j++){
                System.out.print(roomAllocationTable[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void roomDeAllocate(int day, int slot, int unit){
        for(int i=0;i<unit;i++){
            roomAllocationTable[day][slot+i] = 0;
        }
    }
    public boolean getAllocationDay(int day, int unit){
        if(roomAllocationTable[day][4]==0)
            return true;
        else if(roomAllocationTable[day][0]==0)
            return true;
        else
            return false;
    }
    
    public void updateAllocation(int day, int slot, int unit){
        for(int i=slot,temp=0; temp<unit; i++,temp++){
            roomAllocationTable[day][i]=1;
        }
    }
    public Rooms(){
        this.resource="";
        this.type="";
        this.capacity=0;
    }
    public Rooms(String resource, String type, int capacity){
        this.resource = resource;
        this.type = type;
        this.capacity = capacity;
    }
    
    
    public String getResource(){
    	return this.resource;
    }
    public String getType(){
    	return this.type;
    }
    public int getCapacity(){
    	return this.capacity;
    }
}
