package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;



public class ItemDto implements Serializable{

	private static final long serialVersionUID = -3957501070304621550L;

	private Long id;
	private String name;
	private String description;
	private String imagePath;
	private Double initialPrice;
	private Timestamp openingDate;
	private Timestamp expiringDate;
	private Long BestBidValue;
	private String status;
	private CategoryDto category;
	private UserDto user;
	private List<String> availableStatus;
	
	public List<String> getAvailableStatus() {
		return availableStatus;
	}
	public void setAvailableStatus(List<String> availableStatus) {
		this.availableStatus = availableStatus;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
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
	public Double getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(Double initialPrice) {
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
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
}
