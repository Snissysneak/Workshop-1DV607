package model;
import java.util.ArrayList;

public class Member {
    private String name;
    private String personalNum;
    private int memberID;
    public ArrayList<Boat> ownedBoats;        //new

    public Member() { this.ownedBoats = new ArrayList<>();}     //new

    public void setName(String a_name) {
        this.name = a_name;
    }

    public String getName() {
        return name;
    }

    public void setPersonalNum(String a_personalNum) {this.personalNum = a_personalNum; }

    public String getPersonalNum() {
        return personalNum;
    }

    public void setMemberID(int a_memberID) {
        this.memberID = a_memberID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void addBoat (Boat a_boat) {ownedBoats.add(a_boat);}     //new

}
