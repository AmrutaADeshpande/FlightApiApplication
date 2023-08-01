package com.capgemini.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.Entity.FlightDetails;
import com.capgemini.Service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
	@Autowired
	private FlightService flightService;

	@GetMapping
	public List<FlightDetails> getAllFlights() {
		return flightService.getAllFlights();
	}

	@GetMapping("/sorted-by-price")
	public List<FlightDetails> getFlightsSortedByPrice() {
		return flightService.getFlightsSortedByPrice();
	}

	@GetMapping("/sorted-by-duration")
	public List<FlightDetails> getFlightsSortedByDuration() {
		return flightService.getFlightsSortedByDuration();
	}

	@GetMapping("/{id}")
	public ResponseEntity<FlightDetails> getFlightById(@PathVariable Long id) {
		FlightDetails flight = flightService.getFlightById(id);
		if (flight != null) {
			return ResponseEntity.ok(flight);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("find-list")
	public List<FlightDetails> getFlightsByOriginAndDestination(@RequestParam String origin,
			@RequestParam String destination) {
		return flightService.getFlightsByOriginAndDestination(origin, destination);
	}

	@PostMapping
	public ResponseEntity<FlightDetails> createFlight(@RequestBody FlightDetails flight) {
		FlightDetails savedFlight = flightService.createFlight(flight);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FlightDetails> updateFlight(@PathVariable Long id, @RequestBody FlightDetails flight) {
		FlightDetails updatedFlight = flightService.updateFlight(id, flight);
		if (updatedFlight != null) {
			return ResponseEntity.ok(updatedFlight);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
		flightService.deleteFlight(id);
		return ResponseEntity.noContent().build();
	}
}