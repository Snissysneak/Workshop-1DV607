package model;

import java.math.BigInteger;

public class Boat{
    private BigInteger boatID;
    private Type boatType;
    private int boatLength;

    public enum Type{   //new
        Sailboat,
        Motorsailer,
        Kayak_Canoe,
        Other
    }

    public Boat() { }

    public void setBoatID(BigInteger boatID) {
        this.boatID = boatID;
    }

    public BigInteger getBoatID() {
        return boatID;
    }

    public void setBoatType(Type a_boatType){this.boatType = a_boatType; }

    public Type getBoatType() {
        return boatType;
    }

    public void setBoatLength(int boatLength) {
        this.boatLength = boatLength;
    }

    public int getBoatLength() {
        return boatLength;
    }
}
