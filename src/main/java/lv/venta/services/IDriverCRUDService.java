package lv.venta.services;

import java.util.ArrayList;
import java.util.Collection;

import lv.venta.models.Buscategory;
import lv.venta.models.Driver;

public interface IDriverCRUDService {
	ArrayList<Driver> selectAllDriver();
	Driver selectDriverById(long id) throws Exception;
	void deleteDriverById(long id) throws Exception;
	void insertNewDriver(String name, String surname, Collection<Buscategory> categories) throws Exception;
	void insertNewDriver(String name, String surname, Buscategory categories) throws Exception;
	void updateDriverById(long id, String name, String surname, Collection<Buscategory> categories) throws Exception;
}
