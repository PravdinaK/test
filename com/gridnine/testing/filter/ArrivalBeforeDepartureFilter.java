package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.List;

public class ArrivalBeforeDepartureFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(f -> f.getSegments().stream()
                        .allMatch(s -> !s.getArrivalDate().isBefore(s.getDepartureDate())))
                .toList();
    }
}