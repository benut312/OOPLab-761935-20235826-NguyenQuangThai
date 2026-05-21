package hust.soict.hedspi.aims.screen.manager;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.media.Track;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                JDialog dialog = new JDialog();
                dialog.setTitle("Play Media");
                dialog.setSize(400, 200);
                dialog.setLocationRelativeTo(MediaStore.this);
                dialog.setModal(true);

                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setText(getPlaybackMessage());

                dialog.add(new JScrollPane(textArea));
                dialog.setVisible(true);
            });
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private String getPlaybackMessage() {
        if (media instanceof DigitalVideoDisc) {
            DigitalVideoDisc dvd = (DigitalVideoDisc) media;
            if (dvd.getLength() <= 0) {
                return "Cannot play DVD: " + dvd.getTitle()
                        + System.lineSeparator()
                        + "DVD length is non-positive.";
            }
            return "Playing DVD: " + dvd.getTitle()
                    + System.lineSeparator()
                    + "DVD length: " + dvd.getLength();
        }

        if (media instanceof CompactDisc) {
            CompactDisc cd = (CompactDisc) media;
            if (cd.getLength() <= 0) {
                return "Cannot play CD: " + cd.getTitle()
                        + System.lineSeparator()
                        + "CD length is non-positive.";
            }

            StringBuilder builder = new StringBuilder();
            builder.append("Playing CD: ").append(cd.getTitle())
                    .append(System.lineSeparator())
                    .append("CD length: ").append(cd.getLength());

            for (Track track : cd.getTracks()) {
                builder.append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append(formatTrackPlayback(track));
            }
            return builder.toString();
        }

        return "Playing: " + media.getTitle();
    }

    private String formatTrackPlayback(Track track) {
        if (track.getLength() <= 0) {
            return "Cannot play track: " + track.getTitle()
                    + System.lineSeparator()
                    + "Track length is non-positive.";
        }
        return "Playing track: " + track.getTitle()
                + System.lineSeparator()
                + "Track length: " + track.getLength();
    }
}
