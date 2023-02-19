package Lesson_06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Lesson_06 {
    /** Список возможных операционных систем */
    static List<String> os_list = new ArrayList<>(List.of(
            "Windows 10 Home", "Windows 10 Pro", "Windows 10 Ultra",
            "Windows 11 Home", "Windows 11 Pro", "Windows 11 Ultra"
    ));
    /** список возможных названий ноутбуков */
    static List<String> name_list = new ArrayList<>(List.of(
            "Acer", "Samsung", "Dell", "Asus",
            "Hewlett-Packard", "Xiaomi", "BQ"
    ));
    /** список возможных цветов */
    static List<String> color_list = new ArrayList<>(List.of(
            "Красный", "Зеленый", "Синий", "Белый", "Черный", "Желтый", "Розовый"
    ));
    /** сканер для чтения данных, введенных с клавиатуры */
    static Scanner myScanner = new Scanner(System.in);


    /**  Главный метод для проверки работоспособности реализованных методов */
    public static void main(String[] args) {
        // Создание множества ноутбуков
        Set<Laptop> laptopSet = createSetOfLaptops();

        // Запрос очереди фильтрации у пользователя совместно с минимальными
        // и максимальными диапазонами для числовых значений
        Map<Integer, String> filterQueueMap = createFilterQueue();

        // Вывод отфильтрованных данных
        Set<Laptop> filteredData = getFilteredData(laptopSet, filterQueueMap);
        System.out.println("Отфильтрованные данные:");
        showData(filteredData);
    }


    /** <p>Метод для автогенерации списка данных ноутбуков</p>
     * @return список сгенерированных ноутбуков */
    public static Set<Laptop> createSetOfLaptops() {
        // переменная-рандомазер
        Random random = new Random();
        // возвращаемый сгенерированный список
        Set<Laptop> laptopSet = new HashSet<>();

        // генерируем определенное количество ноутбуков в цикле
        for (int i = 0; i < 1000; i++) {
            String name = name_list.get(random.nextInt(name_list.size()));
            // ПЗУ и ОЗУ генерируются числами-степенями двойки от 2^1 до 2^8 (2^12)
            int rom = (int) (Math.pow(2.0, (double)((int)((Math.random() * 8) + 1))));
            int hdd = (int) (Math.pow(2.0, (double)((int)((Math.random() * 12) + 1))));
            String os = os_list.get(random.nextInt(os_list.size()));
            String color = color_list.get(random.nextInt(color_list.size()));

            laptopSet.add(new Laptop(name, rom, hdd, os, color));
        }

        return laptopSet;
    }


    /** <p>Метод для создания фильтра пользователем. Если фильтра числовой, то значение в паре
     * "ключ"-"значение" задается в виде диапазона: строка с двумя числами, разделенными "-"</p>
     * @return словарь фильтров в формате "номер фильтра"-"значение фильтра" */
    public static Map<Integer, String> createFilterQueue() {
        // запрашиваемый у пользователя словарь фильтров
        Map<Integer, String> queueMap = new HashMap<>();
        // ответ от пользователя для продолжения добавления фильтров
        char answer = 'y';

        // цикл с постусловием используется для того, чтобы хотя бы раз пользователь выбрал фильтр
        // перед окончанием цикла проверяется правильную ли цифру ввел пользователь
        // если ошибся, то должен попробовать еще раз добавить фильтр
        do {
            System.out.println("Выберите фильтр:");
            System.out.println("0 - вывести все ноутбуки");
            System.out.println("1 - по названию");
            System.out.println("2 - по ПЗУ");
            System.out.println("3 - по ОЗУ");
            System.out.println("4 - по ОС");
            System.out.println("5 - по цвету");
            System.out.print("Ваш выбор: ");

            int filter = myScanner.nextInt();
            // флаг того, что пользователь ввел число от 0 до 5 (вкл.)
            boolean right_number = true;

            // если выбран текстовый фильтр, то вводится текст
            // если числовой, то два числа: левая и правая границы диапазонов
            // если 0, то добавляем его в фильтр и прерываем цикл досрочно
            if (filter == 1 || filter == 4 || filter == 5) {
                System.out.print("Введите текст фильтра: ");
                String stringFilter = myScanner.next();
                queueMap.put(filter, stringFilter);
            } else if (filter == 2 || filter == 3) {
                int leftRange;
                int rightRange;
                System.out.print("Введите левую и правую границы диапазона поиска: ");
                leftRange = myScanner.nextInt();
                rightRange = myScanner.nextInt();
                queueMap.put(filter, leftRange + "-" + rightRange);
            } else if (filter == 0) {
                queueMap.clear();
                queueMap.put(filter, "");
                break;
            } else {
                System.out.println("Введено неправильное число! Повторите попытку!");
                right_number = false;
            }

            // если ранее номер фильтра был введен правильно, то запрашиваем у пользователя продолжение ввода фильтров
            if (right_number) {
                System.out.print("Хотите добавить еще один фильтр?(n/y)");
                answer = myScanner.next().charAt(0);
            }
        } while (answer != 'n' && answer != 'N' && answer != 'н' && answer != 'Н');

        return queueMap;
    }


    /** <p>Метод для фильтрования данных указанному фильтру</p>
     * @param sourceData исходный список данных о ноутбуках
     * @param filterMap список фильтров */
    public static Set<Laptop> getFilteredData(Set<Laptop> sourceData, Map<Integer, String> filterMap) {
        // отфильтрованный список данных о ноутбуках
        Set<Laptop> filteredSet = new HashSet<>();

        // проходимся по каждому ноутбуку в исходном списке
        for (Laptop laptop : sourceData) {
            // флаг того, что ноутбук подходит под фильтр
            boolean fits_desc = true;
            // проходимся по каждому из указанных в словаре фильтров
            for (Integer filter : filterMap.keySet()) {
                if (filter == 0) {
                    break;
                } else {
                    // если в словаре не нулевой фильтр, тогда по каждому из фильтров идем отдельно и ищем несоответствия
                    // в текстовых фильтрах смотрим на полное равенство значений
                    // в числовых фильтрах смотрим на то, попадает ли число в заданный диапазон
                    switch (filter) {
                        case 1 -> {
                            if (!filterMap.get(filter).equals(laptop.getName()))
                                fits_desc = false;
                        }
                        case 2 -> {
                            String property = filterMap.get(filter);
                            int leftRange = Integer.parseInt(property.substring(0, property.indexOf('-')));
                            int rightRange = Integer.parseInt(property.substring(property.indexOf('-') + 1));
                            if (!(laptop.getRom() >= leftRange && laptop.getRom() <= rightRange))
                                fits_desc = false;
                        }
                        case 3 -> {
                            String property = filterMap.get(filter);
                            int leftRange = Integer.parseInt(property.substring(0, property.indexOf('-')));
                            int rightRange = Integer.parseInt(property.substring(property.indexOf('-') + 1));
                            if (!(laptop.getHdd() >= leftRange && laptop.getHdd() <= rightRange))
                                fits_desc = false;
                        }
                        case 4 -> {
                            if (!filterMap.get(filter).equals(laptop.getOs()))
                                fits_desc = false;
                        }
                        case 5 -> {
                            if (!filterMap.get(filter).equals(laptop.getColor()))
                                fits_desc = false;
                        }
                    }
                }
            }

            // по окончанию прохода по фильтрам добавляем в результирующий список выбранный ноутбук,
            // если он подходит под описание
            if (fits_desc) {
                filteredSet.add(laptop);
            }
        }

        return filteredSet;
    }


    /** <p>Метод для вывода на экран списка ноутбуков</p>
     * @param laptopSet список ноутбуков для вывода */
    public static void showData(Set<Laptop> laptopSet) {
        int counter = 1;
        for (Laptop laptop : laptopSet) {
            System.out.printf("\t\t\tНоутбук №%d\n%s", counter, laptop);
            counter++;
        }
    }
}
