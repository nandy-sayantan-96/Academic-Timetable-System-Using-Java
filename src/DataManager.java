import java.io.*;
import java.util.*;
/**
 *
 * @author Puskar
 */
public class DataManager {
    private Courses courseArr[];
    private Instructors instructorArr[];
    private Sections sectionArr[];
    private Rooms roomArr[];
    private Timeslots timeslotArr[];
    public int getFileLines(String fname){
    	BufferedReader br=null;
    	int n=0;
    	try{
       	    String line;
    	    br=new BufferedReader(new FileReader(fname));
    	    while((line=br.readLine())!=null)
    		n++;
    	}
    	catch(FileNotFoundException e){
      	    System.out.println("The filename you entered does not exist!");
    	}
        catch(IOException e){
            
        }
    	finally{
    	    try{
    		br.close();
    	    }
    	    catch(Exception e){
    		e.printStackTrace();
    	    }
    	}
    	return n-1;
    }
    public Courses[] readCourses(String fname){
        BufferedReader br=null;
        int n=getFileLines(fname);
        courseArr=new Courses[n];
        try{
            String line,sc;
            int j=0;
            br=new BufferedReader(new FileReader(fname));
            line=br.readLine();
            HashMap<String,String> temp=new HashMap<>();
            for(int i=0;i<n;i++)
                courseArr[i]=new Courses();
            while((line=br.readLine())!=null){
                //String arr[]=line.split(",");
                StringTokenizer st=new StringTokenizer(line,",");
                sc=st.nextToken();
                courseArr[j]=new Courses(sc,st.nextToken(),Integer.parseInt(st.nextToken()),st.nextToken(),st.nextToken());
//                courseArr[j].setName();
//                courseArr[j].setContactSlots();
//                courseArr[j].setCourseType();
//                courseArr[j].setResourceType();
                if(st.hasMoreElements())
                    temp.put(sc,st.nextToken());
                j=j+1;
            }
            for(int i=0;i<n;i++){
                Courses partner=new Courses();
                if(temp.containsKey(courseArr[i].getCode())){
                    partner.setCode(temp.get(courseArr[i].getCode()));
                    for(int k=0;k<n;k++){
                        if((temp.get(courseArr[i].getCode())).equals(courseArr[k].getCode())){
                            partner.setName(courseArr[k].getName());
                            partner.setContactSlots(courseArr[k].getContactSlots());
                            partner.setResourceType(courseArr[k].getResourceType());
                        }
                    }
                }
                courseArr[i].setAssociation(partner);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("The filename you entered does not exist!");
        }
        catch(IOException e){
            Arrays.fill(courseArr, null);
        }
        finally{
            try{
                br.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return courseArr;
    }
    public Instructors[] readInstructors(String fname,Courses a[]){
        BufferedReader br=null;
        int n=getFileLines(fname);
        instructorArr=new Instructors[n];
        try{
            br=new BufferedReader(new FileReader(fname));
            String line=br.readLine();
            int j=0;
            for(int i=0;i<n;i++)
                instructorArr[i]=new Instructors();
            while((line=br.readLine())!=null){
                //String arr[]=line.split(",");
                StringTokenizer arr=new StringTokenizer(line,",");
                Courses expertise[]=new Courses[arr.countTokens()-1];
                for(int i=0;i<arr.countTokens()-1;i++)
                    expertise[i]=new Courses();
                String name=arr.nextToken();
                //instructorArr[j].setName(arr.nextToken());
                int i=0;
                while(arr.hasMoreElements()){
                    expertise[i].setCode(arr.nextToken());
                    i++;
                }
                for(i=1;i<arr.countTokens();i++){
                    for(int t=0;t<a.length;t++){
                        if((expertise[i-1].getCode()).equals(a[t].getCode())){
                            expertise[i-1].setName(a[t].getName());
                            expertise[i-1].setCourseType(a[t].getCourseType());
                            expertise[i-1].setContactSlots(a[t].getContactSlots());
                            expertise[i-1].setAssociation(a[t].getAssociation());    
                            expertise[i-1].setResourceType(a[t].getResourceType());
                        }
                    }
                }
                instructorArr[j]=new Instructors(name,expertise);
                j++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("The filename you entered does not exist!");
        }
        catch(IOException e){
            Arrays.fill(instructorArr,null);
        }
        finally{
            try{
                br.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return instructorArr;
    }
    public Sections[] readSections(String fname){
        BufferedReader br=null;
        int n=getFileLines(fname);
        sectionArr=new Sections[n];
        try{
            br=new BufferedReader(new FileReader(fname));
            String line=br.readLine();
            int j=0;
            while((line=br.readLine())!=null){
                StringTokenizer tk=new StringTokenizer(line,",");
                String y=tk.nextToken();
                tk.nextToken();
                sectionArr[j]=new Sections(Integer.parseInt(y), tk.nextToken(), Integer.parseInt(tk.nextToken()));
                j++;
            }
//            String line=br.readLine(),section,lab,lab1,lab2;
//            int j=0,year,lab1Enrol,lab2Enrol;
//            while((line=br.readLine())!=null){
//                StringTokenizer st=new StringTokenizer(line,",");
//                year=Integer.parseInt(st.nextToken());
//                section=st.nextToken();
//                lab1=st.nextToken();
//                lab1Enrol=Integer.parseInt(st.nextToken());
//                if((lab=br.readLine())!=null){
//                    StringTokenizer stLab=new StringTokenizer(lab,",");
//                    stLab.nextToken();
//                    stLab.nextToken();
//                    lab2=stLab.nextToken();
//                    lab2Enrol=Integer.parseInt(stLab.nextToken());
//                    Sections[] labSections=new Sections[2];
//                    labSections[0]=new Sections(year,lab1,null,lab1Enrol);
//                    labSections[1]=new Sections(year,lab2,null,lab2Enrol);
//                    sectionArr[j]=new Sections(year,section,labSections,lab1Enrol+lab2Enrol);
//                }
//                j++;
//            }
            //System.out.println(sectionArr[0].getLecture().getLab().getName());
        }
        catch(FileNotFoundException e){
            System.out.println("The filename you entered does not exist!");
        }
        catch(IOException e){
            Arrays.fill(sectionArr,null);
        }
        finally{
            try{
                br.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return sectionArr;
    }
    public Rooms[] readRooms(String fname){
        BufferedReader br=null;
        int n=getFileLines(fname);
        roomArr=new Rooms[n];
        try{
            br=new BufferedReader(new FileReader(fname));
            String line=br.readLine();
            for(int i=0;i<n;i++)
                roomArr[i]=new Rooms();
            int j=0;
            while((line=br.readLine())!=null){
                //String arr[]=line.split(",");
                StringTokenizer st=new StringTokenizer(line,",");
                roomArr[j]=new Rooms(st.nextToken(),st.nextToken(),Integer.parseInt(st.nextToken()));
                j++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("The filename you entered does not exist!");
        }
        catch(IOException e){
            Arrays.fill(roomArr, null);
        }
        finally{
            try{
                br.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return roomArr;
    }
    public Timeslots[] readTimeslots(String fname){
        BufferedReader br=null;
        int n=getFileLines(fname);
        timeslotArr=new Timeslots[n];
        try{
            br=new BufferedReader(new FileReader(fname));
            String line=br.readLine();
            for(int i=0;i<n;i++)
                timeslotArr[i]=new Timeslots();
            int j=0;
            while((line=br.readLine())!=null){
                //String arr[]=line.split(",");
                StringTokenizer st=new StringTokenizer(line,",");
                timeslotArr[j].setDay(st.nextToken());
                timeslotArr[j].setSlot(Integer.parseInt(st.nextToken()));
                timeslotArr[j].setStart(st.nextToken());
                timeslotArr[j].setEnd(st.nextToken());
                j++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("The filename you entered does not exist!");
        }
        catch(IOException e){
            Arrays.fill(timeslotArr,null);
        }
        finally{
            try{
                br.close();
            }
            catch(Exception e){
                
            }
        }
        return timeslotArr;
    }
    public void export(Period[] arr,int n){
        BufferedWriter bw=null;
        Timetable ob=new Timetable();
        int c=0;
        try{
            bw=new BufferedWriter(new FileWriter("timetable.csv"));
            StringBuffer sb=new StringBuffer();
            sb.append("Year,Section,Lab Section,Day,Start time,End time,Slot,Course Code,Course Name,Course Type,Instructor Name,Resource\n");
            bw.write(sb.toString());
            //System.out.println(n);
            for(int i=0;i<n;i++){
                String s;
                if(arr[i].getCourse()==null)
                    s=arr[i].getSection().getYear()+","+arr[i].getSection().getlabSection().charAt(0)
                            +","+arr[i].getLSection()+","+arr[i].getTimeslots().getDay()+","+arr[i].getTimeslots().getStart()
                            +","+arr[i].getTimeslots().getEnd()+","+arr[i].getTimeslots().getSlot()+",FREE";
                else
                    s=arr[i].getSection().getYear()+","+arr[i].getSection().getlabSection().charAt(0)
                            +","+arr[i].getLSection()+","+arr[i].getTimeslots().getDay()+","+arr[i].getTimeslots().getStart()
                            +","+arr[i].getTimeslots().getEnd()+","+arr[i].getTimeslots().getSlot()+","+arr[i].getCourse().getCode()
                            +","+arr[i].getCourse().getName()+","+arr[i].getCourse().getCourseType()
                            +","+arr[i].getInstructors().getName()+","+arr[i].getRooms().getResource();
                bw.write(s+"\n");
                //System.out.println(s);
            }
            System.out.println("The file has been successfully exported!");
        }
        catch(IOException e){
            System.out.println("The file could not be exported!");
        }
        finally{
            try{
                bw.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
