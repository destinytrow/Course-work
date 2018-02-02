package Model.songs;
import Model.DatabaseConnection;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongService {
    public List<Song> getAllSongs(DatabaseConnection database)
    {
        PreparedStatement statement = database.newStatement("SELECT * FROM Songs");
        final List<Song> allTheSongs = new ArrayList<>();

        try {
            if (statement != null) {

                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        allTheSongs.add(new Song(results.getInt("SongID"), results.getString("Title"), results.getString("Artist"), results.getInt("Duration"), results.getInt("AlbumID")));
                    }
                }
            }
            return allTheSongs;
        } catch (SQLException resultsException) {
            System.out.println("Database select all error: " + resultsException.getMessage());
            return null;
        }

    }
}
//
//    }	public static Songs selectById(int songID, DatabaseConnection database) {return null; }
//        public static void save(Songs song, DatabaseConnection database) { } */
////    public static ArrayList<Song> getAllSongs() throws SQLException
////    {
////        //final DatabaseConnection dbConn = new DatabaseConnection();
////        final ResultSet results = dbConn.executeQuery("SELECT * FROM Songs");
////        final ArrayList<Song> getAllSongs = new ArrayList<>();
////        while (results.next())
////        {
////            //getAllSongs.add()   //STEVE COMMENTED OUT THIS LINE
////        }
////        return null;        //STEVE ADDED THIS LINE, REMOVE IT WHEN YOU'RE READY
////    }
//}