package menu;

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
    }
}