package gb.l3hw;

import java.util.ArrayList;

// сделал, чтобы в коробку можно было класть только фрукты.
public class Box<T extends Fruit> {

    private ArrayList<T> fruits;
    private String boxName;

    public Box(ArrayList<T> fruits, String boxName) {
        this.fruits = fruits;
        this.boxName = boxName;
    }

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    public void putFruit(T fruit){
        fruits.add(fruit);
    }

    public String getBoxName() {
        return boxName;
    }

    public void pourOver(Box<T> anotherBox){
        anotherBox.fruits.addAll(this.fruits);
        fruits.clear();
    }

    public double getBoxWeight(){
        double weight = 0.0;
        for (int i = 0; i < fruits.size(); i++) {
            weight += fruits.get(i).getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> anotherBox){
        return getBoxWeight() - anotherBox.getBoxWeight() >= 0 && getBoxWeight() - anotherBox.getBoxWeight() < 0.000001;
    }
}
