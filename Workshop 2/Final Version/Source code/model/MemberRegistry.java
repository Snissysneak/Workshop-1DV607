package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class MemberRegistry {
    private Member member;
    private Boat boat;

    public MemberRegistry() {
    }

    /*Add new entry member to the file. New entry contains memberID, name and person number*/
    public void addMember(String name, String personalNum, int memberID) throws IOException {
        member = new Member(name, personalNum, memberID); //create a new member for usage in updating the txt file
        ArrayList<String[]> db = readDatabase();
        String[] newRecord = new String[3];

        newRecord[0] = Integer.toString(member.getMemberID());
        newRecord[1] = member.getName();
        newRecord[2] = member.getPersonalNum();

        db.add(newRecord);
        writeDatabase(db);
    }
    /*Change members info in the record  and write to the file */
    public void changeMember(String newName, String newPersonNumber, String memberID) throws IOException {
        ArrayList<String[]> db = readDatabase();
        for (String[] sourceRecord : db) {
            if (sourceRecord[0].equals(memberID)) {
                sourceRecord[1] = newName;
                sourceRecord[2] = newPersonNumber;
            }
        }
        writeDatabase(db);
    }
    /*Removes members record from the file */
    public void deleteMember(String memberID) throws IOException {
        ArrayList<String[]> db = readDatabase();

        db.removeIf(record -> record[0].equals(memberID));
        writeDatabase(db);
    }
    /*Add Boat to file */
    public void addBoat(int boatID, String boatType, int boatLength, String ownerID) throws IOException {
        boat = new Boat(boatID,boatType,boatLength);

        ArrayList<String[]> db = readDatabase();
        int index = 0;
        for (int i = 0; i < db.size(); i++) {
            String[] sourceRecord = db.get(i);
            if (sourceRecord[0].equals(ownerID)) {
                db.remove(index);
                String[] record = Arrays.copyOf(sourceRecord, sourceRecord.length + 3);

                record[sourceRecord.length] = Integer.toString(boat.getBoatID());
                record[sourceRecord.length + 1] = boat.getBoatType();
                record[sourceRecord.length + 2] = Integer.toString(boat.getBoatLength());

                db.add(index, record);
            }
            index++;
        }
        writeDatabase(db);
    }
    /*Change members info in the record  and write to the file */
    public void changeBoat(String boatID, String newBoatType, String newLength) throws IOException {
        ArrayList<String[]> db = readDatabase();
        for (String[] sourceRecord : db) {
            for (int i = 0; i < sourceRecord.length; i++) {
                if (sourceRecord[i].equals(boatID)) {
                    sourceRecord[i + 1] = newBoatType;
                    sourceRecord[i + 2] = newLength;
                }
            }
        }
        writeDatabase(db);
    }
    /*Delete boat from file */
    public void deleteBoat(String input) throws IOException {
        ArrayList<String[]> db = readDatabase();

        for (String[] record : db) {
            for (int i = 0; i < record.length; i++) {
                if (record[i].equals(input)) {
                    if (!record[i].isEmpty())
                        record[i] = "";
                    record[i + 1] = "";
                    record[i + 2] = "";
                }
            }
        }
        writeDatabase(db);
    }
    /* Counting boats for member given in input */
    public int getNumberOfBoats(String[] input) {
        int numberOfBoats = 0;
        for (int i = 3; i < input.length; i++) {
            if (i % 3 == 0) {
                numberOfBoats++;
            }
        }
        return numberOfBoats;
    }
    /* Write content of ArrayList in file */
    public void writeDatabase(ArrayList<String[]> database) {
        File temp = new File("temp.txt");
        File mainFile = new File("members.txt");
        try {
            FileWriter fw = new FileWriter(temp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (String[] record : database) {
                StringBuilder newLine = new StringBuilder();
                for (String entry : record) {
                    if (!entry.isEmpty())
                        newLine.append(entry).append(" ");

                }
                pw.println(newLine);
            }
            pw.flush();
            pw.close();
            mainFile.delete();
            File dump = new File("members.txt");
            temp.renameTo(dump);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* Read content of file and save in ArrayList */
    public ArrayList<String[]> readDatabase() throws IOException {
        String line;
        ArrayList<String[]> entries = new ArrayList<>();
        File file = new File("members.txt");
        file.createNewFile();
        try {
            Scanner x = new Scanner(new File("members.txt"));
            x.useDelimiter("[,\n]");

            while (x.hasNextLine()) {
                line = x.nextLine();     //whole line from file
                String[] lineArray = line.split("\\s");   //line into array
                if (!line.isEmpty()) {
                    entries.add(lineArray);
                }
            }
            x.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
    /*Returns true if input already in the file */
    public boolean existInFile(String input) throws IOException {
        ArrayList<String[]> db = readDatabase();
        for (String[] record : db) {
            for (String s : record) {
                if (s.equals(input))
                    return true;
            }
        }
        return false;
    }
}

