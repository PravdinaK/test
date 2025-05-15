package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {

    private final int maxGroundTimeMinutes;

    public GroundTimeExceedsTwoHoursFilter(int maxGroundTimeMinutes) {
        this.maxGroundTimeMinutes = maxGroundTimeMinutes;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    long totalGroundTime = 0;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        var arrival = segments.get(i).getArrivalDate();
                        var nextDeparture = segments.get(i + 1).getDepartureDate();
                        totalGroundTime += Duration.between(arrival, nextDeparture).toMinutes();
                    }
                    return totalGroundTime <= maxGroundTimeMinutes;
                })
                .collect(Collectors.toList());
    }
}