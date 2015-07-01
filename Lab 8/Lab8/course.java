import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of class course here.
 * 
 * Liam Considine 
 * Lab 8 7/1/2015
 */
public class course
{
    // instance variables - replace the example below with your own
    private ArrayList<Student> students;

    public course()
    {
        students = new ArrayList<Student>();
    }

    public void readStudentData(String filename)
    {
        String info;
        Student s;
        try
        {
            // open the data file
            Scanner fileReader = new Scanner(new File(filename)); 
            Scanner lineReader;

            // continue while there is more data to read
            while(fileReader.hasNext()) {

                // read one line of data
                info = fileReader.nextLine();

                lineReader = new Scanner(info);
                lineReader.useDelimiter(",");

                // read the items one at a time
                String last = lineReader.next();
                String first = lineReader.next();
                int ID = lineReader.nextInt(); 
                double gpa = lineReader.nextDouble();

                // instantiate s as an object of the student class - TO DO
                s = new Student(first, last, ID, gpa);

                // add the student s to the ArrayList - TO DO
                students.add(s);
            }
        }
        catch(FileNotFoundException error)
        {
            // could not find file
            System.out.println("File not found ");

        }

        catch(Exception error)
        {
            // problem reading the file
            System.out.println("Oops!  Something went wrong.");
        }

    }

    public String chooseFile()
    {
        String filename = "";
        JFileChooser fileChooser = 
            new JFileChooser(new File(System.getProperty("user.dir") + "\\\\.."));
        fileChooser.setDialogTitle("Student files");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int status = fileChooser.showOpenDialog(null );
        if (status == JFileChooser.APPROVE_OPTION)
        {
            filename = fileChooser.getSelectedFile().getAbsolutePath();
        }
        return filename;

    }

    public void printRoster()
    {
        for (Student std : students){
            System.out.println(std);
        }
    }

    public ArrayList<Student> probationList()
    {
        ArrayList<Student> probation = new ArrayList<Student>();
        for (Student std : students){
            if (std.getGpa() < 2.0){
                probation.add(std);
            }
        }
        return probation;
    }

    public Student findHighestGrade()
    {
        Student val = students.get(0);
        for (Student std : students) {
            if (std.getGpa() > val.getGpa()){
                val = std;
            }
        }
        return val;
    }

    public boolean searchId(int studentId)
    {
        boolean valId = false;
        for (Student std : students) {
            if (std.getId() == studentId){
                valId = true;
            }
        }
        return valId;
    }
    
    public static void main(String [ ] args) {
        course gvsu = new course();
        String filename = gvsu.chooseFile();
        gvsu.readStudentData(filename);
        gvsu.printRoster();
        System.out.println(gvsu.findHighestGrade());
        System.out.println(gvsu.probationList());
        System.out.println("Search student ID 101 - Results: " + gvsu.searchId(101));
        System.out.println("Search student ID 999 - Results: " + gvsu.searchId(999));
        
        
    }
    

}
