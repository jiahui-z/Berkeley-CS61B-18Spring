public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty linked list deque.
     */
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private void resize(int capacity) {
        T[] newItems = (T []) new Object[capacity];
        if (size == items.length) {
            System.arraycopy(items, nextFirst + 1, newItems, 0, size - nextLast);
            System.arraycopy(items, 0, newItems, size - nextLast, nextLast);
        } else {
            if (nextFirst < nextLast) {
                System.arraycopy(items, nextFirst + 1, newItems, 0, nextLast - 1 - nextFirst);
            } else {
                System.arraycopy(items, nextFirst + 1, newItems, 0, size - nextLast);
                System.arraycopy(items, 0, newItems, 0, nextLast);
            }
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = newItems;
    }

    /**
     * Adds an item of type T to the front of the deque,
     * takes constant time except for resizing process.
     * @param item the item to be added
     */
    public void addFirst(T item) {
        if (nextFirst == 0) {
            items[nextFirst] = item;
            nextFirst = items.length - 1;
        } else {
            items[nextFirst--] = item;
        }
        size++;
        if (size == items.length) {
            resize(2 * size);
        }
    }

    /**
     * Adds an item of type T to the back of the deque,
     * takes constant time except for resizing process.
     * @param item the item to be added
     */
    public void addLast(T item) {
        if (nextLast == items.length - 1 && nextFirst < nextLast) {
            items[nextLast] = item;
            nextLast = 0;
        } else {
            items[nextLast++] = item;
        }
        size++;
        if (size == items.length) {
            resize(2 * size);
        }
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * @return bool of whether the deque is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque,
     * takes constant time except for resizing process.
     * @return size of the deque
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = nextFirst + 1; i < items.length; i++) {
            System.out.print(items[i] + " ");
        }
        for (int j = 0; j < nextLast; j++) {
            System.out.print(items[j] + " ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * Takes constant time except for resizing process.
     * @return the removed item
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        T removed = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (size < 0.25 * items.length) {
            resize(items.length / 2);
        }
        return removed;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * Takes constant time except for resizing process.
     * @return the removed item
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        T removed = items[nextLast];
        items[nextLast] = null;
        size--;
        if (size < 0.25 * items.length) {
            resize(items.length / 2);
        }
        return removed;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Iterative approach.
     * @param index index of the item, zero-based
     * @return item
     */
    public T get(int index) {
        if (nextFirst < nextLast) {
            return items[nextFirst + 1 + index];
        } else {
            if (index < items.length - 1 - nextFirst) {
                return items[nextFirst + 1 + index];
            } else {
                return items[index - (items.length - 1 - nextFirst)];
            }
        }
    }
}
