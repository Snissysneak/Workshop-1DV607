package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class MemberRegistry {

    public ArrayList<Member> members_list;
    private final String splitter = " ";

    public MemberRegistry() {
    }

    /*Add new entry member to the file. New entry contains memberID, name and person number*/
    public void addMember(Member a_member) {
        members_list.add(a_member);
        updateFile();
    }

    /*Change members info in the record  and write to the file */
    public void changeMember(Member a_member) {
        int index = -1;
        for (Member m : members_list){
            if(m.getMemberID() == a_member.getMemberID()){
                index = members_list.indexOf(m);
            }
        }
        members_list.remove(index);
        members_list.add(index, a_member);
        updateFile();
    }

    /*Removes members record from the file */
    public void deleteMember(Member a_member) {
        members_list.removeIf(tempMember -> tempMember.equals(a_member));
        updateFile();
    }

    public Member getMember(int a_memberID) {
        Member member = new Member();
        for (Member m : members_list){
            if(m.getMemberID() == a_memberID){
                return m;
            }
        }
        return member;
    }

    /*Add Boat to file */
    public void addBoat(int a_memberID, Boat a_boat) {
        for (Member m : members_list){
            if(m.getMemberID() == a_memberID){
                m.addBoat(a_boat);
            }
        }
        updateFile();
    }

    /*Change members info in the record  and write to the file */
    public void changeBoat (Boat a_boat) {
        for (Member m : members_list) {
            for (Boat b : m.ownedBoats) {
                if (b.getBoatID() == a_boat.getBoatID()) {
                    m.ownedBoats.remove(b);
                    m.ownedBoats.add(a_boat);
                }
            }
        }
        updateFile();
    }


    /*Delete boat from file */
    public void deleteBoat(int a_boatID) {
        for (Member m : members_list) {
            m.ownedBoats.removeIf(b -> b.getBoatID() == a_boatID);
        }
        updateFile();
    }

    /* Counting boats for member given in input */
    public int getNumberOfBoats(Member a_member) {      //changed
        return a_member.ownedBoats.size();
    }

    /* Write content of Linked List in file */
    public void updateFile() {
        try {
            // create a writer
            FileWriter fileWritter = new FileWriter("database.txt",false);
            BufferedWriter bw = new BufferedWriter(fileWritter);

            // append text to file
            for (Member member : members_list) {
                bw.write(member.getMemberID() + splitter + member.getName() + splitter + member.getPersonalNum() + splitter);
                for (Boat b : member.ownedBoats) {
                    bw.write(b.getBoatID() + splitter + b.getBoatType() + splitter + b.getBoatLength() + splitter);
                }
                bw.newLine();
            }
            // close the writer
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        readFile();
    }


    /* Read content of file and save in ArrayList */
    public void readFile() {
        try {
            // create a reader instance
            File file = new File("database.txt");
                file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader("database.txt"));
            members_list = new ArrayList<>();

            String entry;

            while ((entry = br.readLine()) != null) {
                Member m_member = readEntry(entry);
                members_list.add(m_member);
            }
            // close the reader
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public Member readEntry (String a_entry) {
        Member m_member = new Member();
        String[] entryArray = a_entry.split(splitter);

        m_member.setMemberID(Integer.parseInt(entryArray[0]));
        m_member.setName(entryArray[1]);
        m_member.setPersonalNum(entryArray[2]);
        for (int i = 3; i < entryArray.length; i++) {
            if (i % 3 == 0) {
                Boat b = new Boat();
                b.setBoatID(Integer.parseInt(entryArray[i]));
                b.setBoatType(Boat.Type.valueOf(entryArray[i + 1]));
                b.setBoatLength(Integer.parseInt(entryArray[i + 2]));
                m_member.ownedBoats.add(b);
            }
        }
        return m_member;
    }


    /*Returns true if id already in the file */
    public boolean notExistInFile(int a_ID){
        for(Member m : members_list){
            if (m.getMemberID() == a_ID)
                return false;
            for(Boat b : m.ownedBoats){
                if (b.getBoatID() == a_ID)
                    return false;
            }
        }
        return true;
    }

    public int idGenerator() {
        Random rand = new Random();
        int randomID = rand.nextInt(9999 - 1000) + 1000; //random number generator
        if(!notExistInFile(randomID))
            randomID = rand.nextInt(9999 - 1000) + 1000;
        return randomID;
    }

    public boolean checkPersonalNumber(String input) {
        boolean isCorrect = true;
        for (int i = 0; i < input.length(); i++) { //Check every char in the input for a letter
            if (Character.isLetter(input.charAt(i))) {
                isCorrect = false;
            }
        }
        if (input.length() != 10) {
            isCorrect = false;
        }
        return isCorrect;
    }
}

