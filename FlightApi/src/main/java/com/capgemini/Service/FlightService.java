package com.capgemini.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.Entity.FlightDetails;
import com.capgemini.Repository.FlightRepository;

@Service
public class FlightService {
	@Autowired
	private FlightRepository flightRepository;

	public List<FlightDetails> getAllFlights() {
		return flightRepository.findAll();
	}

	public List<FlightDetails> getFlightsSortedByPrice() {
		return flightRepository.findAll().stream().sorted(Comparator.comparingDouble(FlightDetails::getPrice))
				.collect(Collectors.toList());
	}

	public List<FlightDetails> getFlightsSortedByDuration() {
		return flightRepository.findAll().stream().sorted(Comparator.comparingLong(FlightDetails::getDurationInMinutes))
				.collect(Collectors.toList());
	}

	public FlightDetails getFlightById(Long id) {
		return flightRepository.findById(id).orElse(null);
	}

	public List<FlightDetails> getFlightsByOriginAndDestination(String origin, String destination) {
		return flightRepository.findAll().stream().filter(flight -> flight.getOrigin().equalsIgnoreCase(origin)
				&& flight.getDestination().equalsIgnoreCase(destination)).collect(Collectors.toList());
	}

	public FlightDetails createFlight(FlightDetails flightDetails) {
		return flightRepository.save(flightDetails);
	}

	public FlightDetails updateFlight(Long id, FlightDetails flightDetails) {
		if (flightRepository.existsById(id)) {
			flightDetails.setId(id);
			return flightRepository.save(flightDetails);
		}
		return null;
	}

	public void deleteFlight(Long id) {
		flightRepository.deleteById(id);
	}
}