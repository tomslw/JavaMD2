package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.Cashier;

public interface ICashierService {
	ArrayList<Cashier> selectAllCashiers();
	Cashier selectCashiersById(long id) throws Exception;
}
