
/**
 *
 * @author Sayantan
 */
import java.util.*;
public class LogicMgr {
    public Courses[] coursesVar; 
    public Instructors[] instructorsVar; 
    public Sections[] sectionsVar;
    public Rooms[] roomsVar;
    public void setCourses(Courses[] courses){
        coursesVar=courses;
    }
    public void setInstructors(Instructors[] instructors){
        this.instructorsVar=instructors;
    }
    public void setSections(Sections[] sections){
        sectionsVar=sections;
    }
    public void setRooms(Rooms[] rooms){
        roomsVar=rooms;
    }
    public Instructors[] updateInstructorArray(){
        return this.instructorsVar;
    }
    public Courses[] courseToInstructor(){
        int labSections = 6;
        int lectureSections = 3;
        int unit;
        int len = coursesVar.length;
        for(int i =0;i<len;i++){
            int slotsRequired = coursesVar[i].getTotalSlots(); 
            int len1 = instructorsVar.length; 
            Vector associatedInstructorVector = new Vector();
            Vector expertInstructorVector = new Vector(); 
            unit = (coursesVar[i].getCourseType().equals("Lecture"))?1:coursesVar[i].getContactSlots();
            for(int j=0;j<len1;j++){
                unit = (coursesVar[i].getCourseType().equals("Lecture"))?1:coursesVar[i].getContactSlots();
                if(instructorsVar[j].getSlots()+unit<=15){
                    Courses[] expertiseList = instructorsVar[j].getExpertiseList();
                    int temp1 = expertiseList.length; 
                    for(int k =0;k<temp1;k++){
                        boolean flag = true;
                        if(expertiseList[k].getCode().equals(coursesVar[i].getCode())){
                            if(coursesVar[i].getAssociation()!=null){
                                for(int l=0;l<temp1;l++){
                                   if(coursesVar[i].getAssociation().getCode().equals(expertiseList[l].getCode())){
                                       flag = false;
                                       associatedInstructorVector.addElement(instructorsVar[j]);
                                       break;
                                   } 
                                }
                                if(flag)
                                    expertInstructorVector.addElement(instructorsVar[j]);
                            }
                            else
                                expertInstructorVector.addElement(instructorsVar[j]);
                            break;
                        }
                    }
                }    
            }
            Instructors[] expertInstructors = null;
            Instructors[] associatedInstructors = null; 
            if(associatedInstructorVector.size()>0){
                associatedInstructors = new Instructors[associatedInstructorVector.size()];
                associatedInstructorVector.copyInto(associatedInstructors);
            }
            if(expertInstructorVector.size()>0){
                expertInstructors = new Instructors[expertInstructorVector.size()];
                expertInstructorVector.copyInto(expertInstructors);
            }
            int[] expertSlots;
            int[] associatedSlots;
            if(associatedInstructors!=null){
                int len2 = associatedInstructors.length;
                associatedSlots = new int[len2];
                for(int k=0;k<len2;k++){
                    associatedSlots[k] = 0;
                }
                while(slotsRequired>0){
                boolean flag = false;
                for(int k=0;k<len2;k++){
                    if((associatedInstructors[k].getSlots()+ unit)<=15){
                        slotsRequired -= unit ;
                        associatedInstructors[k].incrementSlots(unit);
                        associatedSlots[k] += unit;
                        if(slotsRequired==0)
                            break;
                        flag=true;
                    }
                }
                if(!flag)
                    break;
                }
                coursesVar[i].setAssociatedInstructors(associatedInstructors);
                coursesVar[i].setAssociatedInstructorsSlots(associatedSlots);
            }
            if((slotsRequired>0)&&(expertInstructors!=null)){
                int len3 = expertInstructors.length;
                expertSlots = new int[len3];
                for(int k=0;k<len3;k++){
                    expertSlots[k] = 0;
                }
                while(slotsRequired>0){
                    boolean flag = false;
                    for(int k=0;k<len3;k++){
                        if((expertInstructors[k].getSlots()+unit)<=15){
                            slotsRequired -= unit ;
                            expertInstructors[k].incrementSlots(unit);
                            expertSlots[k] += unit;
                            if(slotsRequired==0)
                                break;
                            flag=true;
                        }
                    }
                    if(!flag)
                        break;
                }
                coursesVar[i].setExpertInstructorsSlots(expertSlots);
                coursesVar[i].setExpertInstructors(expertInstructors);
            } 
            coursesVar[i].updateTotalSlots(slotsRequired);
        }
        int len4 = instructorsVar.length;
            for(int k=0;k<len4-1;k++){
                for(int l=k;l<len4;l++)
                {
                    if(instructorsVar[k].getSlots()>instructorsVar[l].getSlots()){
                        Instructors temp = instructorsVar[k];
                        instructorsVar[k]=instructorsVar[l];
                        instructorsVar[l]=temp;
                    }
                }
            }
        for(int i=0;i<len; i++){
            int slotsRequired = coursesVar[i].getTotalSlots();
            int count=0;
            unit = (coursesVar[i].getCourseType().equals("Lecture"))?1:coursesVar[i].getContactSlots();
            if(coursesVar[i].getTotalSlots()>0){
                Vector notExpertInstructors = new Vector();
                while(slotsRequired>0){
                    if((instructorsVar[count].getSlots()+ unit)<=15){
                                slotsRequired -= unit;
                                instructorsVar[count].incrementSlots(unit);
                                instructorsVar[count].setTempCount(unit);
                    }
                    if(instructorsVar[count].getSlots()>instructorsVar[count+1].getSlots()){
                        notExpertInstructors.addElement(instructorsVar[count]);
                        count++;
                    }    
                    if(count == instructorsVar.length)
                        count = 0;
                }
                Instructors[] notExpertList = new Instructors[notExpertInstructors.size()];
                notExpertInstructors.copyInto(notExpertList);
                coursesVar[i].setNotExpertInstrutors(notExpertList);
            }
        }
        return this.coursesVar;
    }
    public Sections[] sectionToCourse(){
        int temp = sectionsVar[0].getYear();
        int count =1; 
        for(int i=1;i<sectionsVar.length;i++){
            if(temp!=sectionsVar[i].getYear()){
                temp = sectionsVar[i].getYear();
                count++;
            }
        }
        SC[] sc = new SC[count];      
        temp = sectionsVar[0].getYear();
        sc[0] = new SC(temp);
        count = 1;
        for(int i=1;i<sectionsVar.length;i++){
            if(temp!=sectionsVar[i].getYear()){
                temp = sectionsVar[i].getYear();        
                sc[count] = new SC(temp);
                count++;
            }
        } 
        for(int j=0;j<coursesVar.length;j++){
            temp = coursesVar[j].getYear();
            for(int i=0;i<count;i++){
                if(temp==sc[i].getYear()){
                    sc[i].addCourses(coursesVar[j]);
                    break;
                }
            }
        }
        for(int i=0;i<count;i++){
            sc[i].setCoursesArray();
        }        
        for(int i=0;i<sectionsVar.length;i++){
            temp = sectionsVar[i].getYear();
            for(int j=0;j<count;j++){
                if(temp==sc[j].getYear()){
                    sectionsVar[i].setCourses(sc[j].getCourses());
                }
            }
        }
        return sectionsVar;      
    }
    public Sections[] sectionToInstructors(){
        for(int i=0;i<sectionsVar.length;i+=2){
            Courses[] sCourses = sectionsVar[i].getCourses();
            for(int j=0;j<sCourses.length;j++){
                if(sCourses[j].getCourseType().equals("Lab"))
                    continue;
                Instructors[] tempInstructors = sCourses[j].getAllInstructors();
                int[] tempInstructorsSlots = sCourses[j].getAllInstructosSlots();
                int slots = sCourses[j].getContactSlots();
                int unit = sCourses[j].getUnit();
                CI obj = new CI(tempInstructors);
                if(tempInstructors!=null){    
                    while(slots>0){
                        for(int k=0;k<tempInstructors.length;k++){
                            if(slots <= 0)
                                break;
                            if(tempInstructorsSlots[k]>0){
                                sCourses[j].updateSlots(unit, k);
                                slots -=unit;
                                obj.setInstructorsSlots(unit,k);
                            }
                        }
                    }
                sectionsVar[i].setSectionInstructos(obj, j);
                sectionsVar[i+1].setSectionInstructos(obj, j);
                }
            }
        }
        for(int i=0;i<sectionsVar.length;i++){
            Courses[] sCourses = sectionsVar[i].getCourses(); 
            for(int j=0;j<sCourses.length;j++){
                if(sCourses[j].getCourseType().equals("Lecture"))
                    continue;
                Instructors[] tempInstructors = sCourses[j].getAllInstructors();
                int[] tempInstructorsSlots = sCourses[j].getAllInstructosSlots();
                int slots = sCourses[j].getContactSlots();
                int unit = sCourses[j].getUnit();
                CI obj = new CI(tempInstructors);
                if(tempInstructors!=null){    
                    while(slots>0){
                        for(int k=0;k<tempInstructors.length;k++){
                            if(slots <= 0)
                                break;
                            if(tempInstructorsSlots[k]>0){
                                //tempInstructorsSlots[k] -= 3;
                                sCourses[j].updateSlots(unit, k);
                                slots -= unit;
                                obj.setInstructorsSlots(unit,k);
                            }
                        }
                    }
                sectionsVar[i].setSectionInstructos(obj, j);
                }
            }
        }
        return sectionsVar;      
    }
    public Sections[] sectionToRoom(){
        int count = 0;
        for(int i=0;i<sectionsVar.length;i+=2){
            Courses[] sCourses = sectionsVar[i].getCourses();
            Rooms tempRoom = null;
            if(count>=roomsVar.length){
                count=0;
            }
            for(;count<roomsVar.length;count++){
                if(roomsVar[count].getType().equals("Lecture Room")){
                    if(roomsVar[count].getCapacity()>=(2*sectionsVar[i].getEnrolment())){
                        break;
                    }    
                }
            }
            if(count>=roomsVar.length){
                i -= 2;
                continue;
            }
            tempRoom = roomsVar[count];
            count++;
            for(int j=0;j<sCourses.length;j++){
                if(sCourses[j].getResourceType().equals("Lecture Room")){
                    sectionsVar[i].setRoom(tempRoom, j);
                    sectionsVar[i+1].setRoom(tempRoom, j);
                }    
            }
        }
        for(int i=0;i<sectionsVar.length;i += 6){
            Courses[] sCourses = sectionsVar[i].getCourses();
            for(int j=0;j<sCourses.length;j++){
                Rooms[] tempRooms = null;
                Vector tempRoomsVector;
                tempRoomsVector = new Vector();
                if(sCourses[j].getResourceType().equals("Lecture Room"))
                    continue;        
                final String resource = sCourses[j].getResourceType();
                for(int k=0;k<roomsVar.length;k++){
                    if(resource.equals(roomsVar[k].getType())){
                        tempRoomsVector.addElement(roomsVar[k]);
                    }
                }
                tempRooms = new Rooms[tempRoomsVector.size()];
                tempRoomsVector.copyInto(tempRooms);
                sectionsVar[i].setLabRoom(tempRooms, j);
                sectionsVar[i+1].setLabRoom(tempRooms, j);
                sectionsVar[i+2].setLabRoom(tempRooms, j);
                sectionsVar[i+3].setLabRoom(tempRooms, j);
                sectionsVar[i+4].setLabRoom(tempRooms, j);
                sectionsVar[i+5].setLabRoom(tempRooms, j);
            } 
        }
        return sectionsVar;       
    }   
    public Sections[] sectionToSlotsLabs(){
        int unit = 3;
        boolean backtrack = false;
        boolean flag = false;
        Z:
        for(int i=0;i<sectionsVar.length;i+=2){   
            Courses[] sCourses = sectionsVar[i].getCourses();
            int numLabCourses = 0;
            int lectureCourses = 0;
            for(int j=0;j<sCourses.length;j++){
                if(sCourses[j].getCourseType().equals("Lab")){
                    numLabCourses++;
                }
                else{
                    lectureCourses++;
                }
            }
            if(!backtrack){
                sectionsVar[i].setNumLabCourses(numLabCourses);
                sectionsVar[i+1].setNumLabCourses(numLabCourses);
            }    
            backtrack = true;
            int[] indices = new int[numLabCourses];
            for(int j=0,k=0;j<sCourses.length;j++){
                if(sCourses[j].getCourseType().equals("Lab")){
                    indices[k] = j;
                    k++;
                }
            }
            Courses course1=null, course2=null;
            Instructors ins1=null, ins2=null;
            Rooms room1=null, room2=null;
            int index1 = -1, index2=-1;
            int day=-1, slot=-1;
            int room1Count = 0, room2Count=0;
            int l =0, m=0;
            int j = sectionsVar[i].getCount() + 1;
            Y:
            for(;j<numLabCourses;j++){      
                boolean labelUFlag = false;
                boolean labelVFlag = false;
                boolean labelWFlag = false;
                boolean labelXFlag = false;
                boolean labelYFlag = false;
                boolean labelZFlag = false;
                flag = false;
                index1 = j;
                course1 = sCourses[indices[index1]];
                int y = sectionsVar[i].getPrevY(j);
                if(y==-1){
                    y++;
                    index2 = index1 + 1;
                }
                else{
                    index2 = sectionsVar[i+1].getPrevCourse(j) - lectureCourses;
                }
                X:
                for(;y<numLabCourses;y++,index2++){                    
                    if(index2>=numLabCourses){
                        index2=0;
                    }
                    if(index2==index1){
                        if(sectionsVar[i].getLabRoomCount(indices[index1])<=1){
                            continue;
                        }
                    }
                    if(sectionsVar[i+1].getCourseAllocatedStatus(indices[index2])==false){
                        continue;    
                    }    
                    course2 = sCourses[indices[index2]];                  
                    room1Count = sectionsVar[i].getLabRoomCount(indices[index1]);
                    room2Count = sectionsVar[i+1].getLabRoomCount(indices[index2]);
                    int k=-1;
                    if(!labelXFlag)
                        k = sectionsVar[i].getPrevDay(j);
                    else{
                        k=0;
                        labelXFlag = false;
                    }
                    if(k==-1){
                        k=0;
                    }
                    W:
                    for(;k<5;k++){
                        day = k;
                        ins1 = sectionsVar[i].getLabInstructor(indices[index1], k);
                        ins2 = sectionsVar[i+1].getLabInstructor(indices[index2], k);
                        if(ins1==null||ins2==null||ins1.equals(ins2)){
                            continue;
                        }
                        if(!labelWFlag)
                            slot = sectionsVar[i].getPrevSlot(j);
                        else{
                            slot = 0;
                            labelWFlag = false;
                        }
                        if(slot<0){
                            slot=0;
                        }
                        V:
                        for(;slot<5;slot+=4){
                            if(sectionsVar[i].getAllocatedStatus(k, slot)==1){
                                continue;
                            }
                            if(!labelVFlag)
                                l = sectionsVar[i].getPrevRoom(j);
                            else{
                                l=0;
                                labelVFlag = false;
                            }
                            if(l==-1){
                                l=0;
                            }
                            U:
                            for(;l<room1Count;l++){
                                room1 = sectionsVar[i].getRoom(k, slot, unit, indices[index1], l);
                                if(room1==null){
                                    continue;
                                }
                                else{
                                    if(!labelUFlag)
                                        m = sectionsVar[i+1].getPrevRoom(j) + 1;                                 
                                    else{
                                        m = 0;
                                        labelUFlag = false;
                                    }
                                    T:
                                    for(;m<room2Count;m++){
                                        room2 = sectionsVar[i+1].getRoom(k, slot, unit, indices[index2], m);
                                        if(room2==null||room2.equals(room1)){
                                            continue;
                                        }
                                        else{
                                            flag = true;
                                            break X;
                                        }
                                    }
                                }
                                labelUFlag = true;
                            }//end of U loop
                            labelVFlag = true;
                        }//end of V loop
                        labelWFlag = true;
                    }//end of W loop    
                    labelXFlag = true;
                }//end of X loop
                if(flag){
                    sectionsVar[i].setSequenceArray(indices[index1], day, y, ins1, slot, room1, l);
                    sectionsVar[i+1].setSequenceArray(indices[index2], day, y, ins2, slot, room2, m);
                    sectionsVar[i].updateAllocatedStatus(indices[index1], day, slot, unit);
                    sectionsVar[i+1].updateAllocatedStatus(indices[index2], day, slot, unit);
                    room1.updateAllocation(day, slot, unit);
                    room2.updateAllocation(day, slot, unit);
                    ins1.updateDialySlot(unit, day, slot);
                    ins2.updateDialySlot(unit, day, slot);
                    sectionsVar[i].updateInstructorsSlots(indices[index1], ins1, unit);
                    sectionsVar[i+1].updateInstructorsSlots(indices[index2],ins2,unit);
                    sectionsVar[i].setSCIR(day, slot, indices[index1], ins1, room1, unit);
                    sectionsVar[i+1].setSCIR(day, slot, indices[index2], ins2, room2, unit);
                }
                else{
                    if(j>0){
                        sectionsVar[i].deAllocated();
                        sectionsVar[i+1].deAllocated();
                        j-=2;
                        continue Y;
                    }
                    else if(j==0 && i==0){
                        backtrack = true;
                        continue Z;
                    }
                    else{
                        sectionsVar[i-2].deAllocated();
                        sectionsVar[i-1].deAllocated();
                        sectionsVar[i].setNumLabCourses(numLabCourses);
                        sectionsVar[i+1].setNumLabCourses(numLabCourses);
                        i -=4;                        
                        backtrack = true;
                        continue Z;
                    }
                }
            }//end of Y loop
            backtrack = false;
        }//end of Z loop
        return sectionsVar;
    }     
    public Sections[] sectionToSlotsLecture(){
        int unit=1;
        for(int i=0, r=0;i<sectionsVar.length;i+=2,r++){
            Courses[] sCourses = sectionsVar[i].getCourses();
            int day=-1,slot=-1;
            Z:
            for(int j=0;j<sCourses.length;j++){              
                if(sCourses[j].getCourseType().equals("Lab")){
			continue;
                }        
                Rooms room = roomsVar[r];
                sectionsVar[i].sortInstructors(j);
                int slotsRequired = sCourses[j].getContactSlots();
                day = 0;
		Y:
		while(slotsRequired>0){
                    for(slot=0;slot<7;slot++){
                        if(sectionsVar[i].getAllocatedStatus(day, slot)==0){
                            if(sectionsVar[i].checkPrevCourse(j,day,slot)){
                                Instructors ins = sectionsVar[i].getLectureInstructor(j);
                                if(ins.checkSlot(day, slot)){
                                    slotsRequired--;
                                    sectionsVar[i].updateAllocatedStatus(j, day, slot, unit);
                                    sectionsVar[i+1].updateAllocatedStatus(j, day, slot, unit);
                                    //room.updateAllocation(day, slot, unit);
                                    ins.updateDialySlot(unit, day, slot);
                                    sectionsVar[i].updateInstructorsSlots(j, ins, unit);
                                    sectionsVar[i].setSCIR(day, slot, j, ins, room, unit);
                                    sectionsVar[i+1].setSCIR(day, slot, j, ins, room, unit);
                                    break;
                                }
                            }    
                        }
                    }
                    day++;
                    if(day==5){
                        day=0;
                    }
                }               
            }
        }
        return sectionsVar;
    }
}
