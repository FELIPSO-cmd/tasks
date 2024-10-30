package currency_exchange;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static final String[] name_currency = {"RUB","USD","EUR","CNY","BYN"};
    static final double[][] value_currency = { {1,0.010345,0.009541,0.07407,0.034255},
                                        {96.67,1,0.92629,7.12,3.3},
                                        {104.81,1.08,1,7.69,3.56},
                                        {13.5,0.140461,0.130108,1,0.46573},
                                        {29.19,0.303085,0.280702,2.15,1}};

    public static void main(String[] args) {
        int choice_cmd;

        while (true){
            Scanner input = new Scanner(System.in);
            System.out.println("\nМЕНЮ\n 1. Конвертировать валюту в другие валюты;\n 2. Выход.");
            try {
                choice_cmd = input.nextInt();
            }catch (InputMismatchException e){
                choice_cmd = 0;
            }

            if (choice_cmd == 1){
                Scanner inputs = new Scanner(System.in);
                System.out.println("Выберите и введите назвние валюты из предложенных: "+ Arrays.toString(name_currency));
                String name_cur = inputs.nextLine();
                System.out.println("Введите кол-во данной валюты (пример ввода: 1 | 2,5 | ...): ");

                double value_cur = 0;
                try {
                    value_cur = inputs.nextDouble();
                }catch (InputMismatchException e){
                    value_cur = 0;
                }

                int bit = -1;

                for (int i=0;i<name_currency.length;i++)
                    if (Objects.equals(name_currency[i], name_cur))bit=i;

                System.out.println("\n");

                if (bit != -1){
                    if (value_cur > 0){
                        System.out.println("Курс вылюты: ");
                        for (int i=0;i<name_currency.length;i++)
                            if (i!=bit){
                                //String form = String.format("%.2f", value_cur*value_currency[bit][i]);
                                System.out.println("  "+String.format("%.2f", value_cur)+" "+name_cur+" = "+String.format("%.2f", value_cur*value_currency[bit][i])+" "+name_currency[i]);
                            }

                    }
                    else System.out.println("\nНеверное кол-во валюты, кол-во валюты должно являться числом и быть больше 0.");
                }
                else System.out.println("\nТакой валюты нет в массиве, выбирайте валюту из предложенных.");
            }
            else if (choice_cmd == 2)
                break;
            else System.out.println("\nНеправильный ввод.");

        }
    }
}