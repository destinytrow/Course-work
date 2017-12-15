package view;
import Model.songs.*;
import Model.DatabaseConnection;
import Model.songs.Song;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Album {

    public static void main(String[] args) {
        launchFX();
    }

    public static void launchFX() {
        new JFXPanel();
        Platform.runLater(() -> InitialiseGUI());
    }

    public static void InitialiseGUI() {

        DatabaseConnection check = new DatabaseConnection("MusicPLayer.db");

        Stage stage = new Stage();


        BorderPane borderPane = new BorderPane();
        borderPane.prefHeightProperty().bind(borderPane.heightProperty());


        Scene scene = new Scene(borderPane, 1024, 768);


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

        VBox Plist = new VBox(); //creates a VBox which creates buttons for the individual playlists
        int playlength = 5; // for testing I've set it to 5
        for (int i = 1; i <= playlength; i++){ // creating a for loop to add a button until the condition is met
            Button btn = new Button("playlist" + i );
        Plist.getChildren().add(btn);
        btn.setPrefWidth(200);// setting the width to 200 pixels
        }


        VBox Alist = new VBox(); //creates a VBox that sets the buttons for the individual albums
        int Albumlength = 5;
        for (int i = 1; i <= Albumlength; i++){
            Button btn = new Button("Album" + i );
            Alist.getChildren().add(btn);
            btn.setPrefWidth(200);
        }

        VBox select = new VBox(); // creates a vbox that connects the two previous vBoxes together so they can be set  in the same place on the page
        select.setSpacing(50); // allows a break between the  two types of buttons for aesthetic
        select.getChildren().addAll(Plist, Alist);
        borderPane.setLeft(select); // sets the VBox in the left side
        //ArrayList<Song> test = new ArrayList<>();
      //  Model.songs.SongService.selectAll(test);

        TableView mainTable = new TableView<Song>();
        ObservableList<Song> tableData = FXCollections.observableArrayList();

        mainTable.getItems().add("New Item");
        TableColumn songName = new TableColumn("Song");
        songName.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        mainTable.getColumns().add(songName);

        TableColumn artistName = new TableColumn("Artist");
        artistName.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        mainTable.getColumns().add(artistName);

        TableColumn durationSeconds = new TableColumn("Duration");
        durationSeconds.setCellValueFactory(new PropertyValueFactory<Song, String>("duration"));
        mainTable.getColumns().add(durationSeconds);
        mainTable.setItems(tableData);

        mainTable.setItems(SongService.selectAll(search criteria))
        borderPane.setCenter(mainTable);

        stage.setScene(scene);
        stage.show();
    }
}
