package resource;

import java.io.Serializable;
import java.net.URI;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import exception.ValidacaoException;
import modelo.Cliente;
import service.ClienteService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("cliente")
public class ClienteResource implements Serializable{

	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private ClienteService clService;

	public ClienteResource() {}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereCliente(Cliente cliente)throws ValidatorException{
		try {
			clService.cadastarCliente(cliente);
			URI uri = URI.create("/cliente/" + cliente.getCPF());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	
	@GET
	@Path("{CPF}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientePorCpf(@PathParam("id_cliente") Integer id_cliente) {
		try {
			Cliente cliente = clService.ConsultarClientePorId(id_cliente);
			
			if(cliente != null)
				return Response.ok().entity(cliente).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@Path("{id_cliente}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizaCliente(@PathParam("id_cliente") Integer id_cliente, Cliente cliente) throws Exception{
		clService.atualizarCliente(cliente);
	}
}
