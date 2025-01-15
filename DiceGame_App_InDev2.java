//package com.kl699.dicegametest;
//
//import javafx.geometry.Pos;
//import javafx.stage.Stage;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.application.Application;
//
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicReference;
//
//public class DiceGame_App_InDev2 extends Application {
//
//    private int p1Score = 0;
//    private int p2Score = 0;
//    private int round = 1;
//    private int diceType = 6; // Default: D6
//
//    @Override
//    public void start(Stage primaryStage) {
//        String gameVersion = "v.0.2.1-alpha";
//        primaryStage.setTitle("DiceGame Java Edition - " + gameVersion);
//        primaryStage.getIcons().add(new Image(getClass().getResource("/diceRoll_icon.png").toString()));
//
//        // Main layout
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(15));
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setAlignment(Pos.CENTER);
//
//
//        // UI Elements
//        AtomicReference<Image> diceImage = new AtomicReference<>(new Image(getDiceImagePath(diceType)));
//        ImageView imageView = new ImageView(diceImage.get());
//
//        imageView.setFitWidth(200);
//        imageView.setFitHeight(200);
//
//
//        var titleLabel = new Label("Welcome to DiceGame");
//        var diceTypeLabel = new Label("Choose a dice type from the dropdown.");
//        ComboBox<Integer> diceTypeDropdown = new ComboBox<>();
//        diceTypeDropdown.getItems().addAll(4, 6, 8, 12, 20);
//        diceTypeDropdown.setValue(diceType); // Default: D6
//
//        Button setDiceTypeButton = new Button("Set Dice Type");
//        Label diceTypeMessageLabel = new Label();
//
//        Button rollButton = new Button("Roll Dice");
//        rollButton.setDisable(true); // Disabled until dice is set
//
//        Label resultLabel = new Label();
//        Label p1ScoreLabel = new Label("Player 1 Score: 0");
//        Label p2ScoreLabel = new Label("Player 2 Score: 0");
//        Label roundLabel = new Label("Round: 1");
//
//        // Set Dice Type Button Action
//        setDiceTypeButton.setOnAction(event -> {
//            diceType = diceTypeDropdown.getValue();
//            diceTypeMessageLabel.setText("Dice Type set to D" + diceType);
//
//            // Update dice image dynamically
//            String diceImagePath = getDiceImagePath(diceType);
//            diceImage.set(new Image(diceImagePath));
//            imageView.setImage(diceImage.get());
//
//            rollButton.setDisable(false);
//
//        });
//
//        // Roll Dice Button Action
//        rollButton.setOnAction(event -> {
//            if (round <= 3) {
//                Random rand = new Random();
//
//                // Roll dice for both players
//                int p1Roll = rand.nextInt(diceType) + 1;
//                int p2Roll = rand.nextInt(diceType) + 1;
//
//                // Update cumulative scores
//                p1Score += p1Roll;
//                p2Score += p2Roll;
//
//                // Display roll results for each player
//                resultLabel.setText(String.format("Round %d:\nPlayer 1 rolled %d\nPlayer 2 rolled %d", round, p1Roll, p2Roll));
//                p1ScoreLabel.setText("Player 1 Score: "+p1Score);
//                p1ScoreLabel.setText("Player 2 Score: "+p2Score);
//                roundLabel.setText("Round: "+ (round+1));
//
//                // Check if game over
//                if (round == 3) {
//                    String winner = (p1Score > p2Score) ? "Player 1 wins!" : (p1Score < p2Score) ? "Player 2 wins!" : "It's a tie!";
//                    resultLabel.setText(resultLabel.getText() + "\n\n"+winner);
//                    rollButton.setDisable(true); // Disable button after game is over
//                    showEndGamePopup(resultLabel, p1ScoreLabel, p2ScoreLabel, roundLabel, rollButton); // Show Restart popup
//                }
//
//                round++; // Increment round number
//                System.out.println("Round: " + round);
//            }
//        });
//
//        grid.add(titleLabel, 0, 0, 2, 1);
//        grid.add(diceTypeLabel, 0, 1);
//        grid.add(diceTypeDropdown, 1, 1);
//        grid.add(setDiceTypeButton, 0, 2, 2,1);
//        grid.add(diceTypeMessageLabel, 0, 3, 2,1);
//        grid.add(roundLabel, 0,4);
//        grid.add(p1ScoreLabel, 0,5);
//        grid.add(p2ScoreLabel, 1,5);
//        grid.add(rollButton, 0,6, 2,1);
//        grid.add(resultLabel, 0, 7,2,1);
//
//        Scene scene = new Scene(grid, 400, 500);
//        scene.getStylesheets().add(getClass().getResource("/style.css").toString());
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//    }
//
//    private String getDiceImagePath(int diceType) {
//        String imagePath = "/d"+diceType+"_icon.png";
//
//        if (getClass().getResource(imagePath) == null) {
//            System.out.println("Image not found for D"+diceType+". Check resource path: "+imagePath);
//            return "/diceRoll_icon.png";
//        }
//        return getClass().getResource(imagePath).toString();
//    }
//
//    // Method to show a popup at the end of the game
//    private void showEndGamePopup(Stage primaryStage) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Game Over");
//        alert.setHeaderText("The game has ended!");
//        alert.setContentText("Would you like to restart or quit?");
//
//        ButtonType restartButton = new ButtonType("Restart");
//        ButtonType quitButton = new ButtonType("Quit");
//        alert.getButtonTypes().setAll(restartButton, quitButton);
//
//        alert.showAndWait().ifPresent(response -> {
//            if (response == restartButton) {
//                restartGame(resultLabel, p1ScoreLabel, p2ScoreLabel, roundLabel, rollButton);
//            } else if (response == quitButton) {
//                primaryStage.close();
//            }
//        });
//    }
//
//    // Method to restart the game
//    private void restartGame(Stage primaryStage) {
//        p1Score = 0;
//        p2Score = 0;
//        round = 0;
//        diceType = 6;
//        start(primaryStage);
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
