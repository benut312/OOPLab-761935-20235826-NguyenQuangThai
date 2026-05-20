package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media media1, Media media2) {
        // Sort by cost descending, then by title A-Z.
        int costComparison = Float.compare(media2.getCost(), media1.getCost());
        if (costComparison != 0) {
            return costComparison;
        }
        return media1.getTitle().compareToIgnoreCase(media2.getTitle());
    }
}
