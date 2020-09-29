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

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public int getBoatLength() {
        return boatLength;
    }

    public void setBoatLength(int boatLength) {
        this.boatLength = boatLength;
    }
}
