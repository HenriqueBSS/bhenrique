package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Medico;
import service.MedicoService;

@Named
@RequestScoped
public class MedicoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id_medico;
	
	@Inject 
	private Medico medico;
	
	private List<Medico> medicos = new ArrayList<Medico>();
	
	@Inject
	private MedicoService MedService;
	
	public MedicoBean() {
		medicos.addAll(medicos);
	}
	
	
	public Integer getId_medico() {
		return id_medico;
	}


	public void setId_medico(Integer id_medico) {
		this.id_medico = id_medico;
	}


	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public MedicoService getMedService() {
		return MedService;
	}

	public void salvar() throws ValidacaoException{
		MedService.cadastarMedico(medico);

	}
	
	public void excluir() throws Exception {
		MedService.removerMedico(medico.getId_medico());
	}
	
	public List<Medico> getMedicos() {
		return medicos;
	}


	public void setMedicos(List<Medico> Medicos) {
		this.medicos = Medicos;
	}
	
	public List<Medico> listarMedicos() {
		return MedService.listarMedicos();
	}

	public void AtualizarMedico () throws Exception {
		MedService.atualizarMedico(id_medico, medico);
	}

}
