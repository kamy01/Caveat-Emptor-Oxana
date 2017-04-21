package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import services.user.IRegisterService;

@ManagedBean(name = "activation")
@RequestScoped
public class Activation {

	@ManagedProperty(value = "#{param.key}")
	private String key;
	private boolean valid;

	@EJB
	IRegisterService iRegisterService;

	@PostConstruct
	public void init() {

		if (iRegisterService.isAccountActive(key))
			valid = true;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
