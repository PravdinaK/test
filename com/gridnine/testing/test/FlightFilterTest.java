package com.gridnine.testing.test;

import com.gridnine.testing.filter.FilterFactory;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.util.FlightBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterTest {

    @Test
    @DisplayName("Тест: Вылет до текущего момента")
    void testDepartureAfterNow() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter filter = FilterFactory.departureAfterNow();

        List<Flight> result = filter.filter(flights);

         assertTrue(result.stream().allMatch(f -> f.getSegments().stream()
                .allMatch(s -> s.getDepartureDate().isAfter(LocalDateTime.now()))));
    }

    @Test
    @DisplayName("Тест: Прилёт раньше вылета")
    void testArrivalBeforeDeparture() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter filter = FilterFactory.arrivalAfterDeparture();

        List<Flight> result = filter.filter(flights);

        assertTrue(result.stream().allMatch(f -> f.getSegments().stream()
                .allMatch(s -> !s.getArrivalDate().isBefore(s.getDepartureDate()))));
    }

    @Test
    @DisplayName("Тест: Время на земле больше 2 часов")
    void testGroundTimeNotExceeding() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter filter = FilterFactory.groundTimeNotExceeding(120);

        List<Flight> result = filter.filter(flights);

        assertTrue(result.stream().allMatch(flight -> {
            long totalGroundTime = 0;
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                var arrival = segments.get(i).getArrivalDate();
                var nextDeparture = segments.get(i + 1).getDepartureDate();
                totalGroundTime += java.time.Duration.between(arrival, nextDeparture).toMinutes();
            }
            return totalGroundTime <= 120;
        }));
    }
}