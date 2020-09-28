import java.io.*;
import java.util.Scanner;

public class text_file_test {
    public static void main(String[] args) throws IOException {

        MemberRegistry db = new MemberRegistry();
        db.addMember("John","2234",001);
        db.addMember("Helene", "23232",002);
        db.addMember("Jack", "23232",003);
        db.updateTXT();
        db.getComposeList();


    }
}
