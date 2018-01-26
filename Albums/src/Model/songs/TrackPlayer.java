package Model.songs;

import Model.DatabaseConnection;
import Model.Playlist.Playlist;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class TrackPlayer
{
    private static ArrayList<MediaPlayer> queue = new ArrayList<>();
    private static int songIndex = 0;
    private static boolean isPlaying = false;

    public TrackPlayer(Playlist playlist)
    {
        final DatabaseConnection dbConn = new DatabaseConnection("MusicPlayer.db");
        for (Song song : dbConn.songsInPlaylist(playlist))
        {
            final Media media = new Media("U:\\computer science\\coursework\\Albums\\Songs\\" + song.getTitle() + ".mp3");
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            queue.add(mediaPlayer);
        }
    }

    public static void play()
    {
        if (!isPlaying) queue.get(songIndex).play();
        isPlaying = true;
    }

    public static void pause()
    {
        if (isPlaying) queue.get(songIndex).pause();
        isPlaying = false;
    }



}
