package controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import modelo.Recepcionista;
import service.RecepcionistaService;

@Named
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RecepcionistaService recepcionistaService;
	
	private String nome;
	
	private String password;

	public RecepcionistaService getRecepcionistaService() {
		return recepcionistaService;
	}

	public void setRecepcionistaService(RecepcionistaService recepcionistaService) {
		this.recepcionistaService = recepcionistaService;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String Nome) {
		this.nome = Nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String loginRecepcionista() {
		try {
			if(nome.trim().isEmpty() != true && password.trim().isEmpty() != true) {
				Recepcionista recepcionistaSession = recepcionistaService.loginRecepcionista(nome, password);
				if(recepcionistaSession != null) {
					FacesContext sessao = FacesContext.getCurrentInstance();
					sessao.getExternalContext().getSessionMap().put("recepcionista", recepcionistaSession);
					return "menu?faces-redirect=true";
				}
				else {
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Email ou senha incorretos!"));
				}
			}
		}catch(Exception e) {
			return e.getMessage();
		}
		return null;
	}
	
	public String Deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "Login?faces-redirect=true";
	}
	
}
