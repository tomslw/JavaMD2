package lv.venta.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import jakarta.validation.Valid;
import lv.venta.models.Driver;
import lv.venta.models.Ticket;
import lv.venta.services.ICashierService;
import lv.venta.services.IDriverCRUDService;
import lv.venta.services.ITicketService;
import lv.venta.services.ITripService;

@Controller
public class MainController {
	private RequestMappingHandlerMapping handlerMapping;
	
	@Autowired
	public void EndpointDocController(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}

	@Autowired
	private IDriverCRUDService driverService;
	
	@Autowired
	private ITripService tripService;
	
	@Autowired
	private ICashierService cashierService;
	
	@Autowired
	private ITicketService ticketService;
	
	@GetMapping("/driver/showAll")
	public String getAllDrivers(Model model) {
		model.addAttribute("drivers", driverService.selectAllDriver());
		return "driver-all-page";
	}
	
	@GetMapping("/driver/showAll/{id}")
	public String getDriverById(@PathVariable(name="id") long id, Model model) {
		try {
			model.addAttribute("drivers", driverService.selectDriverById(id));
			return "driver-one-page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@GetMapping("/driver/remove/{id}")
	public String removeDriverById(@PathVariable(name="id") long id, Model model) {
		try {
			driverService.deleteDriverById(id);
			return "redirect:/driver/showAll";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@GetMapping("/driver/addNew")
	public String addDriver(Model model) {
		model.addAttribute("driver", new Driver());
		return "driver-add-page";
	}
	
	@PostMapping("/driver/addNew")
	public String addDriver(@Valid Driver driver, BindingResult result) {
		
		if (!result.hasErrors()) {
			try {
				driverService.insertNewDriver(driver.getName(), driver.getSurname(), driver.getCategories());
				return "redirect:/driver/showAll";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/err"; // doesn't exist yet
			}
		} else {
			return "driver-add-page";
		}
	}
	
	@GetMapping("/driver/update/{id}")
	public String updateDriver(@PathVariable(name="id") long id, Model model) {
		try {
			//model.addAttribute("driver", new Driver());
			model.addAttribute("driver", driverService.selectDriverById(id));
			return "driver-update-page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@PostMapping("/driver/update/{id}")
	public String updateDriver(@PathVariable(name="id") long id, @Valid Driver driver, BindingResult result) {
		try {
			driverService.updateDriverById(id, driver.getName(), driver.getSurname(), driver.getCategories());
			return "redirect:/driver/showAll";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/err";
		}
	}
	
	@GetMapping("/trip/showAll")
	public String getTrips(Model model) {
		model.addAttribute("trips", tripService.selectAlTrips());
		return "trip-all-page";
	}
	
	@GetMapping("/trip/showAll/city/{citytitle}")
	public String getTripByCity(@PathVariable(name="citytitle") String citytitle, Model model) {
		try {
			model.addAttribute("trips", tripService.selectAlTripsByCityTitle(citytitle));
			return "trip-all-page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@GetMapping("/trip/showAll/driver/{driverid}")
	public String getTripByDriver(@PathVariable(name="driverid") long driverid, Model model) {
		try {
			model.addAttribute("trips", tripService.selectAlTripsByDriverId(driverid));
			return "trip-all-page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@GetMapping("/trip/showAll/today")
	public String getTripByDriver(Model model) {
		try {
			model.addAttribute("trips", tripService.selectAlTripsToday());
			return "trip-all-page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}

	}
	
	@GetMapping("/trip/changeDriver/{tripid}/{driverid}")
	public String changeTripDriver(@PathVariable(name="tripid") long tripid, @PathVariable(name="driverid") long driverid, Model model) {
		try {
			tripService.changeTripDriverByDriverId(driverid, tripid);
			return "redirect:/trip/showAll";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@GetMapping("/ticket/showAll/")
	public String getTickets(Model model) {
		model.addAttribute("tickets", ticketService.selectAllTickets());
		return "ticket-all-page";
	}
	
	@GetMapping("/ticket/showAll/onlyChild")
	public String getTicketsChild(Model model) {
		model.addAttribute("tickets", ticketService.selectAllChildTickets());
		return "ticket-all-page";
	}
	
	@GetMapping("/ticket/showAll/less/{threshold}")
	public String getTicketsCheap(@PathVariable(name="threshold") double threshold, Model model) {
		model.addAttribute("tickets", ticketService.selectAllTicketsWherePriceIsLow(threshold));
		return "ticket-all-page";
	}
	
	@GetMapping("/ticket/showAll/trip/{tripid}")
	public String getTicketsByTrip(@PathVariable(name="tripid") long tripid, Model model) {
		try {
			model.addAttribute("tickets", ticketService.selectAllTicketsByTripId(tripid));
			return "ticket-all-page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@GetMapping("/ticket/calculate/trip/{tripid}")
	public String getTicketsTripIncome(@PathVariable(name="tripid") long tripid, Model model) {
		try {
			model.addAttribute("income", ticketService.calculateIncomeOfTripByTripId(tripid));
			model.addAttribute("titletext", "Brauciena Nr." + tripService.selectTripById(tripid).getIdtr() + " ienesīgums");
			return "ticket-income-page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@GetMapping("/ticket/calculate/cashier/{cashierid}")
	public String getTicketsCashierIncome(@PathVariable(name="cashierid") long cashierid, Model model) {
		try {
			model.addAttribute("income", ticketService.calculateIncomeOfCashierByCashierId(cashierid));
			model.addAttribute("titletext", cashierService.selectCashiersById(cashierid).getName() + " nopelnija");
			return "ticket-income-page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@GetMapping("/ticket/add/")
	public String addTicket(Model model) {
		try {
			model.addAttribute("ticket", new Ticket());
			model.addAttribute("trips", tripService.selectAlTrips());
			model.addAttribute("cashiers", cashierService.selectAllCashiers());
			return "ticket-add-page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.toString());
			return "error-page";
		}
	}
	
	@PostMapping("/ticket/add/")
	public String addTicket(@Valid Ticket ticket, BindingResult result) {
		try {
			ticketService.insertNewTicketByTripId(ticket.getTrip().getIdtr(), ticket.getCashier().getIdc(), ticket.getPrice(), ticket.isChild());
			return "redirect:/ticket/showAll/trip/" + ticket.getTrip().getIdtr();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/err";
		}
	}
	
	@GetMapping("/err")
	public String errorPage(Model model) {
		model.addAttribute("msg", "Something went wrong, oopsie!");
		return "error-page";
	}
	
	// for convenience
	@GetMapping("/")
	public String linksPage(Model model) {
		Set<RequestMappingInfo> yes = this.handlerMapping.getHandlerMethods().keySet();
		ArrayList<String> urls = new ArrayList();
		for (RequestMappingInfo item : yes) {
			urls.addAll(item.getPatternValues());
		}	
				
		model.addAttribute("handlers", urls);
		return "links-page";
	}
	
	
	
	
	
	
}
