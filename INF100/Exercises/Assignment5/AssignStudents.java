import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileWriter;

public class AssignStudents{

  /**
   * Reads each line from a file and puts it in an ArrayList of strings
   * @param  String filename      The textfile
   * @return an ArrayList from the file
   */
  public static ArrayList<String> namesFromFilename(String filename) {

    ArrayList<String> nameList = new ArrayList<>();

    try {
      File fil = new File(filename);
      Scanner inn = new Scanner(fil);

      while (inn.hasNextLine()) {
        String linje = inn.nextLine();
        nameList.add(linje);
      }
      inn.close();
    }
    catch(FileNotFoundException e) {
      System.out.println("File not found with exception: " + e);
    }

    return nameList;
  }

  /**
   * Assign the list of students to each graders
   * @param  ArrayList<String> students      list of students
   * @param  ArrayList<String> graders       list of graders
   * @return a combined list where students are assigned to each graders
   */
  public static ArrayList<String> assignGraders(ArrayList<String> students, ArrayList<String> graders) {
    ArrayList<String> gradersWithStudents = new ArrayList<>();
    int studentSize = students.size();
    int graderSize = graders.size();

    int count = 0;
    for(String s : students) {
      gradersWithStudents.add(s + ", " + graders.get(count));
      count++;
      if(count == graderSize) {
        count = 0;
      }
    }

    int count2 = 0;
    for(int i = 0; i < gradersWithStudents.size(); i++) {
      if (gradersWithStudents.get(i).contains(graders.get(count))) {
        System.out.println("");
      }
      count++;
      if(count == graderSize) {
        count = 0;
      }
    }

    return gradersWithStudents;
  }


  /**
   * Takes a list and writes it to a textfile
   * @param ArrayList<String> assignment an ArrayList
   * @param String            filename   name of the textfile
   */
  public static void writeAssignment(ArrayList<String> assignment, String filename) {
    try {
      FileWriter writer = new FileWriter(filename + ".txt");

      for(String s : assignment) {
        writer.write(s + "\n");
      }
      writer.close();
    } catch(IOException e) {
      System.out.println("Something went wrong with filewriting");
    }
  }

  public static void main(String[] args) {
    ArrayList<String> resultList = assignGraders(namesFromFilename("students.csv"), namesFromFilename("graders.csv"));

    writeAssignment(resultList, "result");
  }
}
