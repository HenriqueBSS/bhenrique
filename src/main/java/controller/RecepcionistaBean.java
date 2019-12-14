package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Recepcionista;
import service.RecepcionistaService;

@Named
@RequestScoped
public class RecepcionistaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id_recepcionista;
	
	@Inject 
	private Recepcionista recepcionista;
	
	private List<Recepcionista> recepcionistas = new ArrayList<Recepcionista>();
	
	@Inject
	private RecepcionistaService recepService;
	
	public RecepcionistaBean() {
		recepcionistas.addAll(recepcionistas);
	}
	
	
	public Integer getId_recepcionista() {
		return id_recepcionista;
	}


	public void setId_recepcionista(Integer id_recepcionista) {
		this.id_recepcionista = id_recepcionista;
	}


	public Recepcionista getRecepcionista() {
		return recepcionista;
	}

	public void setRecepcionista(Recepcionista recepcionista) {
		this.recepcionista = recepcionista;
	}

	public RecepcionistaService getRecepService() {
		return recepService;
	}

	public void salvar() throws ValidacaoException{
		recepService.cadastarRecepcionista(recepcionista);

	}
	
	public void excluir() throws Exception {
			recepService.removerRecepcionista(recepcionista.getId_recepcionista());
	}
	
	public List<Recepcionista> getRecepcionistas() {
		return recepcionistas;
	}


	public void setRecepcionistas(List<Recepcionista> Recepcionistas) {
		this.recepcionistas = Recepcionistas;
	}
	
	public List<Recepcionista> listarRecepcionistas() {
		return recepService.listarRecepcionistas();
	}

	public void AtualizarRecepcionista () throws Exception {
		recepService.atualizarRecepcionista(id_recepcionista, recepcionista);
	}

}
