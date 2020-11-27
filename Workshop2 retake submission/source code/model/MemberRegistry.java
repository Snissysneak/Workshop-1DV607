package model;

import java.math.BigInteger;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class MemberRegistry {

    private ArrayList<Member> membersList;
    private final String splitter = " ";

    public MemberRegistry() {
    }

    /* Add new entry member to the file. New entry contains memberID, name and person number*/
    public void addMember(Member a_member) {
        membersList.add(a_member);
        updateFile();
    }

    /* Change members info in the record  and write to the file */
    public void changeMember(Member a_member) {
        int index = -1;
        for (Member m : membersList){
            if(m.getMemberID() == a_member.getMemberID()){
                index = membersList.indexOf(m);
            }
        }
        membersList.remove(index);
        membersList.add(index, a_member);
        updateFile();
    }

    /* Removes members record from the file */
    public void deleteMember(Member a_member) {
        membersList.removeIf(tempMember -> tempMember.equals(a_member));
        updateFile();
    }

    /* Return member with given person number */
    public Member getMember(int a_memberID) {
        Member member = new Member();
        for (Member m : membersList){
            if(m.getMemberID() == a_memberID){
                return m;
            }
        }
        return member;
    }

    /* Add Boat to file */
    public void addBoat(int a_memberID, Boat a_boat) {
        for (Member m : membersList){
            if(m.getMemberID() == a_memberID){
                m.addBoat(a_boat);
            }
        }
        updateFile();
    }

    /*C hange members info in the record  and write to the file */
    public void changeBoat (Boat a_boat) {
        int index = -1;
        for (Member m : membersList){
            for (Boat b : m.ownedBoats){
                if(b.getBoatID() == a_boat.getBoatID()) {
                    index = m.ownedBoats.indexOf(b);
                }
            }
            m.ownedBoats.remove(index);
            m.ownedBoats.add(index, a_boat);
        }
        updateFile();
    }

    /* Delete boat from file */
    public void deleteBoat(int a_boatID) {
        for (Member m : membersList) {
            m.ownedBoats.removeIf(b -> b.getBoatID() == a_boatID);
        }
        updateFile();
    }

    /* Counting boats for member given in input */
    public int getNumberOfBoats(Member a_member) {      //changed
        return a_member.ownedBoats.size();
    }

    /* Return list of members with their boats */
    public ArrayList<Member> getMembersList(){
        return membersList;
    }

    /* Write data from membersList to file */
    public void updateFile() {
        try {
            FileWriter fileWritter = new FileWriter("database.txt",false);
            BufferedWriter bw = new BufferedWriter(fileWritter);

            for (Member member : membersList) {
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

    /* Read content of file and save in membersList */
    public void readFile() {
        try {
            File file = new File("database.txt");
                file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader("database.txt"));
            membersList = new ArrayList<>();

            String entry;

            while ((entry = br.readLine()) != null) {
                Member m_member = readEntry(entry);
                membersList.add(m_member);
            }
            // close the reader
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /* Method transform entry from file into objects in membersList */
    public Member readEntry (String a_entry) {
        Member m_member = new Member();
        String[] entryArray = a_entry.split(splitter);

        int memberID = Integer.parseInt(entryArray[0]);
        m_member.setMemberID(memberID);
        m_member.setName(entryArray[1]);
        m_member.setPersonalNum(new BigInteger(entryArray[2]));
        for (int i = 3; i < entryArray.length; i++) {
            if (i % 3 == 0) {
                Boat b = new Boat();
                int boatID = Integer.parseInt(entryArray[i]);
                b.setBoatID(boatID);
                b.setBoatType(Boat.Type.valueOf(entryArray[i + 1]));
                b.setBoatLength(Integer.parseInt(entryArray[i + 2]));
                m_member.ownedBoats.add(b);
            }
        }
        return m_member;
    }

    /* Return true when given id exist in file */
    public boolean idExistInFile(int a_ID){
        for(Member m : membersList){
            if (m.getMemberID() == a_ID)
                return true;
            for(Boat b : m.ownedBoats){
                if (b.getBoatID() == a_ID)
                    return true;
            }
        }
        return false;
    }
    /* Return true when given person number exist in file */
    public boolean personNumberExistInFile(BigInteger personNum) {
        boolean exist = false;
        for (Member m : membersList) {
            if (m.getPersonalNum().equals(personNum))
                exist = true;
        }
        return exist;
    }

    /* Generate unique id for objects */
    public int idGenerator(){
        Random rand = new Random();
        int randomID = rand.nextInt(9999 - 1000) + 1000;

        if(!idExistInFile(randomID))
            randomID = rand.nextInt(9999 - 1000) + 1000;
        return randomID;
    }

    /* Method check person number */
    public boolean checkPersonalNumber(BigInteger a_input) {
        String input = a_input.toString();
        boolean isCorrect = true;
        if (input.length() != 10) {
            isCorrect = false;
        }
        return isCorrect;
    }
}

