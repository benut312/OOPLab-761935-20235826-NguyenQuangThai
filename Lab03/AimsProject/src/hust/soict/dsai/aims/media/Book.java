package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<String>();
    private String content = "";

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    public List<String> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }

    public int getContentLength() {
        String trimmedContent = content.trim();
        if (trimmedContent.isEmpty()) {
            return 0;
        }
        return trimmedContent.split("\\s+").length;
    }

    public void addAuthor(String authorName) {
        if (authors.contains(authorName)) {
            System.out.println("The author already exists.");
            return;
        }
        authors.add(authorName);
        System.out.println("The author has been added.");
    }

    public void removeAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            System.out.println("The author was not found.");
            return;
        }
        authors.remove(authorName);
        System.out.println("The author has been removed.");
    }

    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + " - " + authors
                + " - content length: " + getContentLength() + ": " + getCost() + " $";
    }
}
