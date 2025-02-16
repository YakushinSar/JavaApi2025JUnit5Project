package lesson2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class FirstUnitTest {

    @Test
    void sumNumberTest(){
        /* структура теста AAA (некjторых частей может не быть)
        -> Arrange(предусловия)
        -> Act(само действие)
        -> Assert(сравнение результата)
        */
        int a = 2;
        int b = 3;

        int actualSum = a + b;
        int expectedSum = 5;

        assertEquals(expectedSum,actualSum);
        assertEquals(5,a + b);
    }

    @Test
    @Disabled //тест не будет запущен,  в отчет попадет как ignored
    void disabledTest() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum);
        System.out.println("Test");
    }

    @Test
    @DisplayName("Сложение двух чисел")
    void titleTest() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum);
    }

    @Test
    @Tag("smoke")
    void tagTest() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum, "Суммы должны быть разными");
    }

    @Test
    @Tags({@Tag("defect"), @Tag("smoke")}) // несколько тегов
    @Timeout(value = 2)
    void timeoutTest() throws InterruptedException {
        Thread.sleep(2000);
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum);
    }

    @RepeatedTest(value = 3, name = "{displayName} - повторение {currentRepetition} из {totalRepetitions}")
    @DisplayName("Сложение двух чисел")
    void repeatedTest() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum);
    }

    @Test
    void assertTrueFalseTest() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertTrue(expectedSum == actualSum);
        assertFalse(expectedSum != actualSum);
    }

    @Test
    void exceptionTest() {
        String test = null;
        Exception thrown = assertThrows(NullPointerException.class, () -> test.length());

        Assertions.assertEquals("Cannot invoke \"String.length()\" because \"test\" is null", thrown.getMessage());
    }

    @Test
    void assertsAllTest() {
        User user = new User("John", "Doe", 30);
        assertAll(
                () -> assertEquals("John", user.getFirstName(), "Неправильное имя"),
                () -> assertEquals("Doe", user.getLastName(), "Неправильная фамилия"),
                () -> assertEquals(30, user.getAge(), "Неправильный возраст")
        );
    }

    @Test
    void AssertAllSeparateTest() {
        User user = new User("John", "Doe", 30);
        assertEquals("John1", user.getFirstName(), "Неправильное имя");
        assertEquals("Doe2", user.getLastName(), "Неправильная фамилия");
        assertEquals(31, user.getAge(),  "Неправильный возраст");
    }


}
