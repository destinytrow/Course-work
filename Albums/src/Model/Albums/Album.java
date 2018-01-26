package Model.Albums;

public class Album {

    private int albumID; private String name;

    public Album(int albumID, String name) {
        this.albumID = albumID;
        this.name = name;
    }

    public int getAlbumID() {
        return albumID;
    }

    public String getName() {
        return name;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
