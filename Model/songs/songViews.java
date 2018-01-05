package Model.songs;
import javafx.beans.property.SimpleStringProperty;

public class songViews {

        private int songID;
        private final SimpleStringProperty title;
        private final SimpleStringProperty artist;
        private int duration;
        private int albumID;

        public songViews (int songID, String title, String artist, int duration, int albumID) {
            this.songID = songID;
            this.title = new SimpleStringProperty(title);
            this.artist = new SimpleStringProperty(artist);
            this.duration = (duration);
            this.albumID = (albumID);
        }

        public int getId() { return songID; }
        public String gettitle() { return title.get(); }
        public String getartist() { return artist.get(); }
        public int getduration() { return duration; }
        public int getalbumID() { return albumID;}


}
