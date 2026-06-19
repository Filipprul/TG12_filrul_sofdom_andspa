package menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class menu{
    public Scene getMenuScene(Stage primaryStage){
        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #1a1a1a;");

        Label titleLabel = new Label("Snake");
        titleLabel.setFont(new Font("Arial", 48));
        titleLabel.setStyle("-fx-text-fill: #4CAF50; -fx-font-weight: bold;");

        Button startButton = new Button("START");
        Button exitButton = new Button("EXIT");

        String buttonStyle = "-fx-background-color: #333333; " +
        "-fx-text-fill: white; " +
        "-fx-font-size: 16px; " +
        "-fx-font-weight: bold; " +
        "-fx-min-width: 200px; " +
        "-fx-min-height: 45px; " +
        "-fx-background-radius: 5px; " +
        "-fx-cursor: hand; ";

        startButton.setStyle(buttonStyle);
        exitButton.setStyle(buttonStyle);

        startButton.setOnMouseEntered(e -> startButton.setStyle(buttonStyle + "-fx-background-color: #4CAF50;"));
        startButton.setOnMouseExited(e -> startButton.setStyle(buttonStyle));

        exitButton.setOnMouseEntered(e -> exitButton.setStyle(buttonStyle + "-fx-background-color: #f44336;"));
        exitButton.setOnMouseExited(e -> exitButton.setStyle(buttonStyle));

        startButton.setOnAction(e -> {
            System.out.println("Spiel startet...");
        })

        exitButton.setOnAction(e -> {
            primaryStage.close();
        })

        root.getChildren().addAll(titleLabel, startButton, exitButton);

        retunr new Scene(root, 800, 600);
    }
}