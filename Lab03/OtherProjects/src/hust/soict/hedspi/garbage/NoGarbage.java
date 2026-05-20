package hust.soict.hedspi.garbage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NoGarbage {
    public static void main(String[] args) throws IOException {
        String filename = args.length > 0 ? args[0] : "lab03_pdf_text.txt";
        StringBuffer outputString = new StringBuffer();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                outputString.append(line).append("\n");
            }
        }

        System.out.println("Read " + outputString.length() + " characters.");
    }
}
