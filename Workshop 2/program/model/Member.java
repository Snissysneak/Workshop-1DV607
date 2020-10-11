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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalNum() {
        return personalNum;
    }

    public void setPersonalNum(String personalNum) {this.personalNum = personalNum; }

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
