package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Cashier;

public interface ICashierRepo extends CrudRepository<Cashier, Long> {

}
