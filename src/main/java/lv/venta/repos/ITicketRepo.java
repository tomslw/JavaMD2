package lv.venta.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Ticket;

public interface ITicketRepo extends CrudRepository<Ticket, Long> {
	
	ArrayList<Ticket> findByTripIdtr(long id);
	ArrayList<Ticket> findByCashierIdc(long id);
	ArrayList<Ticket> findAllByIsChild(boolean isChild);
	
}
