
package QuantitySet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Ajit Singh
 * 
 */
public class QuantitySet<E> implements QuantitySetADT<E> {

    private int numElements;
    private int totalQuantity;
    private Map<E, Node<E>> itemList;

    public QuantitySet() {
        itemList = new HashMap<>();
        this.numElements = this.totalQuantity = 0;
    }

    public QuantitySet(Collection<? extends E> c) {
        this();
        for (E element : c) {
            add(element);
        }
    }

    @Override
    public boolean add(E item, int amount) {
        if (item == null || amount < 1)
            return false;

        if (contains(item)) {
            Node<E> obj = itemList.get(item);
            int counter = obj.quantity;
            counter += amount;
            itemList.put(item, new Node<E>(obj.label, counter));
            this.totalQuantity += counter;
            return true;
        }

        itemList.put(item, new Node<E>(item, amount));
        this.numElements++;
        this.totalQuantity += amount;
        return true;
    }

    @Override
    public boolean add(E item) {
        if (item == null)
            return false;

        if (contains(item)) {
            Node<E> obj = itemList.get(item);
            int counter = obj.quantity;
            counter++;
            itemList.put(item, new Node<E>(obj.label, counter));
            this.totalQuantity += counter;
            return true;
        }

        itemList.put(item, new Node<E>(item, 1));
        this.numElements++;
        this.totalQuantity += 1;
        return true;
    }

    @Override
    public boolean contains(E item) {
        return itemList.containsKey(item);
    }

    @Override
    public boolean remove(E item, int amount) {
        if (item == null)
            return false;

        if (contains(item)) {
            Node<E> obj = itemList.get(item);
            int counter = obj.quantity;
            counter -= amount;

            if (counter <= 0) {
                removeAll(obj.label);
                return true;
            }

            itemList.put(item, new Node<E>(obj.label, counter));
            this.totalQuantity -= counter;
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(E item) {
        if (item == null)
            return false;

        if (contains(item)) {
            Node<E> obj = itemList.get(item);
            int counter = obj.quantity;
            itemList.remove(item);
            this.numElements--;
            this.totalQuantity -= counter;
            return true;
        }

        return false;
    }

    @Override
    public int getQuantity(E item) {
        return itemList.get(item).quantity;
    }

    @Override
    public int totalQuantity() {
        return this.totalQuantity;
    }

    @Override
    public int size() {
        return this.numElements;
    }

    @Override
    public boolean isEmpty() {
        return this.numElements == 0;
    }

    @Override
    public void clear() {
        for (E key : itemList.keySet()) {
            itemList.put(itemList.get(key).label, null);
        }

    }

    @Override
    public String toString() {
        String str = "{";

        for (Node<E> node : itemList.values()) {
            str += node.label + "(" + node.quantity + "), ";
        }

        return str + "}";
    }

    private class Node<E> {
        E label;
        int quantity;

        public Node(E label, int quantity) {
            this.label = label;
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {
        QuantitySet<Character> abcSet = new QuantitySet<>();
        /* add() test */
        abcSet.add('A');
        abcSet.add('A');
        abcSet.add('A');
        abcSet.add('B');
        abcSet.add('C');
        abcSet.add('D');

        /* add() overloaded test */
        abcSet.add('B', 5);
        abcSet.add('D', 0);
        abcSet.add('C', 4);
        abcSet.add(null, 4);
        abcSet.add('A', -100);

        // System.out.println("orignal: " + abcSet);

        /* remove() test */
        // abcSet.remove('B', 4);
        // abcSet.remove('A', 3);
        // abcSet.remove(null, 4);

        // System.out.println("after remove(): " + abcSet);

        /* removeAll() test */
        // abcSet.removeAll('D');
        // abcSet.removeAll('C');

        // System.out.println("after removeAll(): " + abcSet);

        /* totalQuantity() test */
        System.out.println("total quantity: " + abcSet.totalQuantity());

        /* getQuantity() test */
        System.out.println("quantity of A: " + abcSet.getQuantity('A'));
        abcSet.add('A', 5);
        System.out.println("quantity of A (after update): " + abcSet.getQuantity('A'));

        /* size() test */
        System.out.println("size(): " + abcSet.size());

        /* contains() test */
        System.out.println("contains A: " + abcSet.contains('A'));
        System.out.println("contains D: " + abcSet.contains('D'));
        System.out.println("contains null: " + abcSet.contains(null));
        System.out.println("contains Z: " + abcSet.contains('Z'));

        System.out.println(abcSet);

    }

}
