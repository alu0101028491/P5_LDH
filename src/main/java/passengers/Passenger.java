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
package passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import flights.Flight;

/**
 *  The Passenger class allows you to manage passenger data and their respective assigned flights.
 *  @since 09/12/22
 *  @version 1.0
 */
public class Passenger {

    private String identifier;
    private String name;
    private String countryCode;
    private Flight flight;

    /**
     * Class constructor - If an incorrect country code is passed the passenger cannot be created.
     * @param identifier Passenger identifier
     * @param name Passenger name
     * @param countryCode Passenger country code
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * Method to return passenger's identifier
     * @return passenger's identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Method to return passenger's name
     * @return passenger's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to return passenger's country code
     * @return passenger's country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Method to return passenger's flight
     * @return passenger's flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Method to change the assigned flight of a passenger.
     * Returns an error if the passenger is not on any flight
     * as well as if no valid flight is used for the assignment.
     * @param flight Flight to which the passenger will be assigned
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * Method that sets the passenger's current flight by changing its previous value.
     * @param flight New passenger's flight
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Returns the passenger's information in String format
     * @return passenger's information in String format
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
