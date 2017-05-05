package entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "Items.findAllItems", query = "SELECT item from Items item"),})
@Table(name="items")
public class Items implements Serializable{

	private static final long serialVersionUID = 252618522771935009L;
	
	public static final String FIND_ALL_ITEMS = "Items.findAllItems";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "IMAGE_PATH")
	private String imagePath;
	
	@Column(name = "INITIAL_PRICE")
	private Long initialPrice;
	
	@Column(name = "OPENING_DATE")
	private Timestamp openingDate;
	
	@Column(name = "EXPIRING_DATE")
	private Timestamp expiringDate;
	
	@Column(name = "BEST_BID_VALUE")
	private Long BestBidValue;
	
	@Column(name = "STATUS")
	private String status;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(Long initialPrice) {
		this.initialPrice = initialPrice;
	}
	public Timestamp getOpeningDate() {
		return (Timestamp)openingDate.clone();
	}
	public void setOpeningDate(Timestamp openingDate) {
		this.openingDate = openingDate;
	}
	public Timestamp getExpiringDate() {
		return (Timestamp)expiringDate.clone();
	}
	public void setExpiringDate(Timestamp expiringDate) {
		this.expiringDate = expiringDate;
	}
	public Long getBestBidValue() {
		return BestBidValue;
	}
	public void setBestBidValue(Long bestBidValue) {
		BestBidValue = bestBidValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
