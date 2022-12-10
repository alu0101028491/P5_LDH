package flightspassengers;

import flights.Flight;
import passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import static org.junit.jupiter.api.Assertions.*;

public class FlightsPassengersTest {

    private Flight flight;
    private Passenger firstPassenger;
    private Passenger secondPassenger;

    @BeforeEach
    void setUp() {
        flight = new Flight("AB340", 2);
        firstPassenger = new Passenger("01","Samir","ES");
        secondPassenger = new Passenger("02","Patrick","US");
    }

    @Nested
    class FlightPassengertMethods {

        @Test
        @DisplayName("We can add passengers to a flight")
        public void addPassengerTest() {
            assertAll("Checking that we can add passengers to a flight",
                    () -> assertEquals(0, flight.getNumberOfPassengers()),
                    () -> assertTrue(flight.addPassenger(firstPassenger)),
                    () -> assertEquals(1, flight.getNumberOfPassengers()),
                    () -> assertTrue(flight.addPassenger(secondPassenger)),
                    () -> assertEquals(2, flight.getNumberOfPassengers())
            );
        }

        @Test
        @DisplayName("We can remove passengers from a flight")
        public void removePassengerTest() {
            assertAll("Checking that we can remove passengers from a flight",
                    () -> assertTrue(flight.addPassenger(firstPassenger)),
                    () -> assertTrue(flight.addPassenger(secondPassenger)),
                    () -> assertEquals(2, flight.getNumberOfPassengers()),
                    () -> assertTrue(flight.removePassenger(firstPassenger)),
                    () -> assertEquals(1, flight.getNumberOfPassengers()),
                    () -> assertTrue(flight.removePassenger(secondPassenger)),
                    () -> assertEquals(0, flight.getNumberOfPassengers())
            );
        }

        @RepeatedTest(3)
        @DisplayName("We can only add each passenger once within a flight.")
        public void addSamePassengerTest(RepetitionInfo repetitionInfo) {
            for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++)
                flight.addPassenger(firstPassenger);

            assertAll("Checking that you cannot add more than once to the same passenger",
                    () -> assertEquals(1, flight.getNumberOfPassengers())
            );
        }

        @Test
        @DisplayName("We cannot exceed the number of seats")
        public void exceedSeatsTest() {

            String expectedMessage = "Not enough seats for flight AB340";
            flight.addPassenger(firstPassenger);
            flight.addPassenger(secondPassenger);
            Exception exception = assertThrows(Exception.class, () ->
                    flight.addPassenger( new Passenger("03","Ismael","ES")));

            assertAll("Checking that we cannot exceed the number of seats",
                    () -> assertEquals(expectedMessage, exception.getMessage())
            );
        }
    }

    @Nested
    class PassengerFlightMethods {

        @Test
        @DisplayName("We can see the flight to which a passenger is assigned.")
        public void passengerFlightTest() {
            assertAll("Checking that we can see the flight to which a passenger is assigned.",
                    () -> assertTrue(flight.addPassenger(firstPassenger)),
                    () -> assertEquals(flight, firstPassenger.getFlight())
            );
        }

        @Test
        @DisplayName("We can change the passenger's flight")
        public void testPassengerChangeFlight() {
            Flight auxFlight = new Flight("JF445", 20);
            firstPassenger.joinFlight(flight);

            assertAll("Checking that we can change the passenger's flight",
                    () -> assertEquals("AB340",firstPassenger.getFlight().getFlightNumber()),
                    () -> assertEquals(1, flight.getNumberOfPassengers())
            );
            firstPassenger.joinFlight(auxFlight);
            assertAll("Checking that we can change the passenger's flight",
                    () -> assertEquals("JF445",firstPassenger.getFlight().getFlightNumber()),
                    () -> assertEquals(0, flight.getNumberOfPassengers())
            );
        }
    }
}
