package Model.Playlist;

public class Playlist
{
    private int playistID;
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public int getPlayistID() { return playistID; }

    public Playlist(int playistID, String name, String description)
    {
        this.playistID = playistID;
        this.name = name;
        this.description = description;
    }
}

