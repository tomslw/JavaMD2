package lv.venta;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.models.Buscategory;
import lv.venta.models.Cashier;
import lv.venta.models.City;
import lv.venta.models.Driver;
import lv.venta.models.Ticket;
import lv.venta.models.Trip;
import lv.venta.repos.ICashierRepo;
import lv.venta.repos.ICityRepo;
import lv.venta.repos.IDriverRepo;
import lv.venta.repos.ITicketRepo;
import lv.venta.repos.ITripRepo;

@SpringBootApplication
public class JavaMD2Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(JavaMD2Application.class, args);
	}
	
	@Bean
	public CommandLineRunner testModel(ICashierRepo cashRepo, ICityRepo cityRepo, IDriverRepo driverRepo, 
			ITicketRepo ticketRepo, ITripRepo tripRepo) {
		
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
			
				
				Cashier cash1 = new Cashier("Zentina", "Pardevejkundze");
				Cashier cash2 = new Cashier("Krustnaglina", "Pardevejdama");
				
				cashRepo.save(cash1);
				cashRepo.save(cash2);
				
				City city1 = new City("Daugavpils", "Jana iela 16");
				City city2 = new City("Jekabpils", "Kalna iela 8");
				City city3 = new City("Saldus", "Auzu iela 99");
				
				cityRepo.save(city1);
				cityRepo.save(city2);
				cityRepo.save(city3);
				
				Driver driver0 = new Driver("Retired", "Driver", Buscategory.largebus); // workaround to deleting drivers, woopee
				Driver driver1 = new Driver("Ainars", "Ups", Buscategory.largebus);
				Driver driver2 = new Driver("Kristaps", "Krumins", Buscategory.minibus);
				
				driverRepo.save(driver0);
				driverRepo.save(driver1);
				driverRepo.save(driver2);
				
				Trip trip1 = new Trip(Arrays.asList(city1, city2, city3), driver1, new Date(), 5.5);
				Trip trip2 = new Trip(Arrays.asList(city3, city2, city1), driver2, new Date(), 5.5);
				Trip trip3 = new Trip(Arrays.asList(city3, city1), driver2, new Date(), 5.5);
				Trip trip4 = new Trip(Arrays.asList(city3, city1), driver2, new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01"), 5.5);
				
				tripRepo.save(trip1);
				tripRepo.save(trip2);
				tripRepo.save(trip3);
				tripRepo.save(trip4);
				
				Ticket ticket1 = new Ticket(trip1, cash1, 10.50, false);
				Ticket ticket2 = new Ticket(trip1, cash2, 10.50, false);
				Ticket ticket3 = new Ticket(trip1, cash1, 7.50, true);
				
				Ticket ticket4 = new Ticket(trip2, cash2, 11.50, false);
				Ticket ticket5 = new Ticket(trip2, cash1, 11.50, false);
				Ticket ticket6 = new Ticket(trip2, cash2, 8.50, true);
				
				ticketRepo.save(ticket1);
				ticketRepo.save(ticket2);
				ticketRepo.save(ticket3);
				ticketRepo.save(ticket4);
				ticketRepo.save(ticket5);
				ticketRepo.save(ticket6);
				
			}
		};
		
		
	}

}
