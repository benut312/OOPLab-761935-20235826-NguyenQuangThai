package hust.soict.hedspi.aims.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.hedspi.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (itemsInStore.contains(media)) {
            System.out.println("The media already exists in the store.");
            return;
        }
        itemsInStore.add(media);
        System.out.println("The media has been added to the store.");
    }

    public void removeMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            System.out.println("The media was not found in the store.");
            return;
        }
        itemsInStore.remove(media);
        System.out.println("The media has been removed from the store.");
    }

    public Media findMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.isMatch(title)) {
                return media;
            }
        }
        return null;
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            for (int i = 0; i < itemsInStore.size(); i++) {
                System.out.println((i + 1) + ". " + itemsInStore.get(i));
            }
        }
        System.out.println("***************************************************");
    }

    public List<Media> getItemsInStore() {
        return Collections.unmodifiableList(itemsInStore);
    }
}
