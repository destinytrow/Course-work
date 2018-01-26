import Model.Albums.Album;
import Model.Playlist.Playlist;
import Model.songs.*;
import Model.DatabaseConnection;
import Model.songs.Song;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class main {




        private static final DatabaseConnection connection = new DatabaseConnection("MusicPlayer.db");
        private static TableView mainTable;
        private static TableColumn songName;
        private static TableColumn artistName;
        private static TableColumn durationSeconds;


        public static void main(String[] args) {
        launchFX();
        }

        public static void launchFX() {
        new JFXPanel();
        Platform.runLater(main::InitialiseGUI);
        }

        public static void InitialiseGUI()
        {
        Stage stage = new Stage();


        BorderPane borderPane = new BorderPane();
        borderPane.prefHeightProperty().bind(borderPane.heightProperty());


        Scene scene = new Scene(borderPane, 1024, 768);

        HBox controls = new HBox(10);
        Button[] plause = new Button[2];
        plause[0] = new Button("play");
        plause[0].setPrefSize(50, 50);
        //plause[0].setOnAction((ActionEvent e)-> Model.songs.TrackPlayer.play());

        plause[1] = new Button("pause");
        plause[1].setPrefSize(50, 50);

            controls.getChildren().addAll(plause[0], plause[1]);
            borderPane.setBottom(controls);


        HBox boxOfButtons = new HBox(10);
        Button[] myButtons = new Button[1];

        myButtons[0] = new Button("Home");
        myButtons[0].setPrefSize(200, 50);
        myButtons[0].setOnAction((ActionEvent ae) -> System.out.println("ha"));

        final TextField searchBox = new TextField();
        searchBox.setPromptText("search");
        borderPane.setTop(searchBox);
        searchBox.setPrefWidth(400);
        searchBox.setPrefHeight(50);

        boxOfButtons.getChildren().addAll(myButtons[0], searchBox);
        borderPane.setTop(boxOfButtons);

        VBox playlistButtonsVBox = new VBox(); //creates a VBox which creates buttons for the individual playlists
        final ArrayList<Playlist> playlists = connection.allPlaylists();
        for (final Playlist playlist : playlists) {
            // creating a for loop to add a button until the condition is met
            Button playlistButton = new Button("Playlist " + playlist.getName());
            playlistButton.setOnAction(event ->
            {
                updateTable(playlist.getName(), true);
            });
            playlistButtonsVBox.getChildren().add(playlistButton);
            playlistButton.setPrefWidth(200);// setting the width to 200 pixels
        }

        VBox albumBtnVBox = new VBox(); //creates a VBox that sets the buttons for the individual albums
            final ArrayList<Album> listOfAlbums = connection.allAlbums();
            for (Album album : listOfAlbums) {
                // creating a for loop to add a button until the condition is met
                Button albumButton = new Button("Albums " + album.getName());
                albumButton.setOnAction(event ->
                {
                    updateTable(album.getName(), false);
                });
                albumBtnVBox.getChildren().add(albumButton);
                albumButton.setMinWidth(200);// setting the width to 200 pixels
            }

        VBox select = new VBox(); // creates a vbox that connects the two previous vBoxes together so they can be set  in the same place on the page
        select.setSpacing(50); // allows a break between the  two types of buttons for aesthetic
        select.getChildren().addAll(playlistButtonsVBox, albumBtnVBox);
        borderPane.setLeft(select); // sets the VBox in the left side


        mainTable = new TableView<Song>();
        ObservableList<Song> tableData = FXCollections.observableArrayList();

        final SongService songService = new SongService();
        tableData.addAll(songService.selectAll(connection));

        songName = new TableColumn("Song");
        songName.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        mainTable.getColumns().add(songName);

        artistName = new TableColumn("Artist");
        artistName.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        mainTable.getColumns().add(artistName);

        durationSeconds = new TableColumn("Duration");
        durationSeconds.setCellValueFactory(new PropertyValueFactory<Song, String>("duration"));
        mainTable.getColumns().add(durationSeconds);
        mainTable.setItems(tableData);

        mainTable.setOnMousePressed((MouseEvent e)-> {
            if (e.getClickCount() == 2 &&  mainTable.getSelectionModel().getSelectedItem() != null){
                System.out.println("played");
                Model.songs.TrackPlayer.play();
            }
        });

        // mainTable.setItems(SongService.selectAll(search criteria));
        borderPane.setCenter(mainTable);

        stage.setScene(scene);
        stage.show();
        }

        private static void updateTable(String playlistOrAlbumName, boolean isPlaylist)
        {
        mainTable.getItems().clear();
        if (isPlaylist)
        {
            Playlist playlist = connection.playlistFromName(playlistOrAlbumName);
            for (Song song : connection.songsInPlaylist(playlist))
            {
                mainTable.getItems().add(song);
            }
        }
        else
        {
            Album album = connection.albumFromName(playlistOrAlbumName);
            for (Song song : connection.songsInAlbum(album))
            {
                mainTable.getItems().add(song);
            }
        }
        }
}



