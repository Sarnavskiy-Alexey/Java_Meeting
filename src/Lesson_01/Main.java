package Lesson_01;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // создание объекта-сканера для считывания данных из консоли
        Scanner myScanner = new Scanner(System.in);

        // выполнение задания №1
        /* 1. Вычислить n-ое треугольное число(сумма чисел от 1 до n), n! (произведение чисел от 1 до n) */
        {
            System.out.println("\t\tЗадание №1");
            System.out.print("Введите число n: ");
            int n = myScanner.nextInt();
            int n_triangle = triangle_num(n);
            int n_fact = fact(n);
            System.out.printf("%d-ое треугольное число: %d\n", n, n_triangle);
            System.out.printf("%d! равен %d\n", n, n_fact);
            System.out.println("\t\t----------------");
        }

        // выполнение задания №2
        /* 2. Вывести все простые числа от 1 до 1000 */
        {
            System.out.println("\t\tЗадание №2");
            int right_range = 1000;
            List<Integer> list = simple_nums(right_range);
            if (list.size() != 0) {
                System.out.printf("Список простых чисел в диапазоне от 1 до %d:\n", right_range);
                System.out.println(list);
            }
            else
                System.out.println("Список простых чисел пуст!");
            System.out.println("--------------------");
        }

        // выполнение задания №3
        /* 3. Реализовать простой калькулятор */
        {
            System.out.println("\t\tЗадание №3");
            System.out.println("Введите арифметическую операцию вида: \"<a> <sign> <b>\" (пробелы обязательны!):");
            int a = myScanner.nextInt();
            char c = myScanner.next().charAt(0);
            int b = myScanner.nextInt();
            System.out.printf("%d %c %d = %d\n", a, c, b, simple_calc(a, b, c));
            System.out.println("--------------------");
        }
        myScanner.close();
    }


    /** Функция для нахождения значения n-го треугольного числа */
    public static int triangle_num(int n) {
        return (n * (n + 1)) / 2;
    }


    /** Рекурсивная функция для нахождения значения n!
     * <p>Рассматриваются два базовых случая:</p>
     * <p>1) когда n равно нулю - возвращаем 1;</p>
     * <p>2) когда n меньше нуля - возвращаем 0.</p>
     * Рекурсивный случай: возвращаем n * fact(n - 1)*/
    public static int fact(int n) {
        if (n == 0)
            return 1;
        else if (n < 0)
            return 0;
        else
            return n * fact(n - 1);
    }


    /** Функция для поиска простых чисел
     * <p>Если на вход подается число меньшее либо равное единице, то возвращается пустой список;</p>
     * <p>Если на вход подается двойка, то возвращается список с одним значением: 2;</p>
     * Иначе ищутся все возможные простые числа */
    public static List<Integer> simple_nums(int right_r) {
        List<Integer> list = new ArrayList<Integer>();

        if (right_r <= 1)
            return list;
        else {
            list.add(2);
            if (right_r == 2)
                return list;

            for (int num = 3; num < right_r; num += 2) {
                boolean no_divs = true;
                for (int simple : list) {
                    if (num % simple == 0) {
                        no_divs = false;
                        break;
                    }
                }
                if (no_divs)
                    list.add(num);
            }
        }
        return list;
    }


    /** Функция, реализующая простой калькулятор
     * @returns значение, получаемое при подсчете */
    public static int simple_calc(int a, int b, char sign) {
        switch (sign) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': {
                if (b != 0)
                    return a / b;
                else {
                    System.out.println("Делимое равняется нулю! Возвращаемому значению не доверять!");
                    return -1;
                }
            }
            case '%': {
                if (b != 0)
                    return a % b;
                else {
                    System.out.println("Делимое равняется нулю! Возвращаемому значению не доверять!");
                    return -1;
                }
            }
            default: return 0;
        }
    }
}
