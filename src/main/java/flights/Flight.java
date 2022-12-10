/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import passengers.Passenger;

/**
 *  @brief The Flight class allows you to manage flight data and their respective assigned passengers.
 *  @since 09/12/22
 *  @version 1.0
 */
public class Flight {

    private String flightNumber;
    private int seats;
    private Set<Passenger> passengers = new HashSet<>();

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";
    private static Pattern pattern = Pattern.compile(flightNumberRegex);

    /**
     * Class constructor - If an incorrect flight number is passed the flight cannot be created.
     * @param flightNumber Flight number
     * @param seats Limit of flight sites
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * Method to return flight's identifier
     * @return Flight's identifier
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Method to return flight's number of passengers
     * @return Flight's number of passengers
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * Method to add a passenger to the flight. The number of seats cannot be exceeded
     * @param passenger Passenger to add
     * @return True if the operation is successful - False if it fails
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * Method to remove a passenger from the flight.
     * @param passenger Passenger to remove
     * @return True if the operation is successful - False if it fails
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
