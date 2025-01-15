package com.kl699.dicegametest;

import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class DiceGame_App_InDev3 extends Application {

    private int p1Score = 0;
    private int p2Score = 0;
    private int round = 1;
    private int diceType = 6; //Default: D6

    private String gameVersion = "v.0.2.1-alpha";


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DiceGame Java Edition - " +gameVersion);

        //Main
        var root = new VBox(10);
        root.setPadding(new Insets(15));

        //UI Elements

        AtomicReference<Image> diceImage = new AtomicReference<>(new Image(getDiceImagePath(diceType)));
        ImageView imageView = new ImageView(diceImage.get());

        var titleLabel = new Label("Welcome to DiceGame");
        var diceTypeLabel = new Label("Choose a dice type from the dropdown.");

        ComboBox<Integer> diceTypeDropdown = new ComboBox<>();
        diceTypeDropdown.getItems().addAll(4, 6, 8, 10, 12, 16,20);
        diceTypeDropdown.setValue(diceType); //Preset to default D6

        Button setDiceTypeButton = new Button("Set Dice Type");
        Label diceTypeMessageLabel = new Label();

        Button rollButton = new Button("Roll Dice");
        rollButton.setDisable(true); //Disabled until dice is set

        Label resultLabel = new Label();
        Label p1ScoreLabel = new Label("Player 1 Score: 0");
        Label p2ScoreLabel = new Label("Player 2 Score: 0");
        Label roundLabel = new Label("Round: 1");

        //Set Dice Type Button Action
        setDiceTypeButton.setOnAction(event -> {
            diceType = diceTypeDropdown.getValue();
            diceTypeMessageLabel.setText("Dice Type set to D" + diceType);
            rollButton.setDisable(false);
        });

       //Roll Dice Button Action
        rollButton.setOnAction(event -> {
           if (round <= 3) {
               Random rand = new Random();

               //Roll dice for both players
               int p1Roll = rand.nextInt(diceType)+1;
               int p2Roll = rand.nextInt(diceType)+1;

               //Update cumulative scores
               p1Score += p1Roll;
               p2Score += p2Roll;

               //Display roll results for each player
               resultLabel.setText("Round: "+round+" \nPlayer 1 rolled a "+p1Roll+"\nPlayer 2 rolled a "+p2Roll);

               //Update score and round display
               p1ScoreLabel.setText("Player 1 Score: "+p1Score);
               p2ScoreLabel.setText("Player 2 Score: "+p2Score);
               roundLabel.setText("Round: "+(round+1));

               //Check if game over
               if (round==3) {
                   String winner = (p1Score > p2Score) ? "Player 1 wins!" : (p1Score < p2Score) ? "Player 2 wins!" : "It's a tie!";
                   resultLabel.setText(resultLabel.getText()+"\n\nFinal Scores: "+winner);
                   rollButton.setDisable(true); //Disable button after game is over
               }

               round++; //Increment round number
           }
        });

        //Adding elements to layout
        root.getChildren().addAll(titleLabel, diceTypeLabel, diceTypeDropdown, setDiceTypeButton, diceTypeMessageLabel,
                p1ScoreLabel, p2ScoreLabel, roundLabel, rollButton, resultLabel);

        //Create and set scene
        var scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    /**
//     *
//     * @param title
//     * @param message
//     */
//    private void showError(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }


    public static void main(String[] args) {
        launch(args);
    }

}