package model;
import java.io.*;
import java.util.Scanner;

public class MemberRegistry {
    Member member;

    public MemberRegistry() {

    }

    public MemberRegistry(Member member) {
        this.member = member;
    }

    public void addMember(String name, String personalNum, int memberID) {
        member = new Member(name, personalNum, memberID); //create a new member for usage in updating the txt file
        try {
            String str = member.getMemberID() + " " + member.getName() + " " + member.getPersonalNum();   //The content we want to add.

            FileWriter fstream = new FileWriter("members.txt",true);    //Use FileWriter to create file and BufferedWriter so we don't overwrite the file with new content.
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(str + "\n");
            out.close();    //Close BufferedWriter

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(String input) {
        File temp = new File("temp.txt");
        File mainFile = new File("members.txt");
        try {
            FileWriter fw = new FileWriter(temp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("members.txt"));
            x.useDelimiter("[,\n]");

            while (x.hasNextLine()) {
                String line = x.nextLine();     //whole line from file
                if (line.isEmpty())
                    continue;

                String[] entries = line.split("\\s");   //line into array
                String newLine = "";
                if(!entries[0].equals(input)){
                    for (int i = 0; i < entries.length; i++)
                    {
                        String current = entries[i];
                        if(!current.equals(input)) {        //PrintWrite entries which are different than input
                            if(!current.isEmpty())
                                newLine = newLine + current + " ";      //PW entry

                            else {
                                continue;
                            }
                        }

                    }System.out.println(newLine);   //for test purpose
                }
                else {      //when memberID equals input do nothing
                    continue;
                }
                pw.println(newLine);
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
        Member mem = new Member(); //Call for member class to access idGenerator()
        int boatID = mem.idGenerator();

        Boat newBoat = new Boat(boatID, boatType, boatLength);

        try{
            File temp = new File("temp.txt"); //Creat temporary file to write to

            FileWriter fw = new FileWriter(temp, false);
            FileInputStream fStream = new FileInputStream("members.txt");
            DataInputStream in = new DataInputStream(fStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;

            while ((strLine = br.readLine()) != null)   {
                if(strLine.contains(input)) { //If the member is in the current line we want to add the boat at the end
                    fw.append(strLine + " " + newBoat.getBoatID() + " " + newBoat.getBoatType() + " " + newBoat.getBoatLength());
                }
                else {
                    fw.append(strLine); //Else we just want to get the normal line
                }
                fw.append("\n");
            }
            fw.close();
            in.close();

            File original = new File("members.txt"); //Now we want to read from the temporary file to the original file

            FileWriter fileWriter = new FileWriter(original, false);
            FileInputStream fileStream = new FileInputStream("temp.txt");
            DataInputStream inData = new DataInputStream(fileStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inData));

            String stringLine;

            while ((stringLine = bReader.readLine()) != null)   { //Line for line add to the original file
                fileWriter.append(stringLine + "\n");
            }
            fileWriter.close();
            inData.close();

            temp.delete(); //delete the temporary file
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void deleteBoat(String input) {

        File temp = new File("temp.txt");
        File mainFile = new File("members.txt");
        try {
            FileWriter fw = new FileWriter(temp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("members.txt"));
            x.useDelimiter("[,\n]");

            while (x.hasNextLine()) {
                String line = x.nextLine();     //whole line from file
                if (line.isEmpty())
                    continue;

                String[] entries = line.split("\\s");   //line into array
                String newLine = "";

                for (int i = 0; i < entries.length; i++) {
                    String current = entries[i];
                    if (!current.equals(input)) {        //PrintWrite entries which are different than input
                        if (!current.isEmpty())
                            newLine = newLine + current + " ";      //PW entry

                        else {
                            continue;
                        }
                    }
                    /*If current entry = input --> delete entry + 2 next entries (boat type and length */
                    else {
                        entries[i] = "";
                        entries[i + 1] = "";
                        entries[i + 2] = "";
                    }
                }
                pw.println(newLine);
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

    public void updateRegistry(String entryID, String name_or_type, String personalNumber_or_length) {
        File temp = new File("temp.txt");
        File mainFile = new File("members.txt");
        String line;
        try {
            FileWriter fw = new FileWriter(temp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("members.txt"));
            x.useDelimiter("[,\n]");

            while (x.hasNextLine()) {
                line = x.nextLine();     //whole line from file
                if (line.isEmpty())
                    continue;

                String[] entries = line.split("\\s");   //line into array
                String newLine = "";

                for (int i = 0; i < entries.length; i++) {
                    String current = entries[i];
                    if (!current.equals(entryID)) {        //PrintWrite entries which are different than input
                        if (!current.isEmpty())
                            newLine = newLine + current + " ";      //PW entry
                    }
                    else {
                        entries[i] = entryID;
                        entries[i + 1] = name_or_type;
                        entries[i + 2] = personalNumber_or_length;
                        newLine = newLine + entryID + " ";
                    }
                }
                pw.println(newLine);
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

}
