package Model.Playlist;

import javafx.beans.property.SimpleStringProperty;

public class PlaylistView {

    private int playlistID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty description;


    public PlaylistView (int playlistID, String name, String description) {
        this.playlistID = playlistID;
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
    }

    public int getplaylistID() { return playlistID;}
    public String getname() { return name.get(); }
    public String getdescription() { return description.get(); }


}
