package lv.venta.services.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Trip;
import lv.venta.repos.IDriverRepo;
import lv.venta.repos.ITripRepo;
import lv.venta.services.ITripService;

@Service
public class TripService implements ITripService {
	
	@Autowired
	private IDriverRepo driverRepo;
	
	@Autowired
	private ITripRepo tripRepo;

	@Override
	public ArrayList<Trip> selectAlTripsByCityTitle(String title) {
		return tripRepo.findByCitiesTitle(title);
	}

	@Override
	public ArrayList<Trip> selectAlTripsByDriverId(Long id) throws Exception {
		if (id < 1)
			throw new Exception("Invalid Cashier id!");
		
		return tripRepo.findByDriverIdd(id);
	}

	@Override
	public ArrayList<Trip> selectAlTripsToday() {
		try {
														// this is javascript levels of dumb
			return tripRepo.findByStartDateTimeBetween(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()),
													   new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(LocalDate.now().toString() + " 23:59"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList();
		}
	}

	@Override
	public void changeTripDriverByDriverId(Long driverId, Long tripId) throws Exception {
		if (driverId < 1 || tripId < 1)
			throw new Exception("Invalid Cashier id!");
		
		Trip targetTrip = tripRepo.findById(tripId).get();
		targetTrip.setDriver(driverRepo.findById(driverId).get());
		
		tripRepo.save(targetTrip);
	}

	@Override
	public ArrayList<Trip> selectAlTrips() {
		return (ArrayList<Trip>) tripRepo.findAll();
	}

	@Override
	public Trip selectTripById(long id) throws Exception {
		return tripRepo.findById(id).get();
	}

}
