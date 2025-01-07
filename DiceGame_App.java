package com.kl699.dicegametest;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import java.io.IOException;
import java.util.Random;

public class DiceGame_App extends Application {
    @Override
    public void start(Stage primaryStage) {
        //Main
        var root = new VBox(10);
        root.setPadding(new Insets(15));

        //UI Elements
        var titleLabel = new Label("Welcome to DiceGame");
        var instructionLabel = new Label("Enter the number of sides for the dice: ");

        var diceTypeInput = new TextField();
        diceTypeInput.setPromptText("Enter a positive integer");

        var rollButton = new Button("Roll Dice");
        var resultLabel = new Label();

        //Button Action
        rollButton.setOnAction(event -> {
            try {
                String inputText = diceTypeInput.getText();
                int diceType = Integer.parseInt(inputText);

                if (diceType > 0) {
                    int roll = new Random().nextInt(diceType) + 1;
                    resultLabel.setText("You rolled a " +roll+ " on a D"+diceType+ "!");
                } else {
                    resultLabel.setText("Please enter a positive integer greater than 0");
                }
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input! Enter a valid integer.");
            }
        });

        //Adding elements to layout
        root.getChildren().addAll(titleLabel, instructionLabel, diceTypeInput, rollButton, resultLabel);

        //Create and set scene
        var scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}