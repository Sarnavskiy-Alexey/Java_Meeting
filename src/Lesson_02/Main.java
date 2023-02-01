package Lesson_02;


import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {

        // выполнение задания №1
        /* 1. Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации
        запишите в лог-файл. */
        {
            System.out.println("\t\tЗадание №1");
            int[] mas = new int[10];
            for (int i = 0; i < mas.length; i++)
                mas[i] = (int) (Math.random() * 20);
            System.out.printf("Исходный массив:\n%s\n", Arrays.toString(mas));
            System.out.printf("Отсортированный массив:\n%s\n", Arrays.toString(bubble_sort(mas)));
            System.out.printf("Исходный массив не изменен:\n%s\n", Arrays.toString(mas));
            System.out.println("\t\t----------------");
        }

        // выполнение задания №1
        /* 2. К калькулятору из предыдущего дз добавить логирование. */
        {
            System.out.println("\t\tЗадание №2");
            // создание объекта-сканера для считывания данных из консоли
            Scanner myScanner = new Scanner(System.in);

            System.out.println("Введите арифметическую операцию вида: \"<a> <sign> <b>\" (пробелы обязательны!):");
            int a = myScanner.nextInt();
            char c = myScanner.next().charAt(0);
            int b = myScanner.nextInt();
            System.out.printf("%d %c %d = %d\n", a, c, b, simple_calc(a, b, c));

            System.out.println("\t\t----------------");
        }
    }


    /** Функция, реализующая сортировку поступающего массива пузырьком
     * @returns отсортированную копию передаваемого массива */
    public static int[] bubble_sort(int[] mas) {
        // создаем копию исходного массива для отдельной работы
        int[] mas_copy = Arrays.copyOf(mas, mas.length);

        // создаем логгер и файлхэндлер для записи логов
        Logger logger = Logger.getLogger(Main.class.getName());
        FileHandler fh = null;
        SimpleFormatter sFormat = new SimpleFormatter();
        try {
            fh = new FileHandler("log.txt");
            logger.addHandler(fh);
            fh.setFormatter(sFormat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // собственно реализация сортировки пузырьком
        for (int i = 0; i < mas_copy.length - 1; i++)
            for (int j = 0; j < mas_copy.length - i - 1; j++) {
                if (mas_copy[j] > mas_copy[j + 1]) {
                    int tmp = mas_copy[j];
                    mas_copy[j] = mas_copy[j + 1];
                    mas_copy[j + 1] = tmp;

                    // запись данных о сортирующемся массиве в лог
                    logger.info(Arrays.toString(mas_copy));
                }
            }

        return mas_copy;
    }


    /** Функция, реализующая простой калькулятор + логирование операций
     * @returns значение, получаемое при подсчете */
    public static int simple_calc(int a, int b, char sign) {
        // добавление логгера и консольхэндлера
        Logger logger = Logger.getLogger(Main.class.getName());
        ConsoleHandler ch = new ConsoleHandler();
        SimpleFormatter sFormat = new SimpleFormatter();
        logger.addHandler(ch);
        ch.setFormatter(sFormat);

        int result;
        switch (sign) {
            case '+' -> {
                result = a + b;
                logger.info("Ошибок при сложении не обнаружено!");
                logger.info(Integer.toString(a) + " + " + Integer.toString(b) + " = " + Integer.toString(result));
            }
            case '-' -> {
                result = a - b;
                logger.info("Ошибок при вычитании не обнаружено!");
                logger.info(Integer.toString(a) + " - " + Integer.toString(b) + " = " + Integer.toString(result));
            }
            case '*' -> {
                result = a * b;
                logger.info("Ошибок при умножении не обнаружено!");
                logger.info(Integer.toString(a) + " * " + Integer.toString(b) + " = " + Integer.toString(result));
            }
            case '/' -> {
                try {
                    result = a / b;
                    logger.info("Ошибок при делении не обнаружено!");
                    logger.info(Integer.toString(a) + " / " + Integer.toString(b) + " = " + Integer.toString(result));
                } catch (ArithmeticException e) {
                    logger.warning("Обнаружено деление на ноль!");
                    result = -1;
                }
            }
            case '%' -> {
                try {
                    result = a % b;
                    logger.info("Ошибок при делении не обнаружено!");
                    logger.info(Integer.toString(a) + " % " + Integer.toString(b) + " = " + Integer.toString(result));
                } catch (ArithmeticException e) {
                    logger.warning("Обнаружено деление на ноль!");
                    result = -1;
                }
            }
            default -> {
                result = 0;
            }
        }
        return result;
    }
}
