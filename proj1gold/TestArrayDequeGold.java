import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        ArrayDequeSolution<Integer> correctDeque = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> wrongDeque = new StudentArrayDeque<>();

        int num = 0;
        StringBuilder operations = new StringBuilder();
        while (true) {
            double randomNumber = StdRandom.uniform();

            if (randomNumber < 0.2) {
                correctDeque.addFirst(num);
                wrongDeque.addFirst(num);
                operations.append(String.format("addFirst(%d)", num) + "\n");
                num++;
            } else if (randomNumber < 0.4) {
                correctDeque.addLast(num);
                wrongDeque.addLast(num);
                operations.append(String.format("addLast(%d)", num) + "\n");
                num++;
            } else if (randomNumber < 0.6) {
                if (!correctDeque.isEmpty() && !wrongDeque.isEmpty()) {
                    Integer expected = correctDeque.removeFirst();
                    Integer actual = wrongDeque.removeFirst();
                    operations.append(String.format("removeFirst()") + "\n");
                    assertEquals(operations.toString(), expected, actual);
                }
            } else if (randomNumber < 0.8) {
                if (!correctDeque.isEmpty() && !wrongDeque.isEmpty()) {
                    Integer expected = correctDeque.removeLast();
                    Integer actual = wrongDeque.removeLast();
                    operations.append(String.format("removeLast()") + "\n");
                    assertEquals(operations.toString(), expected, actual);
                }
            } else {
                Integer expected = correctDeque.size();
                Integer actual = wrongDeque.size();
                operations.append(String.format("size()") + "\n");
                assertEquals(operations.toString(), expected, actual);
            }
        }
    }
}
