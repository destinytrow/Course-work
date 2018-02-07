package Model.songs;

import Model.DatabaseConnection;
import Model.Playlist.Playlist;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class TrackPlayer
{
    private ArrayList<MediaPlayer> queue = new ArrayList<>();
    public int songIndex = 0;
    private boolean isPlaying = false;

    public TrackPlayer()
    {
        final DatabaseConnection dbConn = new DatabaseConnection("MusicPlayer.db");
        queueFromSongs(dbConn.getAllSongs());
        dbConn.disconnect();
    }

    public void loadPlaylist(Playlist playlist)
    {
        final DatabaseConnection dbConn = new DatabaseConnection("MusicPlayer.db");
        queueFromSongs(dbConn.songsInPlaylist(playlist));
        dbConn.disconnect();
    }

    public TrackPlayer(Playlist playlist)
    {
        loadPlaylist(playlist);
    }

    public void queueFromSongs(ArrayList<Song> songs)
    {
        for (Song song : songs)
        {
            URL url;
            try { url = new File("U:/computer science/coursework/Albums/Songs/" + song.getTitle() + ".mp3").toURI().toURL(); } // we need to add a bit that changes what the song index is. this could be done on the main tables clicking bit, on action. but i dont know how 
            catch (Exception e) { e.printStackTrace(); return; }
            final Media media = new Media(url.toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            queue.add(mediaPlayer);
        }
    }

    public void play()
    {
        if (!isPlaying) queue.get(songIndex).play();
        isPlaying = true;
        System.out.println("playinggg");
    }

    public void pause()
    {
        if (isPlaying) queue.get(songIndex).pause();
        isPlaying = false;
        System.out.println("Pausinggg");
    }

    public void togglePlayPause()
    {
        if (isPlaying) pause();
        else play();

    }



}
