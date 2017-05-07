package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import model.ItemDto;
import services.items.IItemsService;

@ManagedBean(name = "itemsView")
@ViewScoped
public class Items implements Serializable{

	private static final long serialVersionUID = 8967168714511818014L;
	
	@EJB
	IItemsService iItemService;
	
	@ManagedProperty("#{userLogin}")
	private UserLogin userLogin;
	
	private List<ItemDto> items;
	
	@PostConstruct
	public void init(){
		items = iItemService.getItemsByUserId(userLogin.getId()); 
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public void setItems(List<ItemDto> items) {
		this.items = items;
	}

}
