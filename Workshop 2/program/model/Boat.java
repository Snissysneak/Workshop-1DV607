package model;
public class Boat{
    int boatID;
    String boatType;
    int boatLength;

    public Boat() {

    }
    public Boat(int boatID, String boatType, int boatLength) {
        this.boatID = boatID;
        this.boatType = boatType;
        this.boatLength = boatLength;
    }

    public void setBoatID(int boatID) {
        this.boatID = boatID;
    }

    public int getBoatID() {
        return boatID;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType)throws NumberFormatException{

        if (boatType.equals("1"))
            this.boatType = "Sailboat";
        else if (boatType.equals("2"))
            this.boatType = "Motorsailer";
        else if (boatType.equals("3"))
            this.boatType = "Kayak/Canoe";
        else if (boatType.equals("4"))
            this.boatType = "Other";
        else {
            throw new NumberFormatException();
        }
    }

    public int getBoatLength() {
        return boatLength;
    }

    public void setBoatLength(int boatLength) {
        this.boatLength = boatLength;
    }


}
