package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.ItemDto;
import services.items.IItemsService;

@ManagedBean(name = "itemsView")
@ViewScoped
public class Items implements Serializable{

	private static final long serialVersionUID = 8967168714511818014L;
	
	@EJB
	IItemsService iItemService;
	
	private List<ItemDto> items;
	
	@PostConstruct
	public void init(){
		items = iItemService.getAllItems(); 
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public void setItems(List<ItemDto> items) {
		this.items = items;
	}

}
