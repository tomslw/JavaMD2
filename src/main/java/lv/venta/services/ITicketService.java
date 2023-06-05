package lv.venta.services;

import java.util.ArrayList;

import lv.venta.models.Ticket;

public interface ITicketService {
	ArrayList<Ticket> selectAllTickets();
	ArrayList<Ticket> selectAllChildTickets();
	ArrayList<Ticket> selectAllTicketsWherePriceIsLow(double maxPrice);
	ArrayList<Ticket> selectAllTicketsByTripId(long id) throws Exception;
	double calculateIncomeOfTripByTripId(long id) throws Exception;
	double calculateIncomeOfCashierByCashierId(long id) throws Exception;
	void insertNewTicketByTripId(long tripId, long cashierId, double price, boolean isChild) throws Exception;
}
