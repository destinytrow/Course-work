package Model.songs;
import Model.DatabaseConnection;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SongService {
 public List<Song> selectAll(List<Song> targetList, DatabaseConnection database) {
     PreparedStatement statement = database.newStatement("SELECT * FROM Songs");

     try {
         if (statement != null) {

             ResultSet results = database.executeQuery(statement);

             if (results != null) {
                 while (results.next()) {
                     targetList.add(new Song(results.getInt("SongID"), results.getString("Title"), results.getString("Artist"), results.getInt("Duration"), results.getInt("AlbumID")));
                     }
             }
         }
         return targetList;
     } catch (SQLException resultsException) {
         System.out.println("Database select all error: " + resultsException.getMessage());
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
////        final ArrayList<Song> allSongs = new ArrayList<>();
////        while (results.next())
////        {
////            //allSongs.add()   //STEVE COMMENTED OUT THIS LINE
////        }
////        return null;        //STEVE ADDED THIS LINE, REMOVE IT WHEN YOU'RE READY
////    }
//}
