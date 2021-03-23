import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class PriorityQueueTest {
    private static Stream<Arguments> priorityQueueParamProvider() {
        return Stream.of(
            Arguments.of(new int[]{1, 3, 5, -3, 15}, new int[]{-3, 1, 3, 5, 15}),
            Arguments.of(new int[]{6, 0, 7, -38, 87}, new int[]{-38, 0, 6, 7, 87}),
            Arguments.of(new int[]{681, 724, 555, -308, 1}, new int[]{-308, 1, 555, 681, 724}),
            Arguments.of(new int[]{100, -3, 50, -3, 15}, new int[]{-3, -3, 15, 50, 100}),
            Arguments.of(new int[]{0, 0, 0, -3, -15}, new int[]{-15, -3, 0, 0, 0})
        );
    }

    @ParameterizedTest(name = "#{index} - Test with Argument = {0}, {1}")
    @MethodSource("priorityQueueParamProvider")
    public void parameterizedTest(int [] randomArray, int [] correctArray) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for(int i = 0; i < randomArray.length; i++) queue.add(randomArray[i]);

        int[] result = new int[randomArray.length];
        for(int i = 0; i < correctArray.length; i++) result[i] = queue.poll();

        assertTrue(queue.isEmpty());
        assertArrayEquals(correctArray, result);
    }

    @Test
    public void ClassCastExceptionTest() {
        Exception exception = assertThrows(ClassCastException.class, () -> {
            PriorityQueue<Object> queue = new PriorityQueue<Object>();
            queue.add(new int [1]);
        });
    }

    @Test
    public void NullPointerExceptionTest1() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Object> queue = new PriorityQueue<Object>();
            queue.add(null);
        });
    }

    @Test
    public void NullPointerExceptionTest2() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Object> queue = new PriorityQueue<Object>();
            Object a[] = null;
            queue.toArray(a);
        });
    }
}
