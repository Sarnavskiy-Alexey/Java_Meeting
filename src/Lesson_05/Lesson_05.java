package Lesson_05;

import java.util.*;

public class Lesson_05 {
    public static void main(String[] args) {
        // выполнение задания №1
        /* 1. Реализуйте структуру телефонной книги с помощью HashMap, учитывая,
        что 1 человек может иметь несколько телефонов. */
        {
            Map<String, HashSet<phone_Number>> phone_book = create_phone_book();

            for (HashMap.Entry<String, HashSet<phone_Number>> item : phone_book.entrySet()) {
                System.out.printf("%s:\t", item.getKey());
                for (phone_Number item_phone : item.getValue()) {
                    System.out.printf("+%d (%d) %d \t", item_phone.country, item_phone.city, item_phone.number);
                }
                System.out.println();
            }
        }

        // выполнение задания №2
        /* Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
        Отсортировать по убыванию популярности. */
        {
            // создание сканера
            Scanner myScanner = new Scanner(System.in);

            // создание списка
            List<full_Name> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                full_Name full_name = new full_Name();
                System.out.print("Введите имя: ");
                full_name.first_name = myScanner.next();
                System.out.print("Введите фамилию: ");
                full_name.last_name = myScanner.next();
                list.add(full_name);
            }

            List<Popularity> popularityList = popular_names(list);
            for (Popularity item : popularityList) {
                System.out.printf("%s: %d\n", item.name, item.freq);
            }
        }

        // выполнение задания №3
        /* 3. Реализовать алгоритм пирамидальной сортировки (HeapSort). */
        {
            System.out.println(list);

            ArrayList<Integer> sortedList = heapSort(list, list.size());
            System.out.println(sortedList);
        }
    }


    public static Map<String, HashSet<phone_Number>> create_phone_book() {
        // создание сканера
        Scanner myScanner = new Scanner(System.in);

        // создание телефонной книги
        Map<String, HashSet<phone_Number>> phone_book = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            HashSet<phone_Number> hashSet = new HashSet<>();
            full_Name full_name = new full_Name();
            phone_Number phone_number = new phone_Number();
            String name;

            System.out.print("Введите имя: ");
            full_name.first_name = myScanner.next();
            System.out.print("Введите фамилию: ");
            full_name.last_name = myScanner.next();

            name = full_name.first_name + " " + full_name.last_name;

            System.out.print("Введите код страны: ");
            phone_number.country = myScanner.nextInt();
            System.out.print("Введите код города: ");
            phone_number.city = myScanner.nextInt();
            System.out.print("Введите номер: ");
            phone_number.number = myScanner.nextInt();

            hashSet.add(phone_number);
            System.out.println(phone_book.containsKey(name));
            if (phone_book.containsKey(name)) {
                for (HashMap.Entry<String, HashSet<phone_Number>> item : phone_book.entrySet()) {
                    if (item.getKey().equals(name)) {
                        hashSet.addAll(item.getValue());
                        item.setValue(hashSet);
                    }
                }
            } else {
                phone_book.put(name, hashSet);
            }
        }

        return phone_book;
    }


    public static List<Popularity> popular_names(List<full_Name> list) {
        Map<String, Integer> resultMap = new HashMap<>();
        List<Popularity> resultList = new ArrayList<>();

        for (full_Name fullName : list) {
            if (resultMap.containsKey(fullName.first_name)) {
                resultMap.put(fullName.first_name, resultMap.get(fullName.first_name) + 1);
            } else {
                resultMap.put(fullName.first_name, 1);
            }
        }

        for (Map.Entry<String, Integer> item : resultMap.entrySet()) {
            Popularity popularity = new Popularity();
            popularity.name = item.getKey();
            popularity.freq = item.getValue();
            resultList.add(popularity);
        }

        for (int i = 0; i < resultList.size() - 1; i++) {
            for (int j = i + 1; j < resultList.size(); j++) {
                if (resultList.get(i).freq < resultList.get(j).freq) {
                    Popularity tmp = resultList.get(i);
                    resultList.set(i, resultList.get(j));
                    resultList.set(j, tmp);
                }
            }
        }

        return resultList;
    }

    static ArrayList<Integer> list = new ArrayList<>(
            List.of(11, 3, 5, 17, 28, 34, 3, 10, 8, 44, 18, 7, 13, 61));

    private static ArrayList<Integer> heapSort(ArrayList<Integer> list, int length) {
        // Выход из рекурсии
        if (length == 1) return list;

        // Ищем индекс крайнего в списке родителя
        int lastParent = findLastParentIndex(length);

        // Сравниваем родителей и наследников, затем присваиваем родителю максимальное значение.
        for (int parent = lastParent; parent >= 0; parent--) {
            int leftChild = parent * 2 + 1;
            int rightChild = parent * 2 + 2;

            if (leftChild == length - 1) rightChild = leftChild;

            int max = leftChild;
            if (list.get(rightChild) > list.get(max)) max = rightChild;

            if (list.get(parent) < list.get(max)) swapIndexes(list, max, parent);
        }
        swapIndexes(list, 0, length - 1);
        return heapSort(list, length - 1);
    }

    /* Метод для перестановки местами элементов под определенными индексами */
    private static void swapIndexes(ArrayList<Integer> list, int i, int j) {
        Collections.swap(list, i, j);
    }

    /* Метод нахождения последнего родителя */
    private static int findLastParentIndex(int listSize) {
        int i;
        if (listSize % 2 == 0) {
            i = (listSize - 1) / 2;
        } else {
            i = (listSize - 2) / 2;
        }
        return i;
    }

}


class phone_Number {
    public int country = 0;
    public int city = 0;
    public int number = 0;
}


class full_Name {
    String first_name;
    String last_name;
}


class Popularity {
    String name;
    Integer freq;
}