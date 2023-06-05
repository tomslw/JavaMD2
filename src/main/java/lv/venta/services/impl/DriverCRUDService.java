package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Buscategory;
import lv.venta.models.Driver;
import lv.venta.models.Trip;
import lv.venta.repos.IDriverRepo;
import lv.venta.repos.ITripRepo;
import lv.venta.services.IDriverCRUDService;

@Service
public class DriverCRUDService implements IDriverCRUDService {
	
	@Autowired
	private IDriverRepo driverRepo;
	
	@Autowired
	private ITripRepo tripRepo;
	

	@Override
	public ArrayList<Driver> selectAllDriver() {
		return (ArrayList<Driver>) driverRepo.findAll();
	}

	@Override
	public Driver selectDriverById(long id) throws Exception {
		if (id < 1)
			throw new Exception("Invalid Driver id!");
		
		return driverRepo.findById(id).get();
	}

	@Override
	public void deleteDriverById(long id) throws Exception {
		if (id < 2) // can't delete the first one
			throw new Exception("Invalid Driver id!");
		
		Driver byebye = driverRepo.findById(id).get();
		
		// setting all the trips that this driver took to the default first one
		// this isnt for production so i don't care how janky it is
		for(Trip trip : byebye.getTrips()) {
			trip.setDriver(driverRepo.findById((long) 1).get());
			tripRepo.save(trip);
		}
		
		driverRepo.deleteById(id);
	}

	@Override
	public void insertNewDriver(String name, String surname, Collection<Buscategory> categories) throws Exception {
		Driver newDriver = new Driver(name, surname, categories);
		driverRepo.save(newDriver);
	}

	@Override
	public void insertNewDriver(String name, String surname, Buscategory categories) throws Exception {
		Driver newDriver = new Driver(name, surname, categories);
		driverRepo.save(newDriver);
	}

	@Override
	public void updateDriverById(long id, String name, String surname, Collection<Buscategory> categories) throws Exception {
		if (id < 2) // can't update the retired default
			throw new Exception("Invalid Driver id!");
		
		Driver targetDriver = driverRepo.findById(id).get();

		targetDriver.setName(name);
		targetDriver.setSurname(surname);
		targetDriver.setCategories(categories);

		driverRepo.save(targetDriver);
	}

}
