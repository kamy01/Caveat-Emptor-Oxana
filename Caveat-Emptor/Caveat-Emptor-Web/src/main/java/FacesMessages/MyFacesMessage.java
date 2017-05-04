package FacesMessages;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MyFacesMessage {
	
	public static void addMessage(Severity severity, String summary, String detail){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null,
				new FacesMessage(severity, summary, detail));
		
	}
	
	public static void addExternalMessage(Severity severity, String summary, String detail){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null,
				new FacesMessage(severity, summary, detail));
		
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

}
