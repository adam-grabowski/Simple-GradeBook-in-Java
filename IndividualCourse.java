/* IndividualCourse.java
 * GradeBook v.1.0 by Adam Grabowski
 * 25 January 2009
 * Purpose: Contains list of students (Student class objects)
 * Requires: Student.java
 */

import java.io.*;
import java.util.List;
import java.util.ArrayList;

class IndividualCourse
{ 
  private String courseName, studName;
  private List <Student> students;
  
  
  public IndividualCourse ( String theName )  throws IOException
  {
    this.courseName     = theName;
    students = new ArrayList <Student>(); 
    
  }
  
  public void createStudentList() throws IOException  {
    //Reads file and assign each student to the course
    BufferedReader studFile = new BufferedReader(new FileReader (courseName + "/students.txt"));  
    while(true)
    {
      studName = studFile.readLine();
      if(studName==null){break;}
      Student m = new Student(studName);
      students.add(m);
    }    
    studFile.close(); //Close File
  }
  
  
  
  public String getCourseName(){
    return this.courseName;
  }
  
  
  public void setCourseName(String n){
    this.courseName = n;
  }
  
  
  /////////////////////////////// STUDENTS OF THE COURSE///////////////////////////        
  
  
//SHOW STUDENTS LIST
  public void showStudents(){
    for(int i=1; i < students.size();i++){
      Output.println("[" + i + "]" + students.get(i).getStudentName());
    }
  }
  
  
  
  
  //CREATE NEW STUDENT
  public void createStudent(Student k)  throws IOException
  { 
    //Add student to file of students 
    PrintWriter courseFile = new PrintWriter(new FileWriter (courseName + "/students.txt",true));
    courseFile.println(k.getStudentName());
    courseFile.close(); 
    
    //Create student file in course folder
    File f;
    f=new File(courseName + "/" + k.getStudentName() + ".txt");
    if(!f.exists()){
      f.createNewFile();
    }        
    //Add first entry
    PrintWriter stFile = new PrintWriter(new FileWriter (courseName + "/" + k.getStudentName() + ".txt",true));
    stFile.println("null");
    stFile.println("-1");
    stFile.println("-1");
    stFile.close(); 
    students.add(k);//add student at the end of the list
  } // END CREATE NEW STUDENT      
  
  
  
  
  
//DELETE STUDENT 
  public void deleteStudent(int index) throws IOException 
  {   
    //Delete student file 
    boolean success = (new File(courseName + "/" + students.get(index).getStudentName() + ".txt")).delete();
    if (!success) {
      Output.println("Student file cannot be deleted!");
    }
    
    
    students.remove(index); //removes student object form the list
    
    //Put new array to the file
    PrintWriter delStudentFile = new PrintWriter(new FileWriter (courseName + "/students.txt"));
    
    for(int j = 0; j < students.size(); j++)
    {
      delStudentFile.println(students.get(j).getStudentName());
    }  
    delStudentFile.close();      
  }
  
  
  
  
  //START OF EDIT STUDENT NAME
  public void editStudentName(int nm, String st) throws IOException  
  { 
    // Rename file name
    File file = new File(courseName + "/" + students.get(nm).getStudentName() + ".txt");
    File file2 = new File(courseName + "/" + st + ".txt");  
    boolean success = file.renameTo(file2);
    if (!success) {  
      Output.println("Student file cannot be renamed!");
    }
    
    students.get(nm).setStudentName(st); //Edit element of array
    
    PrintWriter editStudentFile = new PrintWriter(new FileWriter (courseName + "/students.txt"));
    for(int j = 0; j < students.size(); j++)
    {
      editStudentFile.println(students.get(j).getStudentName());
    }  
    editStudentFile.close();      
    
  } //ENDOF  EDIT STUDENT NAME
  
  
  
  
//GETS SELECTED OBJECT FROM THE LIST
  public Student getStudentObject(int no) throws IOException {
    students.get(no).createAssignmentsList(courseName);
    return students.get(no);     
  }
  
  
  
//GET STUDENT LIST SIZE
  public int getSize(){
    return students.size();
  }   
  
  
  
  
} //end class IndividualCourse
