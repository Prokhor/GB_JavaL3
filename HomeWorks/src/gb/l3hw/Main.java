package gb.l3hw;

/*
1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
2. Написать метод, который преобразует массив в ArrayList;
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f,
не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра,
true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать
в коробку с апельсинами), соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static double APPLE_WEIGHT = 1.0;
    private static double ORANGE_WEIGHT = 1.5;

    public static void main(String[] args) {
        // 1.
        Integer[] arrInt1 = new Integer[]{ 10, 20, 30, 40, 50, 60, 70, 80};
        System.out.printf("Source: %s\n", Arrays.toString(arrInt1));
        try{
            swapElements(arrInt1, 3, 6);
            System.out.printf("Swapped: %s\n", Arrays.toString(arrInt1));
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        String[] arrStr1 = new String[]{"a", "b", "c", "d", "f", "g", "h", "i", "j", "k"};
        System.out.printf("Source: %s\n", Arrays.toString(arrStr1));
        try {
            swapElements(arrStr1, 2, 5);
            System.out.printf("Swapped: %s\n", Arrays.toString(arrStr1));
            swapElements(arrStr1, 2, 50);
            System.out.printf("Swapped: %s\n", Arrays.toString(arrStr1));
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        Boolean[] arrBln = new Boolean[]{true, true, false, true, false, true, true, false, true, false};
        try {
            swapElements(arrBln, 2, 3);
            System.out.printf("Swapped: %s\n", Arrays.toString(arrBln));
            swapElements(arrBln, 2, 30);
            System.out.printf("Swapped: %s\n", Arrays.toString(arrBln));
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        System.out.println();
        // 2.
        Integer[] arrInt2 = new Integer[]{1, 2, 3};
        ArrayList<Integer> arrayListInt = returnAsList(arrInt2);
        System.out.println(arrayListInt);
        String[] arrStr2 = new String[]{"one", "two", "three"};
        ArrayList<String> arrayListStr = returnAsList(arrStr2);
        System.out.println(arrayListStr);

        System.out.println();
        // 3.
        testTask3();
    }

    private static void printBoxWeight(Box<?> box){
        System.out.printf("Box [%s] weight: %.2f\n", box.getBoxName(), box.getBoxWeight());
    }

    // 3.
    private static void testTask3(){
        Box<Apple> appleBox1 = new Box<>(new ArrayList<>(), "appleBox1");
        Box<Apple> appleBox2 = new Box<>(new ArrayList<>(), "appleBox2");
        Box<Apple> appleBox3 = new Box<>(new ArrayList<>(), "appleBox3");

        Box<Orange> orangeBox1 = new Box<>(new ArrayList<>(), "orangeBox1");
        Box<Orange> orangeBox2 = new Box<>(new ArrayList<>(), "orangeBox2");
        Box<Orange> orangeBox3 = new Box<>(new ArrayList<>(), "orangeBox3");

        for (int i = 0; i < 10; i++) {
            appleBox1.putFruit(new Apple(APPLE_WEIGHT));
        }
        for (int i = 0; i < 15; i++) {
            appleBox2.putFruit(new Apple(APPLE_WEIGHT));
        }
        for (int i = 0; i < 19; i++) {
            appleBox3.putFruit(new Apple(APPLE_WEIGHT));
        }

        for (int i = 0; i < 10; i++) {
            orangeBox1.putFruit(new Orange(ORANGE_WEIGHT));
        }
        for (int i = 0; i < 15; i++) {
            orangeBox2.putFruit(new Orange(ORANGE_WEIGHT));
        }
        for (int i = 0; i < 20; i++) {
            orangeBox3.putFruit(new Orange(ORANGE_WEIGHT));
        }

        printBoxWeight(appleBox1);
        printBoxWeight(appleBox2);
        printBoxWeight(appleBox3);

        printBoxWeight(orangeBox1);
        printBoxWeight(orangeBox2);
        printBoxWeight(orangeBox3);

        System.out.println("Pour over from apple box 1 to apple box 3");
        appleBox1.pourOver(appleBox3);
        printBoxWeight(appleBox1);
        printBoxWeight(appleBox2);
        printBoxWeight(appleBox3);

        System.out.println("Pour over from orange box 3 to orange box 2");
        orangeBox3.pourOver(orangeBox2);
        printBoxWeight(orangeBox1);
        printBoxWeight(orangeBox2);
        printBoxWeight(orangeBox3);

        System.out.println("Compare same weight boxes");
        System.out.println(appleBox2.compare(orangeBox1));
        System.out.println("Compare same weight boxes");
        System.out.println(appleBox1.compare(orangeBox3));
        System.out.println("Compare different weight boxes");
        System.out.println(appleBox3.compare(orangeBox2));
    }
    // 1.
    private static Object[] swapElements(Object[] array, int i, int k){
        if (i < array.length && k < array.length && i >= 0 && k >= 0) {
            Object iElem = array[i];
            array[i] = array[k];
            array[k] = iElem;
            return array;
        } else {
            throw new ArrayIndexOutOfBoundsException("Not correct arguments");
        }
    }
    // 2.
    private static <T> ArrayList<T> returnAsList(T[] array){
        return new ArrayList<>(Arrays.asList(array));
    }

}
