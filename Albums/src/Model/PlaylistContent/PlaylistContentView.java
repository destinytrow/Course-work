package Model.PlaylistContent;

public class PlaylistContentView {
    private int playlistID;
    private int songID;

    public PlaylistContentView(int playlistID, int songID) {
        this.playlistID= playlistID;
        this.songID = songID;
    }

    public int getplaylistID() {
        return playlistID;
    }

    public int getsongID() {
        return songID;
    }
}



