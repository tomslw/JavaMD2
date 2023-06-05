package lv.venta.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "trip_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Trip {
	@Column(name = "Idtr")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idtr;
	
	@ManyToMany
	@JoinTable(name = "city_trip_table", 
	joinColumns = @JoinColumn(name = "Idtr"),
	inverseJoinColumns = @JoinColumn(name = "Idc"))
	@ToString.Exclude
	private Collection<City> cities = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "Idd")
	private Driver driver;
	
	@OneToMany(mappedBy = "trip")
	@ToString.Exclude
	private Collection<Ticket> tickets;
	
	@Column(name = "StartDateTime")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	
	@Column(name = "Duration")
	@NotNull
	@Min(0)
	private double duration;

	public Trip(Collection<City> cities, Driver driver, @NotNull Date startDateTime,
			@NotNull @Min(0) double duration) {
		super();
		this.cities = cities;
		this.driver = driver;
		this.startDateTime = startDateTime;
		this.duration = duration;
	}
	
}
