import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MemberRegistry extends Member{
    ArrayList<Member> members = new ArrayList<Member>();

    StringBuilder sb;

    public MemberRegistry() {

    }

    public MemberRegistry(ArrayList<Member> members) {
        this.members = members;
    }

    public void addMember(String name, String personalNum, int memberID) {
        Member person = new Member(name, personalNum, memberID);

        members.add(person);
    }

    public void deleteMember() {

    }

    public void changeMember() {

    }

    public void updateTXT() {
        try {
            File myObj = new File("members.txt");
            if (myObj.createNewFile()) {

            } else {
                //System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void getComposeList() {

    }

    public void getVerboseList() {

    }
}
