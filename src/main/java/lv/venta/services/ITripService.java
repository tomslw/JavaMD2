package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.Trip;

public interface ITripService {
	Trip selectTripById(long id) throws Exception;
	ArrayList<Trip> selectAlTripsByCityTitle(String title);
	ArrayList<Trip> selectAlTripsByDriverId(Long id) throws Exception;
	ArrayList<Trip> selectAlTripsToday();
	ArrayList<Trip> selectAlTrips();
	void changeTripDriverByDriverId(Long driverId, Long tripId) throws Exception;
}
