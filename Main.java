import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Welcome to DiceGame Java Edition!");
        System.out.println("Choose a dice type: ");
        int diceType = input.nextInt();

        int roll = rand.nextInt(diceType) + 1;
        System.out.println("You Rolled a " + roll);

    }

}
