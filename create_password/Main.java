package create_password;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    static Random rand = new Random();
    static char[] symbols = {'~','`','!','@','#','$','%','^','&','*','(',')','_','-'
            ,'+','=',']','[','/','?','.','>','<',':',';','|','\\','"','№',','};

    public static void main(String[] args) {
        int cmd = 0;
        while (true){
            Scanner input = new Scanner(System.in);
            System.out.println("\nМЕНЮ\n 1. Создать пароль (8-12 символов в пароле);\n 2. Создать пароль определённой длины;\n 3. Выход.");

            try {
                cmd = input.nextInt();
            }catch (InputMismatchException e){
                cmd = 0;
            }

            if (cmd == 1)
                System.out.println("Пароль: "+crypt(rand.nextInt(4)+8));
            else if (cmd == 2){
                System.out.println("Введите кол-во символов в пароле (не менее 3): ");
                try {
                    cmd = input.nextInt();
                }catch (InputMismatchException e){
                    cmd = 0;
                }
                if (cmd > 3) System.out.println("Пароль: "+crypt(cmd));
                else System.out.println("Неправильный ввод размера пароля.\n");
            }
            else if (cmd == 3) break;
            else System.out.println("Выберите пункт из меню с 1 по 3.\n");
        }
    }

    private static String crypt(int count) {
        int[] mas = new int[4];
        String password = "";
        int score = 0;
        int k = 0;

        for (int i=0;i<mas.length;i++){
            mas[i] = rand.nextInt(count/mas.length)+1;
            score += mas[i];
        }

        if (count > score)
            while (true){
                for (int i=0;i<mas.length;i++){
                    k = rand.nextInt(count-score)+1;
                    mas[i] = mas[i]+k;
                    score += k;
                    if (score == count) break;
                }
                if (score == count) break;
            }

        score = 0;
        while (score < count){
            k = rand.nextInt(mas.length);
            if (mas[k]<1) continue;

            if (k == 0)
                password += (char)(rand.nextInt(26) + 'a');
            else if (k == 1)
                password += (char)(rand.nextInt(26) + 'A');
            else if (k == 2)
                password += (rand.nextInt(9));
            else if (k == 3)
                password += symbols[rand.nextInt(symbols.length)];

            mas[k] -= 1;
            score++;
        }
        return password;
    }
}