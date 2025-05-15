package com.gridnine.testing.filter;

public class FilterFactory {
    public static FlightFilter departureAfterNow() {
        return new DepartureBeforeNowFilter();
    }

    public static FlightFilter arrivalAfterDeparture() {
        return new ArrivalBeforeDepartureFilter();
    }

    public static FlightFilter groundTimeNotExceeding(int minutes) {
        return new GroundTimeExceedsTwoHoursFilter(minutes);
    }
}