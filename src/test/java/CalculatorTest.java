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
        // 0 - 310 (4 beams)
        assertEquals(4, calculator.calculateNumberOfBeams(127));
        assertEquals(4, calculator.calculateNumberOfBeams(200));
        assertEquals(4, calculator.calculateNumberOfBeams(310));

        // 311 - 619 (6 beams)
        assertEquals(6, calculator.calculateNumberOfBeams(311));
        assertEquals(6, calculator.calculateNumberOfBeams(511));
        assertEquals(6, calculator.calculateNumberOfBeams(619));

        // 620 - 929 (8 beams)
        assertEquals(8, calculator.calculateNumberOfBeams(620));
        assertEquals(8, calculator.calculateNumberOfBeams(767));
        assertEquals(8, calculator.calculateNumberOfBeams(929));

        // 930 - 1239 (10 beams)
        assertEquals(10, calculator.calculateNumberOfBeams(930));
        assertEquals(10, calculator.calculateNumberOfBeams(1000));
        assertEquals(10, calculator.calculateNumberOfBeams(1239));

        // 1240 - 1549 (12 beams)
        assertEquals(12, calculator.calculateNumberOfBeams(1240));
        assertEquals(12, calculator.calculateNumberOfBeams(1430));
        assertEquals(12, calculator.calculateNumberOfBeams(1549));
    }
}
