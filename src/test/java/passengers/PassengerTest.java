package passengers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests to check the methods and attributes of the Passenger class
 *  @since 16/12/22
 *  @version 1.0
 */
class PassengerTest {

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger("01","Samir","ES");
    }

    @Nested
    @DisplayName("Given a passenger")
    class RegularPassenger {

        @Test
        @DisplayName("Passenger attributes are checked")
        void passengerAttributesTest() {
            assertAll("Checking that the attributes are properly initialized",
                    () -> assertEquals("01", passenger.getIdentifier()),
                    () -> assertEquals("Samir", passenger.getName()),
                    () -> assertEquals("ES", passenger.getCountryCode()),
                    () -> assertEquals("Passenger Samir with identifier: 01 from ES", passenger.toString())
            );
        }
    }
}