package hust.soict.dsai.aims;

import java.util.Scanner;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

public class Aims {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Store STORE = new Store();
    private static final Cart CART = new Cart();

    public static void main(String[] args) {
        seedStore();
        int choice;
        do {
            showMenu();
            choice = readInt();
            switch (choice) {
                case 1:
                    handleViewStore();
                    break;
                case 2:
                    handleUpdateStore();
                    break;
                case 3:
                    handleCart();
                    break;
                case 0:
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void seedStore() {
        STORE.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        STORE.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f));

        Book cleanCode = new Book("Clean Code", "Programming", 35.50f);
        cleanCode.addAuthor("Robert C. Martin");
        cleanCode.setContent("Meaningful names functions comments formatting objects classes systems tests");
        STORE.addMedia(cleanCode);

        CompactDisc cd = new CompactDisc("Best of Java", "Education", "SOICT Band", "HUST", 15.00f);
        cd.addTrack(new Track("Inheritance", 4));
        cd.addTrack(new Track("Polymorphism", 5));
        STORE.addMedia(cd);
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    private static void handleViewStore() {
        int choice;
        do {
            STORE.printStore();
            storeMenu();
            choice = readInt();
            switch (choice) {
                case 1:
                    showMediaDetails();
                    break;
                case 2:
                    addMediaToCartByTitle();
                    break;
                case 3:
                    playMediaFromStore();
                    break;
                case 4:
                    handleCart();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void showMediaDetails() {
        Media media = askMediaFromStore();
        if (media == null) {
            return;
        }
        System.out.println(media);
        int choice;
        do {
            mediaDetailsMenu();
            choice = readInt();
            switch (choice) {
                case 1:
                    CART.addMedia(media);
                    System.out.println("Current cart size: " + CART.getNumberOfMedias());
                    break;
                case 2:
                    play(media);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void addMediaToCartByTitle() {
        Media media = askMediaFromStore();
        if (media == null) {
            return;
        }
        CART.addMedia(media);
        System.out.println("Current cart size: " + CART.getNumberOfMedias());
        if (media instanceof DigitalVideoDisc) {
            System.out.println("Number of DVDs in current cart: " + CART.getNumberOfDVDs());
        }
    }

    private static void playMediaFromStore() {
        Media media = askMediaFromStore();
        if (media != null) {
            play(media);
        }
    }

    private static void handleUpdateStore() {
        int choice;
        do {
            System.out.println("Update store:");
            System.out.println("--------------------------------");
            System.out.println("1. Add media");
            System.out.println("2. Remove media");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            choice = readInt();
            switch (choice) {
                case 1:
                    Media media = createMediaFromInput();
                    if (media != null) {
                        STORE.addMedia(media);
                    }
                    break;
                case 2:
                    Media removedMedia = askMediaFromStore();
                    if (removedMedia != null) {
                        STORE.removeMedia(removedMedia);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void handleCart() {
        int choice;
        do {
            CART.print();
            cartMenu();
            choice = readInt();
            switch (choice) {
                case 1:
                    filterCart();
                    break;
                case 2:
                    sortCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaFromCart();
                    break;
                case 5:
                    CART.clear();
                    System.out.println("An order is created.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void filterCart() {
        System.out.println("1. Filter by id");
        System.out.println("2. Filter by title");
        int choice = readInt();
        if (choice == 1) {
            System.out.print("Enter id: ");
            CART.searchById(readInt());
        } else if (choice == 2) {
            System.out.print("Enter title: ");
            CART.searchByTitle(readLine());
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void sortCart() {
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by cost");
        int choice = readInt();
        if (choice == 1) {
            CART.sortByTitleCost();
            CART.print();
        } else if (choice == 2) {
            CART.sortByCostTitle();
            CART.print();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void removeMediaFromCart() {
        System.out.print("Enter title: ");
        Media media = CART.findByTitle(readLine());
        if (media == null) {
            System.out.println("No media found in cart.");
            return;
        }
        CART.removeMedia(media);
    }

    private static void playMediaFromCart() {
        System.out.print("Enter title: ");
        Media media = CART.findByTitle(readLine());
        if (media == null) {
            System.out.println("No media found in cart.");
            return;
        }
        play(media);
    }

    private static Media askMediaFromStore() {
        System.out.print("Enter title: ");
        Media media = STORE.findMediaByTitle(readLine());
        if (media == null) {
            System.out.println("No media found in store.");
        }
        return media;
    }

    private static void play(Media media) {
        if (!(media instanceof Playable)) {
            System.out.println("This media cannot be played.");
            return;
        }
        ((Playable) media).play();
    }

    private static Media createMediaFromInput() {
        // Create the correct Media subtype from the user's menu choice.
        System.out.println("Choose media type:");
        System.out.println("1. Book");
        System.out.println("2. DigitalVideoDisc");
        System.out.println("3. CompactDisc");
        int type = readInt();
        System.out.print("Title: ");
        String title = readLine();
        System.out.print("Category: ");
        String category = readLine();
        System.out.print("Cost: ");
        float cost = readFloat();

        if (type == 1) {
            Book book = new Book(title, category, cost);
            System.out.print("Authors separated by comma: ");
            String authors = readLine();
            if (!authors.trim().isEmpty()) {
                for (String author : authors.split(",")) {
                    book.addAuthor(author.trim());
                }
            }
            System.out.print("Content: ");
            book.setContent(readLine());
            return book;
        }
        if (type == 2) {
            System.out.print("Director: ");
            String director = readLine();
            System.out.print("Length: ");
            int length = readInt();
            return new DigitalVideoDisc(title, category, director, length, cost);
        }
        if (type == 3) {
            System.out.print("Artist: ");
            String artist = readLine();
            System.out.print("Director: ");
            String director = readLine();
            CompactDisc cd = new CompactDisc(title, category, artist, director, cost);
            System.out.print("Number of tracks: ");
            int numberOfTracks = readInt();
            for (int i = 0; i < numberOfTracks; i++) {
                System.out.print("Track title: ");
                String trackTitle = readLine();
                System.out.print("Track length: ");
                int trackLength = readInt();
                cd.addTrack(new Track(trackTitle, trackLength));
            }
            return cd;
        }
        System.out.println("Invalid media type.");
        return null;
    }

    private static int readInt() {
        while (!SCANNER.hasNextInt()) {
            System.out.println("Please enter an integer.");
            SCANNER.nextLine();
        }
        int value = SCANNER.nextInt();
        SCANNER.nextLine();
        return value;
    }

    private static float readFloat() {
        while (!SCANNER.hasNextFloat()) {
            System.out.println("Please enter a number.");
            SCANNER.nextLine();
        }
        float value = SCANNER.nextFloat();
        SCANNER.nextLine();
        return value;
    }

    private static String readLine() {
        return SCANNER.nextLine();
    }
}
