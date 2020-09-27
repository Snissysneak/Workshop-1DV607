import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MemberRegistry extends Member{
    Member member;
    //ArrayList<Member> members = new ArrayList<Member>();

    StringBuilder sb; // not currently in use but leave it for now

    public MemberRegistry() {

    }

    public MemberRegistry(/*ArrayList<Member> members*/Member member) {
        //this.members = members; //leave this
        this.member = member;
    }
                /*
                This is the format we are going to use in the txt file
                Textfile standard for member:   [id]|name|/personal number/-boats-
                Textfile standard for boats:    _id_<typ>%length%
                */

    public void addMember(String name, String personalNum, int memberID) {
        member = new Member(name, personalNum, memberID); //create a new member for usage in updating the txt file

        //members.add(person);
        updateTXT(); //call for method
    }

    public void deleteMember() {

    }

    public void changeMember() {

    }

    public void updateTXT() {
        try {
            File myFile = new File("members.txt"); //new files created
            PrintWriter pw = new PrintWriter(myFile); //to write stuff to the file
            String str = "[" + member.getMemberID() + "]|" + member.getName() + "|/" + member.getPersonalNum() + "/";          //Needs more content but works

            /*while((line = bufferedReader.readLine()) != null)
            {
                sb.append(line); //append the lines to the string       //This does not work fuck this shit
                sb.append('\n'); //append new line
            }*/

            pw.println(str); //add content to file

            pw.close(); //close writer

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
