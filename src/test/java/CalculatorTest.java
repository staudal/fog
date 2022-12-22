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
    void poleNumberTest() {
        // CARPORT LENGTH: 0 - 440 cm (4 poles)
        for (int i = 0; i <= 440; i++) {
            assertEquals(4, calculator.poleNumber(i));
        }

        // CARPORT LENGTH: 441 - 750 (6 poles)
        for (int i = 441; i <= 750; i++) {
            assertEquals(6, calculator.poleNumber(i));
        }

        // CARPORT LENGTH: 751 - 800 (8 poles)
        for (int i = 751; i <= 800; i++) {
            assertEquals(8, calculator.poleNumber(i));
        }
    }

    @Test
    void beamNumberTest() {
        // CARPORT LENGTH: 0 - 720 (2 beams)
        for (int i = 0; i <= 720; i++) {
            assertEquals(2, calculator.beamNumber(i));
        }

        // CARPORT LENGTH: 721 - 800 (4 beams)
        for (int i = 721; i <= 800; i++) {
            assertEquals(4, calculator.beamNumber(i));
        }
    }

    @Test
    void rafterNumberTest() {
        // CARPORT LENGTH: 240 cm (5 rafters, 54.13 cm spacing)
        assertEquals(5, calculator.rafterNumber(240));
        // CARPORT LENGTH: 270 cm (5 rafters, 61.63 cm spacing)
        assertEquals(5, calculator.rafterNumber(270));
        // CARPORT LENGTH: 300 cm (6 rafters, 54.36 cm spacing)
        assertEquals(6, calculator.rafterNumber(300));
        // CARPORT LENGTH: 330 cm (7 rafters, 49.52 cm spacing)
        assertEquals(7, calculator.rafterNumber(330));
        // CARPORT LENGTH: 360 cm (7 rafters, 54.52 cm spacing)
        assertEquals(7, calculator.rafterNumber(360));
        // CARPORT LENGTH: 390 cm (8 rafters, 50.34 cm spacing)
        assertEquals(8, calculator.rafterNumber(390));
        // CARPORT LENGTH: 420 cm (8 rafters, 54.63 cm spacing)
        assertEquals(8, calculator.rafterNumber(420));
        // CARPORT LENGTH: 450 cm (9 rafters, 50.96 cm spacing)
        assertEquals(9, calculator.rafterNumber(450));
        // CARPORT LENGTH: 480 cm (9 rafters, 54.71 cm spacing)
        assertEquals(9, calculator.rafterNumber(480));
        // CARPORT LENGTH: 510 cm (10 rafters, 51.44 cm spacing)
        assertEquals(10, calculator.rafterNumber(510));
        // CARPORT LENGTH: 540 cm (10 rafters, 54.78 cm spacing)
        assertEquals(10, calculator.rafterNumber(540));
        // CARPORT LENGTH: 570 cm (11 rafters, 51.83 cm spacing)
        assertEquals(11, calculator.rafterNumber(570));
        // CARPORT LENGTH: 600 cm (11 rafters, 54.83 cm spacing)
        assertEquals(11, calculator.rafterNumber(600));
        // CARPORT LENGTH: 630 cm (12 rafters, 52.15 cm spacing)
        assertEquals(12, calculator.rafterNumber(630));
        // CARPORT LENGTH: 660 cm (13 rafters, 49.91 cm spacing)
        assertEquals(13, calculator.rafterNumber(660));
        // CARPORT LENGTH: 690 cm (13 rafters, 52.41 cm spacing)
        assertEquals(13, calculator.rafterNumber(690));
        // CARPORT LENGTH: 720 cm (14 rafters, 50.32 cm spacing)
        assertEquals(14, calculator.rafterNumber(720));
        // CARPORT LENGTH: 750 cm (14 rafters, 52.63 cm spacing)
        assertEquals(14, calculator.rafterNumber(750));
        // CARPORT LENGTH: 780 cm (15 rafters, 50.68 cm spacing)
        assertEquals(15, calculator.rafterNumber(780));
    }

    @Test
    void lowerSideFasciaNumberTest() {
        // CARPORT LENGTH: 0 - 600 (2 boards)
        for (int i = 0; i <= 600; i++) {
            assertEquals(2, calculator.lowerSideFasciaNumber(i));
        }

        // CARPORT LENGTH: 601 - 800 (4 boards)
        for (int i = 601; i <= 800; i++) {
            assertEquals(4, calculator.lowerSideFasciaNumber(i));
        }
    }

    @Test
    void upperSideFasciaNumberTest() {
        // CARPORT LENGTH: 0 - 600 (2 boards)
        for (int i = 0; i <= 600; i++) {
            assertEquals(2, calculator.upperSideFasciaNumber(i));
        }

        // CARPORT LENGTH: 601 - 800 (4 boards)
        for (int i = 601; i <= 800; i++) {
            assertEquals(4, calculator.upperSideFasciaNumber(i));
        }
    }

    @Test
    void roofNumberTest() {
        // longest roof is 600 cm so the number of roofs will just be multiplied by 2 if the length of the carport is longer than 600 cm
        for (int i = 0; i <= 600; i++) {
            // CARPORT LENGTH: 240 cm (3 roofs)
            assertEquals(3, calculator.roofNumber(i, 240));
            // CARPORT LENGTH: 270 cm (3 roofs)
            assertEquals(3, calculator.roofNumber(i, 270));
            // CARPORT LENGTH: 300 cm (4 roofs)
            assertEquals(4, calculator.roofNumber(i, 300));
            // CARPORT LENGTH: 330 cm (4 roofs)
            assertEquals(4, calculator.roofNumber(i, 330));
            // CARPORT LENGTH: 360 cm (4 roofs)
            assertEquals(4, calculator.roofNumber(i, 360));
            // CARPORT LENGTH: 390 cm (4 roofs)
            assertEquals(4, calculator.roofNumber(i, 390));
            // CARPORT LENGTH: 420 cm (5 roofs)
            assertEquals(5, calculator.roofNumber(i, 420));
            // CARPORT LENGTH: 450 cm (5 roofs)
            assertEquals(5, calculator.roofNumber(i, 450));
            // CARPORT LENGTH: 480 cm (5 roofs)
            assertEquals(5, calculator.roofNumber(i, 480));
            // CARPORT LENGTH: 510 cm (6 roofs)
            assertEquals(6, calculator.roofNumber(i, 510));
            // CARPORT LENGTH: 540 cm (6 roofs)
            assertEquals(6, calculator.roofNumber(i, 540));
            // CARPORT LENGTH: 570 cm (6 roofs)
            assertEquals(6, calculator.roofNumber(i, 570));
            // CARPORT LENGTH: 600 cm (7 roofs)
            assertEquals(7, calculator.roofNumber(i, 600));
        }
        for (int i = 601; i <= 800; i++) {
            // CARPORT LENGTH: 240 cm (6 roofs)
            assertEquals(3 * 2, calculator.roofNumber(i, 240));
            // CARPORT LENGTH: 270 cm (6 roofs)
            assertEquals(3 * 2, calculator.roofNumber(i, 270));
            // CARPORT LENGTH: 300 cm (8 roofs)
            assertEquals(4 * 2, calculator.roofNumber(i, 300));
            // CARPORT LENGTH: 330 cm (8 roofs)
            assertEquals(4 * 2, calculator.roofNumber(i, 330));
            // CARPORT LENGTH: 360 cm (8 roofs)
            assertEquals(4 * 2, calculator.roofNumber(i, 360));
            // CARPORT LENGTH: 390 cm (8 roofs)
            assertEquals(4 * 2, calculator.roofNumber(i, 390));
            // CARPORT LENGTH: 420 cm (10 roofs)
            assertEquals(5 * 2, calculator.roofNumber(i, 420));
            // CARPORT LENGTH: 450 cm (10 roofs)
            assertEquals(5 * 2, calculator.roofNumber(i, 450));
            // CARPORT LENGTH: 480 cm (10 roofs)
            assertEquals(5 * 2, calculator.roofNumber(i, 480));
            // CARPORT LENGTH: 510 cm (12 roofs)
            assertEquals(6 * 2, calculator.roofNumber(i, 510));
            // CARPORT LENGTH: 540 cm (12 roofs)
            assertEquals(6 * 2, calculator.roofNumber(i, 540));
            // CARPORT LENGTH: 570 cm (12 roofs)
            assertEquals(6 * 2, calculator.roofNumber(i, 570));
            // CARPORT LENGTH: 600 cm (14 roofs)
            assertEquals(7 * 2, calculator.roofNumber(i, 600));
        }
    }

}
