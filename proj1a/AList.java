/** Array based list. */

public class AList {

    private int[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = new int[100];
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        int[] newItems = new int[capacity];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[size++] = x;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {
        if (size < 0.25 * items.length) {
            resize(items.length / 2);
        }
        int last = items[size - 1];
        size -= 1;
        return last;
    }

    public static void main(String[] args) {
        AList L = new AList();
        L.addLast(99);
        L.addLast(99);
        System.out.println(L.size());
    }
}
