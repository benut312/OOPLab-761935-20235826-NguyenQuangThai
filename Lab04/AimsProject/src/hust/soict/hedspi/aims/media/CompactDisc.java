package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public CompactDisc(String title, String category, String artist, String director, float cost) {
        super(title, category, director, 0, cost);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("The track already exists.");
            return;
        }
        tracks.add(track);
        System.out.println("The track has been added.");
    }

    public void removeTrack(Track track) {
        if (!tracks.contains(track)) {
            System.out.println("The track was not found.");
            return;
        }
        tracks.remove(track);
        System.out.println("The track has been removed.");
    }

    @Override
    public int getLength() {
        // CD length is the sum of all track lengths.
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("Cannot play CD: " + getTitle());
            System.out.println("CD length is non-positive.");
            return;
        }
        System.out.println("Playing CD: " + getTitle());
        System.out.println("CD length: " + getLength());
        for (Track track : tracks) {
            track.play();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CD - ").append(getTitle()).append(" - ").append(getCategory())
                .append(" - ").append(artist).append(" - ").append(getDirector())
                .append(" - ").append(getLength()).append(": ").append(getCost()).append(" $");
        if (!tracks.isEmpty()) {
            builder.append(System.lineSeparator()).append("Tracks:");
            for (Track track : tracks) {
                builder.append(System.lineSeparator()).append("  ").append(track);
            }
        }
        return builder.toString();
    }
}
