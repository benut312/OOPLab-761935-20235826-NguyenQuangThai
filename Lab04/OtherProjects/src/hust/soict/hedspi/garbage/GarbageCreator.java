package hust.soict.hedspi.garbage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GarbageCreator {
    public static void main(String[] args) throws IOException {
        String filename = args.length > 0 ? args[0] : "Lab04/_pdf_text.txt";
        String outputString = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                outputString += line + "\n";
            }
        }

        System.out.println("Read " + outputString.length() + " characters.");
    }
}
