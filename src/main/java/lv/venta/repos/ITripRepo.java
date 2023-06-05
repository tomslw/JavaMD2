package lv.venta.repos;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Trip;

public interface ITripRepo extends CrudRepository<Trip, Long> {
	ArrayList<Trip> findByCitiesTitle(String title);
	ArrayList<Trip> findByDriverIdd(long id);
	ArrayList<Trip> findByStartDateTimeBetween(Date start, Date end);
}
