package lv.venta.models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "city_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class City {
	@Column(name = "Idc")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idc;
	
	@ManyToMany(mappedBy = "cities")
	@ToString.Exclude
	private Collection<Trip> trips = new ArrayList<>();
	
	@Column(name = "Title")
	@NotNull
	@Size(min = 1, max = 100) 	// according to google the longest is 85 (Taumatawhakatangihangakoauauotamateaturipukakapikimaungahoronukupokaiwhenuakitnatahu) 		
	private String title;		// 			   		   and shortest is 1 (Å)
	
	@Column(name = "Address")
	@NotNull
	@Size(min = 1, max = 200)
	private String address;

	public City(@NotNull @Size(min = 1, max = 100) String title, @NotNull @Size(min = 1, max = 200) String address) {
		super();
		this.title = title;
		this.address = address;
	}
}
