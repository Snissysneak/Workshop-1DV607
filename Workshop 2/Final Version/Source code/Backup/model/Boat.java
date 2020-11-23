package model;
public class Boat{
    private int boatID;
    private Type boatType;
    private int boatLength;

    public enum Type{   //new
        Sailboat,
        Motorsailer,
        Kayak_Canoe,
        Other
    }

    public Boat() { }

    public void setBoatID(int boatID) {
        this.boatID = boatID;
    }

    public int getBoatID() {
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
