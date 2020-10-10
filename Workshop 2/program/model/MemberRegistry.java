package model;
import java.io.*;
import java.util.Scanner;

public class MemberRegistry extends Member{
    Member member;

    StringBuilder sb; // not currently in use but leave it for now

    public MemberRegistry() {

    }

    public MemberRegistry(Member member) {
        this.member = member;
    }
                /*
                This is the format we are going to use in the txt file
                Textfile standard for member:   [id]|name|/personal number/-boats-
                Textfile standard for boats:    _id_<typ>%length%
                */

    public void addMember(String name, String personalNum, int memberID) {
        member = new Member(name, personalNum, memberID); //create a new member for usage in updating the txt file
        try {
            String str = member.getMemberID() + "," + member.getName() + "," + member.getPersonalNum();   //The content we want to add.

            FileWriter fstream = new FileWriter("members.txt",true);    //Use FileWriter to create file and BufferedWriter so we don't overwrite the file with new content.
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(str + "\n");
            out.close();    //Close BufferedWriter

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(String input) throws FileNotFoundException {
        File temp = new File("temp.txt");
        File mainFile = new File("members.txt");
        String id, name, personalNumber;
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

    public void addBoat(String boatType, int boatLength, String input) {
        //This is a bad solution for adding boats to the txt file.

        //It reades the information from the original file and then
        //changes the information in the temp file and lastly takes
        //the information from the temp file to the original file
        int boatID = idGenerator();
        Boat newBoat = new Boat(boatID, boatType, boatLength);

        try{
            File temp = new File("temp.txt");

            FileWriter fw = new FileWriter(temp, false);
            FileInputStream fstream = new FileInputStream("members.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;

            while ((strLine = br.readLine()) != null)   {
                if(strLine.contains(input)) {
                    fw.append(strLine + "," + newBoat.getBoatID() + "," + newBoat.getBoatType() + "," + newBoat.getBoatLength());
                }
                else {
                    fw.append(strLine);
                }
                fw.append("\n");
            }
            fw.close();
            in.close();

            File original = new File("members.txt");

            FileWriter fileWriter = new FileWriter(original, false);
            FileInputStream fileStream = new FileInputStream("temp.txt");
            DataInputStream inData = new DataInputStream(fileStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inData));

            String stringLine;

            while ((stringLine = bReader.readLine()) != null)   {
                fileWriter.append(stringLine + "\n");
            }
            fileWriter.close();
            inData.close();

            temp.delete();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void deleteBoat(String input) {
        File temp = new File("temp.txt");
        File mainFile = new File("members.txt");
        String id, boatType, boatLength;
        try {
            FileWriter fw = new FileWriter(temp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("members.txt"));
            x.useDelimiter("[,\n]");
            String str = "";
            boolean rowBreak = false;

            while(x.hasNext()) {
                while (x.hasNext()) {
                    id = x.next();
                    boatType = x.next();
                    boatLength = x.next();
                    if (boatLength.contains("/")) {
                        rowBreak = true;
                        
                    }
                    if (!id.equals(input)) {
                        System.out.println(str + id + "," + boatType + "," + boatLength + ",");
                        str += id + "," + boatType + "," + boatLength + ",";
                    }
                    if (rowBreak) {
                        str += ("\n");
                    }
                }

                pw.println(str);
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

    public void updateRegistry(String rvID, String rvName, String rvPerNum) {
        File temp = new File("temp.txt");
        File mainFile = new File("members.txt");
        String id, name, personalNumber, line;
        try {
            FileWriter fw = new FileWriter(temp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("members.txt"));
            x.useDelimiter("[,\n]");
            Scanner scan = new Scanner(System.in);

            while(x.hasNext()) {
                id = x.next();
                name = x.next();
                personalNumber = x.next();
                if(id.equals(rvID)) {
                    pw.println(rvID + "," + rvName + "," + rvPerNum);
                }
                else {
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

        removeBlankSpaces();
    }

    public void removeBlankSpaces() {
        Scanner file;
        PrintWriter writer;

        try {

            file = new Scanner(new File("members.txt"));
            writer = new PrintWriter("members2.txt");

            while (file.hasNext()) {
                String line = file.nextLine();
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.write("\n");
                }
            }

            file.close();
            writer.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        File file1 = new File("members.txt");
        File file2 = new File("members2.txt");

        file1.delete();
        file2.renameTo(file1);
    }
}
