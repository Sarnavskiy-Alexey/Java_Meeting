package Lesson_03;

import java.util.ArrayList;
import java.util.List;

public class Lesson_03 {
    public static void main(String[] args) {
        // выполнение задания №1
        /* 1. Реализовать алгоритм сортировки слиянием */
        {
            System.out.println("\t\tЗадание №1");
            System.out.println("Исходный массив сгенерирован рандомно!");
            List<Integer> integerList = new ArrayList<Integer>(10);
            for (int i = 0; i < 10; i++) {
                integerList.add((int) (Math.random() * 100));
            }
            List<Integer> result = my_merge(integerList);
            System.out.println("Исходный список:");
            System.out.println(integerList);
            System.out.println("Результат сортировки:");
            System.out.println(result);
            System.out.println("\t\t----------------");
        }

        // выполнение задания №2
        /* 2. Пусть дан произвольный список целых чисел, удалить из него четные числа */
        {
            System.out.println("\t\tЗадание №2");
            System.out.println("Исходный список:");
            List<Integer> integerList = new ArrayList<Integer>();
            for (int i = 0; i < 10; i++) {
                integerList.add((int) (Math.random() * 100));
            }
            System.out.println(integerList);
            remove_evens(integerList);
            System.out.println("Результат удаления четных чисел:");
            System.out.println(integerList);
            System.out.println("\t\t----------------");
        }

        // выполнение задания №3
        /* 3. Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее из этого списка */
        {
            System.out.println("\t\tЗадание №3");
            System.out.println("Исходный список:");
            List<Integer> integerList = new ArrayList<Integer>();
            for (int i = 0; i < 10; i++) {
                integerList.add((int) (Math.random() * 100));
            }
            System.out.println(integerList);
            System.out.printf("Минимальное значение в списке: %d\n", find_min(integerList));
            System.out.printf("Максимальное значение в списке: %d\n", find_max(integerList));
            System.out.printf("Среднее арифметическое значение в списке: %.1f\n", (find_average(integerList)));

            System.out.println("\t\t----------------");
        }
    }


    /**
     * <p>Функция, с которой начинается сортировка слиянием.
     * Она является подготовительным этапом перед непосредственно сортировкой.
     * Разбивает исходный массив целых чисел на два подмассива (левый и правый).</p>
     *
     * @param list исходный список целых чисел
     * @return Результат работы функции сортировки двух созданных подмассивов
     */
    public static List<Integer> my_merge(List<Integer> list) {
        // базовый случай: если элементов в списке меньше двух, то возвращаем этот же список
        if (list.size() < 2)
            return list;

        // далее до конца тела функции: рекурсивный случай
        int middle = list.size() / 2;   // индекс середины списка

        // создаем подмассив с элементами в левой половине исходного массива
        List<Integer> left = new ArrayList<Integer>();
        for (int i = 0; i < middle; i++) {
            left.add(list.get(i));
        }

        // создаем подмассив с элементами в правой половине исходного массива
        List<Integer> right = new ArrayList<Integer>();
        for (int i = middle; i < list.size(); i++) {
            right.add(list.get(i));
        }

        // разбиваем каждую из половин еще пополам
        left = my_merge(left);
        right = my_merge(right);

        // возвращаем результат выполнения основной функции сортировки двух подмассивов
        return my_merge_sorter(left, right);
    }


    /**
     * <p>Основная функция сортировки слиянием, в которой и происходит сортировка.
     * Суть сортировки в том, что последовательно берется по одному элементу из
     * левого и правого подмассивов, сравниваются и добавляются в результирующий массив.</p>
     *
     * @param left  левый подмассив
     * @param right правый подмассив
     * @return Отсортированный массив на основе левого и правого подмассивов
     */
    public static List<Integer> my_merge_sorter(List<Integer> left, List<Integer> right) {
        // результирующий массив
        List<Integer> result = new ArrayList<Integer>();

        int i = 0;  // счетчик для левого подмассива
        int j = 0;  // счетчик для правого подмассива

        // цикл, в котором сравниваются элемент левого подмассива и элемент правого и
        // добавляется меньший из них в результирующий массив
        while ((i < left.size()) && (j < right.size())) {
            if (left.get(i) < right.get(j)) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }
        // цикл, в котором перебираются оставшиеся элементы левого подмассива (если они есть)
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }
        // цикл, в котором перебираются оставшиеся элементы правого подмассива (если они есть)
        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result;
    }


    /**
     * <p>Функция, реализующая удаление четных чисел в исходном списке (не копии!)</p>
     * @param list исходный список, который редактируется прямо в функции
     */
    public static void remove_evens(List<Integer> list) {
        int i = 0;  // счетчик по индексам списка

        while (i < list.size()) {
            // если нашли четное число, то удаляем его
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            } /* иначе переходим к следующему индексу */ else {
                i++;
            }
        }
    }


    /** <p>Функция для нахождения минимума из списка целых чисел.</p>
     * @param list исходный список
     * @return Минимальный элемент из списка
     */
    public static int find_min(List<Integer> list) {
        int min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int tmp = list.get(i);
            if (min > tmp) {
                min = tmp;
            }
        }
        return min;
    }


    /** <p>Функция для нахождения максимума из списка целых чисел.</p>
     * @param list исходный список
     * @return Максимальный элемент из списка
     */
    public static int find_max(List<Integer> list) {
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int tmp = list.get(i);
            if (max < tmp) {
                max = tmp;
            }
        }
        return max;
    }


    /** <p>Функция для нахождения среднего арифметического значения из списка целых чисел.</p>
     * @param list исходный список
     * @return Среднее арифметическое значение из списка
     */
    public static double find_average(List<Integer> list) {
        double result = 0.0;
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }
        return result / list.size();
    }
}
