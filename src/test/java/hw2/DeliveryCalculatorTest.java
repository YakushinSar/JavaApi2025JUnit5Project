package hw2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeliveryCalculatorTest {
    @Test
    @Tag("Позитивный тест")
    @DisplayName("Большой размер груза")
    void largeTest() {
        Delivery delivery = new Delivery(5, CargoDimension.LARGE, false, ServiceWorkload.NORMAL);

        assertNotEquals(250, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Позитивный тест")
    @DisplayName("Минимальная стоимость доставки")
    void minPriceTest() {
        Delivery delivery = new Delivery(1, CargoDimension.SMALL, false, ServiceWorkload.NORMAL);
        assertEquals(400, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Позитивный тест")
    @DisplayName("Минимальная стоимость доставки")
    void maxPriceTest() {
        Delivery delivery = new Delivery(30, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH);
        double actualPrice = delivery.calculateDeliveryCost();
        assertEquals(1120, actualPrice);
    }
    @ParameterizedTest
    @Tag("Позитивный тест")
    @DisplayName("Проверяет правильность расчетов стоимости доставки")
    @MethodSource("validDeliveryData")
    void priceDeliveryTest(int distance, CargoDimension cargo, boolean fragile, ServiceWorkload rate, double expectedCost) {
        Delivery delivery = new Delivery(distance, cargo, fragile, rate);

        double actualCost = delivery.calculateDeliveryCost();
        assertEquals(expectedCost,actualCost);
    }

    static Stream<Arguments> validDeliveryData() {

        return Stream.of(
                Arguments.of(0, CargoDimension.LARGE, true, ServiceWorkload.NORMAL, 550),
                Arguments.of(2, CargoDimension.SMALL, true, ServiceWorkload.INCREASED,540),
                Arguments.of(5, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH, 960),
                Arguments.of(10,CargoDimension.SMALL, true, ServiceWorkload.NORMAL,500),
                Arguments.of(20,CargoDimension.LARGE, false, ServiceWorkload.HIGH,560),
                Arguments.of(30, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH,1120),
                Arguments.of(31 ,CargoDimension.SMALL, false, ServiceWorkload.HIGH, 560),
                Arguments.of(50 ,CargoDimension.SMALL, false, ServiceWorkload.NORMAL, 400)
        );
    }

    @ParameterizedTest
    @Tag("Позитивный тест")
    @DisplayName("Граничные значения расстояний")
    @CsvSource({
            "2,SMALL,false,NORMAL,400.0",
            "9,LARGE,true,HIGH,840.0",
            "10,SMALL,false,NORMAL,400.0",
            "11,LARGE,true,HIGH,980.0",
            "29,SMALL,true,VERY_HIGH,960.0",
            "30,LARGE,false,INCREASED,480.0",
    })
    void distanceBoundaryValuesTest(int distance, CargoDimension cargoDimension, boolean isFragile, ServiceWorkload serviceWorkload, double expectedPrice) {
        Delivery delivery = new Delivery(distance, cargoDimension, isFragile, serviceWorkload);
        double actualPrice = delivery.calculateDeliveryCost();
        assertEquals(expectedPrice, actualPrice);
    }
}
