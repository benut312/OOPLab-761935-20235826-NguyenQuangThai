package hust.soict.hedspi.test.disc;

import java.util.ArrayList;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;

public class PolymorphismTest {
    public static void main(String[] args) {
        ArrayList<Media> mediae = new ArrayList<Media>();

        mediae.add(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));

        CompactDisc cd = new CompactDisc("Best of Java", "Education", "SOICT Band", "HUST", 15.00f);
        cd.addTrack(new Track("Inheritance", 4));
        mediae.add(cd);

        Book book = new Book("Clean Code", "Programming", 35.50f);
        book.addAuthor("Robert C. Martin");
        mediae.add(book);

        for (Media media : mediae) {
            System.out.println(media.toString());
        }
    }
}
