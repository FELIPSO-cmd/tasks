package gallows;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    final static String[] dictionary_words = {"avenue","axiom","banjo","beekeeper","blizzard","boxcar","crypt","flapjack","jackpot",
            "kilobyte","kiwifruit","length","matrix","oxygen","pixel","quartz","wizard","zephyr","zombie"};

    final static char[] dictionary_letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public static void main(String[] args) {
        while (true){
            System.out.println("MENU\n 1. PLAY;\n 2. EXIT.");
            Scanner input = new Scanner(System.in);
            int choice_cmd = 0;

            try {
                choice_cmd = input.nextInt();
            }catch (InputMismatchException e) {
                choice_cmd = 0;
            }

            if (choice_cmd == 1) game_logic();
            else if(choice_cmd == 2) break;
            else System.out.println("incorrect input.\n");
        }
    }

    private static void game_logic() {
        int hearts = 6;
        int bit = 0;
        int score = 0;

        char[] letter = new char[dictionary_letters.length];
        for (int i=0;i<dictionary_letters.length;i++)
            letter[i] = dictionary_letters[i];

        Random rand = new Random();
        int x = rand.nextInt(dictionary_words.length);
        final String need_word = dictionary_words[x].toUpperCase();
        String hidden_word = new String();
        char choice_letter = ' ';

        for (int i=0;i<need_word.length();i++)
            hidden_word += "_";

        while (true){
            game_display(hearts,hidden_word,letter);
            System.out.println("HEARTS: " + hearts);
            System.out.println("Select and enter a letter from the available ones: ");

            bit = 0;
            Scanner input = new Scanner(System.in);
            while (bit == 0){
                choice_letter = input.next().toUpperCase().charAt(0);
                for (int i=0;i<letter.length;i++)
                    if ((choice_letter == letter[i]) && (choice_letter != ' ')){
                        bit = 1;
                        letter[i] = ' ';
                        break;
                    }
                if (bit == 0) System.out.println("The character is entered incorrectly!\n Enter again:");
            }

            bit = 0;
            for (int i=0;i<need_word.length();i++)
                if (need_word.charAt(i) == choice_letter){
                    bit += 1;
                    hidden_word = hidden_word.substring(0, i) + need_word.charAt(i) + hidden_word.substring(i+1);
                }

            if (bit > 0){
                score += bit;
                if (score == need_word.length()){
                    game_display(hearts,hidden_word,letter);
                    System.out.println("\n$$  $$  $$$$$$   $$$$   $$$$$$   $$$$   $$$$$   $$  $$\n$$  $$    $$    $$  $$    $$    $$  $$  $$  $$   $$$$\n$$  $$    $$    $$        $$    $$  $$  $$$$$     $$\n $$$$     $$    $$  $$    $$    $$  $$  $$  $$    $$\n  $$    $$$$$$   $$$$     $$     $$$$   $$  $$    $$ ");
                    break;
                }

            }
            else {
                hearts-=1;
                if (hearts < 1){
                    game_display(hearts,hidden_word,letter);
                    System.out.println("\nThe hidden word: "+need_word+"\n");
                    System.out.println(" $$$$    $$$$   $$   $$  $$$$$      $$$$   $$  $$  $$$$$  $$$$$\n$$      $$  $$  $$$_$$$  $$        $$  $$  $$  $$  $$     $$  $$\n$$ $$$  $$$$$$  $$ $ $$  $$$$      $$  $$  $$  $$  $$$$   $$$$$\n$$  $$  $$  $$  $$   $$  $$        $$  $$   $$$$   $$     $$  $$\n $$$$   $$  $$  $$   $$  $$$$$      $$$$     $$    $$$$$  $$  $$");
                    break;
                }
            }
        }
        System.out.println("\n\n\n");
    }

    private static void game_display(int hearts, String h_word, char[] letter) {
        System.out.println("+-------------------------------+");

        if (hearts == 6)
            for(int i=0;i<6;i++)
                System.out.println("|                               |");
        else if (hearts == 5){
            for(int i=0;i<5;i++)
                System.out.println("|                               |");
        }
        else if (hearts == 4){
            System.out.println("|                               |");
            for(int i=0;i<5;i++)
                System.out.println("|                |              |");
        }
        else if (hearts == 3){
            System.out.println("|            +---+              |");
            for(int i=0;i<5;i++)
                System.out.println("|                |              |");
        }
        else if (hearts == 2){
            System.out.println("|            +---+              |");
            System.out.println("|            |   |              |");
            for(int i=0;i<4;i++)
                System.out.println("|                |              |");
        }
        else if (hearts == 1){
            System.out.println("|            +---+              |");
            System.out.println("|            |   |              |");
            System.out.println("|            O   |              |");
            for(int i=0;i<3;i++)
                System.out.println("|                |              |");
        }
        else if (hearts == 0){
            System.out.println("|            +---+              |");
            System.out.println("|            |   |              |");
            System.out.println("|            O   |              |");
            System.out.println("|           /|\\  |              |");
            System.out.println("|           / \\  |              |");
            System.out.println("|                |              |");
        }

        if (hearts < 6) System.out.println("|          =========''']        |");
        String vr_text = "|                               |";

        vr_text = "|            ";
        vr_text += h_word;

        for (int i=0;i<(20-h_word.length());i++)
            if (i == (19-h_word.length())) vr_text += '|';
            else vr_text += ' ';

        System.out.println(vr_text);
        System.out.println("|-------------------------------|");

        vr_text = "|   ";
        for (int i=0;i<26;i++){
            vr_text += letter[i] + " ";
            if (i==12) vr_text += "  |\n|   ";
            if (i==25) vr_text += "  |";
        }
        System.out.println(vr_text);
        System.out.println("+-------------------------------+");
    }
}
/*
+-------------------------------+
|            +---+              |
|            |   |              |
|            O   |              |
|           /|\  |              |
|           / \  |              |
|                |              |
|          =========''']        |
|                               |
|-------------------------------|
|   A B C D E F G H I J K L M   |
|   N O P Q R S T U V W X Y Z   |
+-------------------------------+

$$  $$  $$$$$$   $$$$   $$$$$$   $$$$   $$$$$   $$  $$
$$  $$    $$    $$  $$    $$    $$  $$  $$  $$   $$$$
$$  $$    $$    $$        $$    $$  $$  $$$$$     $$
 $$$$     $$    $$  $$    $$    $$  $$  $$  $$    $$
  $$    $$$$$$   $$$$     $$     $$$$   $$  $$    $$

 $$$$    $$$$   $$   $$  $$$$$      $$$$   $$  $$  $$$$$  $$$$$
$$      $$  $$  $$$_$$$  $$        $$  $$  $$  $$  $$     $$  $$
$$ $$$  $$$$$$  $$ $ $$  $$$$      $$  $$  $$  $$  $$$$   $$$$$
$$  $$  $$  $$  $$   $$  $$        $$  $$   $$$$   $$     $$  $$
 $$$$   $$  $$  $$   $$  $$$$$      $$$$     $$    $$$$$  $$  $$

*/
