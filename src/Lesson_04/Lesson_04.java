package Lesson_04;

import java.io.IOException;
import java.util.LinkedList;

public class Lesson_04 {
    public static void main(String[] args) {
        // выполнение задания №1
        /* 1. Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список. */
        {
            System.out.println("\t\tЗадание №1");
            System.out.println("Исходный список:");
            LinkedList<Integer> list = new LinkedList<Integer>();
            for (int i = 0; i < 10; i++) {
                list.addFirst((int) (Math.random() * 100));
            }
            System.out.println(list);
            LinkedList<Integer> result = reverse_linked_list(list);
            System.out.println("Результат переворачивания исходного списка:");
            System.out.println(result);
            System.out.println("\t\t----------------");
        }

        // выполнение задания №2
        /* 2. Реализуйте очередь с помощью LinkedList со следующими методами:
        a) enqueue() - помещает элемент в конец очереди,
        b) dequeue() - возвращает первый элемент из очереди и удаляет его,
        c) first() - возвращает первый элемент из очереди, не удаляя. */
        {
            System.out.println("\t\tЗадание №2");
            LinkedList<Integer> list = new LinkedList<>();
            System.out.println("Попытаемся удалить элемент из пустой очереди:");
            System.out.println(dequeue(list));
            for (int i = 0; i < 10; i++) {
                list.add((int)(Math.random() * 100));
            }
            System.out.println("В очереди находятся следующие элементы:");
            System.out.println(list);
            System.out.print("Вернем головной элемент очереди:\n");
            System.out.println(dequeue(list));
            System.out.println("Удален ли он из очереди?");
            System.out.println(list);

            System.out.println("Добавим новый элемент в конец очереди:");
            enqueue(list, 30);
            System.out.println(list);

            System.out.println("Подсмотрим первый элемент из очереди, но не удалять его из нее не будем:");
            System.out.printf("Первый элемент: %d\n", first(list));
            System.out.println("Очередь не изменилась?");
            System.out.println(list);
            System.out.println("\t\t----------------");
        }
    }


    /** <p>Функция разворачивает передаваемый список</p>
     * @param list исходный список
     * @return перевернутый исходный список
     */
    public static LinkedList<Integer> reverse_linked_list(LinkedList<Integer> list) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        for (Integer integer : list) {
            result.addFirst(integer);
        }
        return result;
    }


    /** <p>Функция, помещающая новый элемент в очередь</p>
     * @param list исходный список, являющийся очередью
     * @param integer элемент для добавления*/
    public static void enqueue(LinkedList<Integer> list, Integer integer) {
        list.addLast(integer);
    }


    /** <p>Функция, возвращающая первый элемент из очереди с удалением</p>
     * @param list исходный список, являющийся очередью
     * @return элемент из очереди с удалением, при ошибке - -1 и сообщение об ошибке*/
    public static int dequeue(LinkedList<Integer> list) {
        try {
            return list.pop();
        } catch (Exception e) {
            System.out.printf("Список пуст! Возвращено %d!\n", -1);
            return -1;
        }
    }


    /** <p>Функция, подсматривающая первый элемент в очереди без удаления</p>
     * @param list исходный список, являющийся очередью
     * @return элемент из очереди без удаления, при ошибке - -1 и сообщение об ошибке*/
    public static Integer first(LinkedList<Integer> list) {
        try {
            return list.getFirst();
        } catch (Exception e) {
            System.out.printf("Список пуст! Возвращено %d!\n", -1);
            return -1;
        }
    }
}
