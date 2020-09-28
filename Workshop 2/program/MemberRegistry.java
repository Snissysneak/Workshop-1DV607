import java.io.*;
import java.util.List;
import java.util.Scanner;

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

    public void deleteMember(String input) throws FileNotFoundException {
        File temp = new File("temp.txt");
        File mainFile = new File("members.txt");
        String id = "";
        String name = "";
        String personalNumber = "";
        try {
            FileWriter fw = new FileWriter(temp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("members.txt"));
            x.useDelimiter("[,\n]");

            while(x.hasNext()) {
                id = x.next();
                name = x.next();
                personalNumber = x.next();

                if(!id.equals(input)) {
                    pw.println(id + "," + name + "," + personalNumber);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            mainFile.delete();
            File dump = new File("members.txt");
            temp.renameTo(dump);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeMember(String input) {

    }

    public void updateTXT() {
        try {
            String str = member.getMemberID() + "," + member.getName() + "," + member.getPersonalNum();   //The content we want to add.

            FileWriter fstream = new FileWriter("members.txt",true);    //Use FileWriter to create file and BufferedWriter so we don't overwrite the file with new content.
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(str + "\n");
            out.close();    //Close BufferedWriter

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void getComposeList() throws IOException {

        File file = new File("members.txt");

        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            System.out.println(line);
            String [] entry = line.split(" ");
            System.out.println(entry[1]);
        }
        scan.close();

    }

    public void getVerboseList() {

    }
}
