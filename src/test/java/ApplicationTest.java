import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void random3Num() {
        Application app = new Application();
        int[] arr = app.random3Num();
        assertTrue(arr.length == 3 && (arr[0] != arr[1] && arr[1] != arr[2] && arr[0] != arr[2]) );
    }

    @Test
    void userInputTrue() {
        Application app = new Application();
        System.setIn(new ByteArrayInputStream("123".getBytes()));
        int[] arr = {1,2,3};
        assertArrayEquals(arr, app.userInput());
    }

    @Test
    void isErrorLengthException() {
        Application app = new Application();
        String ss = "123456";
        assertThrows(IllegalStateException.class, () -> app.isError(ss));
    }

    @Test
    void isErrorTypeException() {
        Application app = new Application();
        String ss = "asd";
        assertThrows(IllegalStateException.class, () -> app.isError(ss));
    }

    @Test
    void isErrorDuplicateException() {
        Application app = new Application();
        String ss = "111";
        assertThrows(IllegalStateException.class, () -> app.isError(ss));
    }

    @Test
    void isAnswerTrue() {
        Application app = new Application();
        int[] com = {1, 2, 3};
        int[] user = {1, 2, 3};
        assertTrue(app.isAnswer(com, user));
    }

    @Test
    void isAnswerFalse() {
        Application app = new Application();
        int[] com1 = {1, 2, 3};
        int[] user1 = {3, 2, 1};
        assertFalse(app.isAnswer(com1, user1));

        int[] com2 = {1, 2, 3};
        int[] user2 = {7, 8, 9};
        assertFalse(app.isAnswer(com2, user2));
    }

    @Test
    void strike() {
        Application app = new Application();
        int[] com = {1, 2, 3};
        int[] user = {1, 4, 3};
        assertEquals(2, app.strike(com, user));
    }

    @Test
    void ball() {
        Application app = new Application();
        int[] com = {1, 2, 3};
        int[] user = {3, 4, 1};
        assertEquals(2, app.ball(com, user));
    }

    @Test
    void restartTrue() {
        Application app = new Application();
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        assertTrue(app.restart());
    }

    @Test
    void restartFalse() {
        Application app = new Application();
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        assertFalse(app.restart());
    }

    @Test
    void restartException1() {
        Application app = new Application();
        System.setIn(new ByteArrayInputStream("5".getBytes()));
        assertThrows(IllegalStateException.class, () -> app.restart());
    }

    @Test
    void restartException2() {
        Application app = new Application();
        System.setIn(new ByteArrayInputStream("aa".getBytes()));
        assertThrows(IllegalStateException.class, () -> app.restart());
    }
}