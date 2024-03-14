
package QuantitySet;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Seth
 * @param <E>
 */
public interface QuantitySetADT<E> {
    public boolean add(E item, int amount);

    public boolean add(E item);

    public boolean contains(E item);

    public boolean remove(E item, int amount);

    public boolean removeAll(E item);

    public int getQuantity(E item);

    public int totalQuantity();

    public int size();

    public boolean isEmpty();

    public void clear();
}