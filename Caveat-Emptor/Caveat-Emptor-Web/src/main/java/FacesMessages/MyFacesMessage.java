package FacesMessages;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import constants.Constant;

public class MyFacesMessage {
	
	public static void addMessage(Severity severity, String summary, String detail){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null,
				new FacesMessage(severity, summary, detail));
		
	}
	
	public static void addExternalMessage(Severity severity, String summary, String detail) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null,
				new FacesMessage(severity, summary, detail));
		
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public static void redirectToPage(String page) {
		
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
			
		} catch (IOException e) {
			
			addMessage(FacesMessage.SEVERITY_ERROR, Constant.LOGIN_ERROR, Constant.NO_SUCH_USER);
			
		}
		
	}

}
