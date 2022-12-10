package flights;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight("AB340", 50);
    }

    @Nested
    @DisplayName("Given a flight")
    class RegularFlight {

        @Test
        @DisplayName("Flight attributes are checked")
        public void flightAttributesTest() {
            assertAll("Checking that the attributes are properly initialized",
                    () -> assertEquals("AB340", flight.getFlightNumber()),
                    () -> assertEquals(0, flight.getNumberOfPassengers())
            );
        }
    }
}