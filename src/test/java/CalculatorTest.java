import static org.junit.jupiter.api.Assertions.assertEquals;
import com.backend.fog.logics.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testCalculateBeams() {
        // LENGTH: 0 - 310  WIDTH: 0 - 500
        assertEquals(4, calculator.calculateNumberOfBeams(127, 127));
        assertEquals(4, calculator.calculateNumberOfBeams(200, 250));
        assertEquals(4, calculator.calculateNumberOfBeams(310, 500));

        // LENGTH: 311 - 619    WIDTH: 0 - 500
        assertEquals(6, calculator.calculateNumberOfBeams(311, 200));
        assertEquals(6, calculator.calculateNumberOfBeams(500, 400));
        assertEquals(6, calculator.calculateNumberOfBeams(619, 500));

        // LENGTH: 620 - 929  WIDTH: 0 - 500
        assertEquals(8, calculator.calculateNumberOfBeams(620, 150));
        assertEquals(8, calculator.calculateNumberOfBeams(800, 200));
        assertEquals(8, calculator.calculateNumberOfBeams(929, 500));

        // LENGTH: 0 - 310  WIDTH: 501 - 999
        assertEquals(6, calculator.calculateNumberOfBeams(150, 501));
        assertEquals(6, calculator.calculateNumberOfBeams(200, 750));
        assertEquals(6, calculator.calculateNumberOfBeams(310, 999));

        // LENGTH: 0 - 310  WIDTH: 1000 - 1499
        assertEquals(8, calculator.calculateNumberOfBeams(150, 1000));
        assertEquals(8, calculator.calculateNumberOfBeams(200, 1200));
        assertEquals(8, calculator.calculateNumberOfBeams(310, 1499));

        // LENGTH: 311 - 619    WIDTH: 501 - 999
        assertEquals(8, calculator.calculateNumberOfBeams(311, 501));
        assertEquals(8, calculator.calculateNumberOfBeams(500, 750));
        assertEquals(8, calculator.calculateNumberOfBeams(619, 999));

        // LENGTH: 620 - 929    WIDTH: 501 - 999
        assertEquals(10, calculator.calculateNumberOfBeams(620, 501));
        assertEquals(10, calculator.calculateNumberOfBeams(750, 750));
        assertEquals(10, calculator.calculateNumberOfBeams(929, 999));
    }
}
