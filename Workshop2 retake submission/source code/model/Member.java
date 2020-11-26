package model;
import java.util.ArrayList;
import java.math.BigInteger;

public class Member {
    private String name;
    private BigInteger personalNum;
    private int memberID;
    public ArrayList<Boat> ownedBoats;

    public Member() { this.ownedBoats = new ArrayList<>();}

    /* set name */
    public void setName(String a_name) { this.name = a_name; }

    /* get name */
    public String getName() { return name; }

    /* Set person number */
    public void setPersonalNum(BigInteger a_personalNum) {  this.personalNum = a_personalNum; }

    /* get person number */
    public BigInteger getPersonalNum() { return personalNum; }

    /* set id */
    public void setMemberID(int a_memberID) { this.memberID = a_memberID; }

    /* get id */
    public int getMemberID() { return memberID; }

    /* add boat */
    public void addBoat (Boat a_boat) { ownedBoats.add(a_boat); }

}
