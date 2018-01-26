package Model;

import Model.Albums.Album;
import Model.Playlist.Playlist;
import Model.songs.Song;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DatabaseConnection {

    private Connection connection = null;

    public DatabaseConnection(String filename)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
            System.out.println("Database connection successfully established.");
        }
        catch (ClassNotFoundException exception)
        {
            System.out.println("Class not found exception: " + exception.getMessage());
        }
        catch (SQLException exception)
        {
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }


    public PreparedStatement newStatement(String query)
    {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
        }
        catch (SQLException exception)
        {
            System.out.println("Database statement error: " + exception.getMessage());
        }
        return statement;
    }


    public ResultSet executeQuery(PreparedStatement statement)
    {
        try {
            return statement.executeQuery();
        }
        catch (SQLException exception)
        {
            System.out.println("Database query error: " + exception.getMessage());
            return null;
        }
    }

    public ResultSet executeQuery(String query)
    {
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) { return null; }

    }


    public void executeUpdate(PreparedStatement statement)
    {
        try {
            statement.executeUpdate();
        }
        catch (SQLException exception)
        {
            System.out.println("Database update error: " + exception.getMessage());
        }
    }

    public void disconnect()
    {
        System.out.println("Disconnecting from database.");
        try {
            if (connection != null) connection.close();
        }
        catch (SQLException exception)
        {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

    public ArrayList<Playlist> allPlaylists()
    {
        final ArrayList<Playlist> playlists = new ArrayList<>();
        final ResultSet resultSet = executeQuery("SELECT * FROM Playlists");
        try
        {
            while (resultSet.next())
            {
                final Playlist playlist = new Playlist(
                        resultSet.getInt("PlaylistID"),
                        resultSet.getString("Name"),
                        resultSet.getString("description")
                );
                playlists.add(playlist);
            }
        } catch (Exception e) { e.printStackTrace(); return null;  }
        return playlists;
    }

    public ArrayList<Album> allAlbums()
    {
        final ArrayList<Album> albums = new ArrayList<>();
        final ResultSet resultSet = executeQuery("SELECT * FROM Albums");
        try
        {
            while (resultSet.next())
            {
                final Album album = new Album(
                        resultSet.getInt("AlbumID"),
                        resultSet.getString("Name")
                );
                albums.add(album);
            }
        } catch (Exception e) { e.printStackTrace(); return null;  }
        return albums;
    }

    public ArrayList<Song> songsInPlaylist(Playlist playlist)
    {
        final String query = "SELECT Songs.* FROM Songs JOIN PlaylistsContent ON Songs.SongID = PlaylistsContent.SongID WHERE PlaylistsContent.PlaylistID = " + playlist.getPlayistID() + ";";
        final ResultSet resultLines = executeQuery(query);
        final ArrayList<Song> songs = new ArrayList<>();
        try
        {
            while (resultLines.next())
            {
                Song newSong = new Song(
                        resultLines.getInt("SongID"),
                        resultLines.getString("Title"),
                        resultLines.getString("Artist"),
                        resultLines.getInt("Duration"),
                        resultLines.getInt("AlbumID")
                );
                songs.add(newSong);
            }
        } catch (Exception e) { e.printStackTrace(); return null; }
        return songs;
    }

    public ArrayList<Song> songsInAlbum(Album album)
    {
        final String query = "SELECT Songs.* FROM Songs JOIN Albums ON Songs.SongID = Albums.AlbumID WHERE Albums.AlbumID = " + album.getAlbumID() + ";";
        final ResultSet resultLines = executeQuery(query);
        final ArrayList<Song> songs = new ArrayList<>();
        try
        {
            while (resultLines.next())
            {
                Song newSong = new Song(
                        resultLines.getInt("SongID"),
                        resultLines.getString("Title"),
                        resultLines.getString("Artist"),
                        resultLines.getInt("Duration"),
                        resultLines.getInt("AlbumID")
                );
                songs.add(newSong);
            }
        } catch (Exception e) { e.printStackTrace(); return null; }
        return songs;
    }

    public Album albumFromName(String albumName)
    {
        for (Album album : allAlbums())
        {
            if (album.getName().equals(albumName)) return album;
        }
        return null;
    }
    public Playlist playlistFromName(String playlistName)
    {
        for (Playlist playlist : allPlaylists())
        {
            if (playlist.getName().equals(playlistName)) return playlist;
        }
        return null;
    }

}

