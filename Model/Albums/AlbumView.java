package Model.Albums;

import javafx.beans.property.SimpleStringProperty;

public class AlbumView {

    private int albumID;
    private final SimpleStringProperty name;

    public AlbumView (int albumID, String name) {
        this.albumID = albumID;
        this.name = new SimpleStringProperty(name);
    }

    public int getId() { return albumID; };
    public String getname() { return name.get(); }


}


