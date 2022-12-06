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
        assertEquals(4, calculator.calculateNumberOfPoles(0, 0));
        assertEquals(4, calculator.calculateNumberOfPoles(200, 250));
        assertEquals(4, calculator.calculateNumberOfPoles(310, 500));

        // LENGTH: 311 - 619  WIDTH: 0 - 500
        assertEquals(6, calculator.calculateNumberOfPoles(311, 0));
        assertEquals(6, calculator.calculateNumberOfPoles(500, 250));
        assertEquals(6, calculator.calculateNumberOfPoles(619, 500));

        // LENGTH: 620 - 800  WIDTH: 0 - 500
        assertEquals(8, calculator.calculateNumberOfPoles(620, 0));
        assertEquals(8, calculator.calculateNumberOfPoles(700, 250));
        assertEquals(8, calculator.calculateNumberOfPoles(800, 500));

        // LENGTH: 0 - 310  WIDTH: 501 - 600
        assertEquals(6, calculator.calculateNumberOfPoles(0, 501));
        assertEquals(6, calculator.calculateNumberOfPoles(200, 550));
        assertEquals(6, calculator.calculateNumberOfPoles(310, 600));

        // LENGTH: 311 - 619  WIDTH: 501 - 600
        assertEquals(9, calculator.calculateNumberOfPoles(311, 501));
        assertEquals(9, calculator.calculateNumberOfPoles(500, 550));
        assertEquals(9, calculator.calculateNumberOfPoles(619, 600));

        // LENGTH: 620 - 800  WIDTH: 501 - 600
        assertEquals(12, calculator.calculateNumberOfPoles(620, 501));
        assertEquals(12, calculator.calculateNumberOfPoles(700, 550));
        assertEquals(12, calculator.calculateNumberOfPoles(800, 600));
    }

    @Test
    void testCalculateSupportBeams() {
        // LENGTH: 0 - 720  WIDTH: 0 - 500
        assertEquals(2, calculator.calculateNumberOfSupportBeam(200, 200));
        assertEquals(2, calculator.calculateNumberOfSupportBeam(400, 350));
        assertEquals(2, calculator.calculateNumberOfSupportBeam(720, 500));

        // LENGTH: 0 - 720  WIDTH: 501 - 600
        assertEquals(3, calculator.calculateNumberOfSupportBeam(200, 501));
        assertEquals(3, calculator.calculateNumberOfSupportBeam(400, 550));
        assertEquals(3, calculator.calculateNumberOfSupportBeam(720, 600));

        // LENGTH: 721 - 800  WIDTH: 0 - 500
        assertEquals(4, calculator.calculateNumberOfSupportBeam(721, 200));
        assertEquals(4, calculator.calculateNumberOfSupportBeam(750, 350));
        assertEquals(4, calculator.calculateNumberOfSupportBeam(800, 500));

        // LENGTH: 721 - 800  WIDTH: 501 - 600
        assertEquals(6, calculator.calculateNumberOfSupportBeam(721, 501));
        assertEquals(6, calculator.calculateNumberOfSupportBeam(750, 550));
        assertEquals(6, calculator.calculateNumberOfSupportBeam(800, 600));
    }
}
