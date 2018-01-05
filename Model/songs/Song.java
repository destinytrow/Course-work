package Model.songs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Song {
   	/*private Integer songID;
   	private String title;
   	private String artist;
   	private int duration;
   	private int albumID;

    public Song(int songID, String title, String artist, int duration, int albumID) {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.albumID = albumID;
    }*/

   	private SimpleIntegerProperty songID;
   	private SimpleStringProperty title;
   	private SimpleStringProperty artist;
   	private SimpleIntegerProperty duration;
   	private SimpleIntegerProperty albumID;

    public Song(Integer songID, String title, String artist, Integer duration, Integer albumID)
    {
        this.songID = new SimpleIntegerProperty(songID);
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.duration = new SimpleIntegerProperty(duration);
        this.albumID = new SimpleIntegerProperty(albumID);
    }

    public Integer getSongID() { return songID.get(); }
    public String getTitle() { return title.get(); }
    public String getArtist() { return artist.get(); }
    public Integer getDuration() { return duration.get(); }
    public Integer getAlbumID() { return albumID.get(); }
}
