package resource;

import java.io.Serializable;
import java.net.URI;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import exception.ValidacaoException;
import modelo.Enfermeiro;
import service.EnfermeiroService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("cliente")
public class EnfermeiroResource implements Serializable{

	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private EnfermeiroService enferService;

	public EnfermeiroResource() {}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereEnfermeiro(Enfermeiro enfermeiro)throws ValidatorException{
		try {
			enferService.cadastarEnfermeiro(enfermeiro);
			URI uri = URI.create("/Enfermeiro/" + enfermeiro.getCPF());
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
	public Response getEnfermeiroPorCpf(@PathParam("id_enfermeiro") Integer id_enfermeiro) {
		try {
			Enfermeiro enfermeiro = enferService.BuscarEnfermeiroPorId(id_enfermeiro);
			
			if(enfermeiro != null)
				return Response.ok().entity(enfermeiro).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@Path("{id_enfermeiro}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizaEnfermeiro(@PathParam("id_enfermeiro") Integer id_enfermeiro, Enfermeiro enfermeiro) throws Exception{
		enferService.atualizarEnfermeiro(enfermeiro);
	}

	
	@Path("{id_enfermeiro}")
	@DELETE
	public Response deleteEnfermeiro(@PathParam("id_enfermeiro") Integer id_enfermeiro) {
		try {
			boolean success = enferService.removerEnfermeiro(id_enfermeiro);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
				
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id_enfermeiro}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void trocaEnfermeiro(@PathParam("id_enfermeiro") Integer id_enfermeiro, Enfermeiro enfermeiro) throws Exception{
		enferService.atualizarEnfermeiro(id_enfermeiro, enfermeiro);
	}
	
	
}

