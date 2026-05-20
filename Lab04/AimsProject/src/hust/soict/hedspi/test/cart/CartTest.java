package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        Book book = new Book("Clean Code", "Programming", 35.50f);
        book.addAuthor("Robert C. Martin");

        CompactDisc cd = new CompactDisc("Best of Java", "Education", "SOICT Band", "HUST", 15.00f);
        cd.addTrack(new Track("Inheritance", 4));
        cd.addTrack(new Track("Polymorphism", 5));

        cart.addMedia(dvd1);
        cart.addMedia(dvd2);
        cart.addMedia(book);
        cart.addMedia(cd);

        cart.print();
        cart.searchById(dvd1.getId());
        cart.searchByTitle("star");

        cart.sortByTitleCost();
        cart.print();

        cart.sortByCostTitle();
        cart.print();
    }
}
