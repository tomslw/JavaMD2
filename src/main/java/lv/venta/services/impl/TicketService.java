package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.models.Ticket;
import lv.venta.repos.ICashierRepo;
import lv.venta.repos.ICityRepo;
import lv.venta.repos.IDriverRepo;
import lv.venta.repos.ITicketRepo;
import lv.venta.repos.ITripRepo;
import lv.venta.services.ITicketService;

@Service
public class TicketService implements ITicketService {
	
	@Autowired
	private IDriverRepo driverRepo;
	
	@Autowired
	private ICashierRepo cashierRepo;
	
	@Autowired
	private ICityRepo cityRepo;
	
	@Autowired
	private ITicketRepo ticketRepo;
	
	@Autowired
	private ITripRepo tripRepo;

	@Override
	public ArrayList<Ticket> selectAllChildTickets() {
		return (ArrayList<Ticket>) ticketRepo.findAllByIsChild(true);
	}

	@Override
	public ArrayList<Ticket> selectAllTicketsWherePriceIsLow(double maxPrice) {
		return (ArrayList<Ticket>)((List<Ticket>) ticketRepo.findAll()).stream()
				.filter(f -> f.getPrice() <= maxPrice)
				.collect(Collectors.toList()); // :D
		// very java
	}

	@Override
	public ArrayList<Ticket> selectAllTicketsByTripId(long id) throws Exception {
		if (id < 1)
			throw new Exception("Invalid Trip id!");
		
		return (ArrayList<Ticket>) ticketRepo.findByTripIdtr(id);
	}

	@Override
	public double calculateIncomeOfTripByTripId(long id) throws Exception {
		if (id < 1)
			throw new Exception("Invalid Trip id!");
		
		return ((List<Ticket>) ticketRepo.findByTripIdtr(id)).stream()
				.collect(Collectors.summingDouble(item -> item.getPrice())); // :D
		// Lovely
	}

	@Override
	public double calculateIncomeOfCashierByCashierId(long id) throws Exception {
		if (id < 1)
			throw new Exception("Invalid Cashier id!");
		
		return ((List<Ticket>) ticketRepo.findByCashierIdc(id)).stream()
				.collect(Collectors.summingDouble(item -> item.getPrice()));
	}

	@Override
	public void insertNewTicketByTripId(long tripId, long cashierId, double price, boolean isChild) throws Exception {
		if (tripId < 1 || cashierId < 1)
			throw new Exception("Invalid Trip id!");
		Ticket newTicket = new Ticket(tripRepo.findById(tripId).get(), cashierRepo.findById(cashierId).get(), price, isChild);
		ticketRepo.save(newTicket);
	}

	@Override
	public ArrayList<Ticket> selectAllTickets() {
		return (ArrayList<Ticket>) ticketRepo.findAll();
	}

}
