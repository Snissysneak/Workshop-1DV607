package model;
import java.io.*;

public class Member extends Boat{
    String name;
    String personalNum;
    int memberID;

    StringBuilder sb; //not in use as of right now

    public Member() {

    }
    public Member(String name, String personalNum, int memberID) {
        this.name = name;
        this.personalNum = personalNum;
        this.memberID = memberID;
    }

    public void addBoat(String boatType, int boatLength, String input) {
        //This is a bad solution for adding boats to the txt file.

        //It reades the information from the original file and then
        //changes the information in the temp file and lastly takes
        //the information from the temp file to the original file
        int boatID = idGenerator();
        Boat newBoat = new Boat(boatID, boatType, boatLength);

        try{
            File temp = new File("temp.txt");

            FileWriter fw = new FileWriter(temp, false);
            FileInputStream fstream = new FileInputStream("members.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;

            while ((strLine = br.readLine()) != null)   {
                if(strLine.contains(input)) {
                    fw.append(strLine + "," + newBoat.getBoatID() + "," + newBoat.getBoatType() + "," + newBoat.getBoatLength());
                }
                else {
                    fw.append(strLine);
                }
                fw.append("\n");
            }
            fw.close();
            in.close();

            File original = new File("members.txt");

            FileWriter fileWriter = new FileWriter(original, false);
            FileInputStream fileStream = new FileInputStream("temp.txt");
            DataInputStream inData = new DataInputStream(fileStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inData));

            String stringLine;

            while ((stringLine = bReader.readLine()) != null)   {
                fileWriter.append(stringLine + "\n");
            }
            fileWriter.close();
            inData.close();

            temp.delete();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void removeBoat() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalNum() {
        return personalNum;
    }

    public void setPersonalNum(String personalNum) {
        this.personalNum = personalNum;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int idGenerator() {
        return (int)(Math.random() * ((9999 - 1) + 1)) + 1; //random number gen
    }
}
