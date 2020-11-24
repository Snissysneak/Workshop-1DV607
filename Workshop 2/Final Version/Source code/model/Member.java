package model;
import java.util.ArrayList;
import java.math.BigInteger;

public class Member {
    private String name;
    private String personalNum;
    private BigInteger memberID;
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

    public void setMemberID(BigInteger a_memberID) {
        this.memberID = a_memberID;
    }

    public BigInteger getMemberID() {
        return memberID;
    }

    public void addBoat (Boat a_boat) {ownedBoats.add(a_boat);}     //new

}
