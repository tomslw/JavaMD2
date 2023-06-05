package lv.venta.models;

import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "ticket_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ticket {
	@Column(name = "Idt")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idt;
	
	@ManyToOne
	@JoinColumn(name = "Idtr")
	private Trip trip;
	
	@ManyToOne
	@JoinColumn(name = "Idc")
	private Cashier cashier;
	
	@Column(name = "Price")
	@NotNull
	@Min(0)
	private double price;
	
	@Column(name = "IsChild")
	@NotNull
	private boolean isChild;
	
	@Column(name = "PurchaseDateTime")
	@NotNull
	private LocalDateTime purchaseDateTime;

	// mostly for test data, although there must be some convenient way to spoof local time just like in django
	public Ticket(Trip trip, Cashier cashier, @NotNull @Min(0) double price, @NotNull boolean isChild,
			@NotNull LocalDateTime purchaseDateTime) {
		super();
		this.trip = trip;
		this.cashier = cashier;
		this.price = price;
		this.isChild = isChild;
		this.purchaseDateTime = purchaseDateTime;
	}
	
	public Ticket(Trip trip, Cashier cashier, @NotNull @Min(0) double price, @NotNull boolean isChild) {
		super();
		this.trip = trip;
		this.cashier = cashier;
		this.price = price;
		this.isChild = isChild;
		this.purchaseDateTime = LocalDateTime.now();
	}
	
	
}
