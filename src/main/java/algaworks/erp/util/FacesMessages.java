package algaworks.erp.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class FacesMessages implements Serializable { //Essa classe esta sendo chamada no ManageBean(GestaoEmpresasBean) que é serializable
	private static final long serialVersionUID = 1L;

	private void add(String msg, FacesMessage.Severity severity) {
		FacesMessage facesMessage = new FacesMessage(msg);
		facesMessage.setSeverity(severity);
		
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void info(String msg) {
		add(msg, FacesMessage.SEVERITY_INFO);
	}
}