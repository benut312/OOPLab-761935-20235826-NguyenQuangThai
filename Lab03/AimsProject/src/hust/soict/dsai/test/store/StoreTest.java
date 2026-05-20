package hust.soict.dsai.test.store;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        Book book = new Book("Clean Code", "Programming", 35.50f);

        store.addMedia(dvd);
        store.addMedia(book);
        store.printStore();

        store.removeMedia(dvd);
        store.printStore();
    }
}
