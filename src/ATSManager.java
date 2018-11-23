/**
 *
 * @author Puskar
 * @author Sayantan
 */
public class ATSManager {
    public static void main(String args[]){
        DataManager dataObj=new DataManager();
        Courses cArr[]=dataObj.readCourses("courses.csv");
        Instructors iArr[]=dataObj.readInstructors("instructors.csv", cArr);
        Sections sArr[]=dataObj.readSections("sections.csv");
        Rooms rArr[]=dataObj.readRooms("rooms.csv");
        Timeslots tArr[]=dataObj.readTimeslots("timeslots.csv");
        LogicMgr logicObj = new LogicMgr();
        logicObj.setCourses(cArr);
        logicObj.setInstructors(iArr);
        logicObj.setSections(sArr);
        logicObj.setRooms(rArr);
        cArr = logicObj.courseToInstructor();      
        for(int i =0;i<cArr.length;i++){
            cArr[i].courseInstructors();
        }
        sArr = logicObj.sectionToCourse();
        sArr = logicObj.sectionToInstructors();
        sArr = logicObj.sectionToRoom();   
//        System.out.println("Sections allocated to Room courseWise");
        sArr = logicObj.sectionToSlotsLabs();
        sArr = logicObj.sectionToSlotsLecture();
//        System.out.println("\nSection to LabSlots Details");
        Timetable tt=new Timetable();
        tt.getTimetable(sArr,tArr);
        Period arr[]=tt.getTimetable();
        //tt.print();
        //System.out.println(tt.lengthArr());
        dataObj.export(arr,tt.lengthArr());
    }
}
