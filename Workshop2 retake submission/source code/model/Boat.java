package model;

import java.math.BigInteger;

public class Boat{
    private int boatID;
    private Type boatType;
    private int boatLength;

    public enum Type{
        Sailboat,
        Motorsailer,
        Kayak_Canoe,
        Other
    }

    public Boat() { }

    /* set id */
    public void setBoatID(int boatID) { this.boatID = boatID; }

    /* get id */
    public int getBoatID() { return boatID; }

    /* set type */
    public void setBoatType(Type a_boatType){this.boatType = a_boatType; }

    /* get type */
    public Type getBoatType() { return boatType; }

    /* set length */
    public void setBoatLength(int boatLength) { this.boatLength = boatLength; }

    /* get length */
    public int getBoatLength() { return boatLength; }
}
