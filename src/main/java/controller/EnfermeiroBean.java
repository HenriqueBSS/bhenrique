package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Enfermeiro;
import service.EnfermeiroService;

@Named
@RequestScoped
public class EnfermeiroBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id_enfermeiro;
	
	@Inject 
	private Enfermeiro enfermeiro;
	
	private List<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();
	
	@Inject
	private EnfermeiroService EnferService;
	
	public EnfermeiroBean() {
		enfermeiros.addAll(enfermeiros);
	}
	
	
	public Integer getId_enfermeiro() {
		return id_enfermeiro;
	}


	public void setId_enfermeiro(Integer id_enfermeiro) {
		this.id_enfermeiro = id_enfermeiro;
	}


	public Enfermeiro getEnfermeiro() {
		return enfermeiro;
	}

	public void setEnfermeiro(Enfermeiro enfermeiro) {
		this.enfermeiro = enfermeiro;
	}

	public EnfermeiroService getEnferService() {
		return EnferService;
	}

	public void salvar() throws ValidacaoException{
		EnferService.cadastarEnfermeiro(enfermeiro);

	}
	
	public void excluir() throws Exception {
			EnferService.removerEnfermeiro(enfermeiro.getId_enfermeiro());
	}
	
	public List<Enfermeiro> getEnfermeiros() {
		return enfermeiros;
	}


	public void setEnfermeiros(List<Enfermeiro> enfermeiros) {
		this.enfermeiros = enfermeiros;
	}
	
	public List<Enfermeiro> listarEnfermeiros() {
		return EnferService.listarEnfermeiros();
	}

	public void AtualizarEnfermeiro () throws Exception {
		EnferService.atualizarEnfermeiro(id_enfermeiro, enfermeiro);
	}

}
