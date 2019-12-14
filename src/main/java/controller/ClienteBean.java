package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import exception.ValidacaoException;
import modelo.Cliente;
import service.ClienteService;

@Named
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id_cliente;

	@Inject
	private Cliente cliente;

	private List<Cliente> lista = new ArrayList<Cliente>();

	@Inject
	private ClienteService clService;

	public ClienteBean() {
	}

	public ClienteBean(Cliente cliente, List<Cliente> lista, Integer id_cliente, ClienteService clService) {
		this.cliente = cliente;
		this.lista = lista;
		this.id_cliente = id_cliente;
		this.clService = clService;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteService getClService() {
		return clService;
	}

	public void setClService(ClienteService clService) {
		this.clService = clService;
	}

	public void salvar() throws ValidacaoException {
		try {
			clService.cadastarCliente(cliente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizalizado com sucesso!"));
		}catch(ValidacaoException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Ocorreu um erro ao realizar o cadastro!"));
		}
	}

	public void excluir() throws Exception {
		clService.removerCliente(cliente.getId_cliente());
	}

	public void consultarCliente() throws Exception {
		clService.ConsultarClientePorId(id_cliente);
	}
	
	public List<Cliente> listaTodos() {
		lista = clService.listarCliente();
		return lista;
	}

	public void atualizarCliente() throws Exception {
		clService.atualizarCliente(id_cliente, cliente);
	}

}
