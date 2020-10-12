package model;

import java.util.Random;

public class Member {
    private String name;
    private String personalNum;
    private int memberID;

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
        Random rand = new Random();
        return rand.nextInt(9999 - 1000) + 1000; //random number gen
    }

    public boolean checkLetter(String input) {
        boolean isNumber = true;
        for (int i = 0; i < input.length(); i++) { //Check every char in the input for a letter
            if (Character.isLetter(input.charAt(i))) {
                isNumber = false;
            }
        }
        return isNumber;
    }
}
