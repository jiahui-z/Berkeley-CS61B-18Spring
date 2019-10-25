public class LinkedListDeque<T> {

    private class ListNode {
        private ListNode prev;
        private T item;
        private ListNode next;

        public ListNode(ListNode prev, T item, ListNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private ListNode sentinel;
    private int size;

    /**
     * Creates an empty LinkedListDeque.
     */
    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Creates the LinkedListDeque with one node.
     * @param x
     */
    public LinkedListDeque(T x) {
        sentinel = new ListNode(null, null, null);
        sentinel.next = new ListNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /**
     * Adds an item of type T to the front of the deque,
     * takes constant time.
     * @param item the item to be added
     */
    public void addFirst(T item) {
        size += 1;
        ListNode first = sentinel.next;
        sentinel.next = new ListNode(sentinel, item, first);
        first.prev = sentinel.next;
    }

    /**
     * Adds an item of type T to the back of the deque,
     * takes constant time.
     * @param item the item to be added
     */
    public void addLast(T item) {
        size += 1;
        ListNode last = sentinel.prev;
        last.next = new ListNode(last, item, sentinel);
        sentinel.prev = last.next;
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
     * takes constant time.
     * @return size of the deque
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        int iteration = size;
        ListNode ptr = sentinel.next;
        while (iteration > 0) {
            System.out.print(ptr.item + " ");
            iteration--;
            ptr = ptr.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * Takes constant time.
     * @return the removed item
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        ListNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        return first.item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * Takes constant time.
     * @return the removed item
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        ListNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        return last.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Iterative approach.
     * @param index index of the item, zero-based
     * @return item
     */
    public T get(int index) {
        ListNode ptr;

        if (index < 0 || index >= size) {
            return null;
        } else if (index < size / 2) {
            ptr = sentinel.next;
            while (index > 0) {
                index--;
                ptr = ptr.next;
            }
        } else {
            ptr = sentinel.prev;
            while (size - 1 - index > 0) {
                index++;
                ptr = ptr.prev;
            }
        }

        return ptr.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Recursive approach.
     * @param index index of the item, zero-based
     * @return item
     */
    public T getRecursive(int index) {
        return getHelper(sentinel.next, index);
    }

    private T getHelper(ListNode ptr, int index) {
        if (index == 0) {
            return ptr.item;
        }
        return getHelper(ptr.next, index - 1);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(1);
        deque.addLast(2);
        deque.addFirst(4);
        System.out.println(deque.getRecursive(0));
    }
}
