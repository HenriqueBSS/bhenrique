package resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
import modelo.Recepcionista;
import service.RecepcionistaService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("recepcionista")
public class RecepcionistaResource implements Serializable{

	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private RecepcionistaService recepcionistaService;

	public RecepcionistaResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recepcionista> listaRecepcionista() {
		List<Recepcionista> Recepcionistas = recepcionistaService.listarRecepcionistas();
		return Recepcionistas;
	}
	
	@GET
	@Path("{id_recepcionista}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRecepcionistaPorId(@PathParam("id_recepcionista") Integer id_recepcionista) {
		try {
			Recepcionista recepcionista = recepcionistaService.getBuscaPorId(id_recepcionista);
			
			if(recepcionista != null)
				return Response.ok().entity(recepcionista).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereRecepcionista(Recepcionista recepcionista){
		try {
			recepcionistaService.cadastarRecepcionista(recepcionista);
			URI uri = URI.create("/recepcionistas/" + recepcionista.getId_recepcionista());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id_recepcionista}")
	@DELETE
	public Response deleteRecepcionista(@PathParam("id_recepcionista") Integer id_recepcionista) {
		try {
			boolean success = recepcionistaService.removerRecepcionista(id_recepcionista);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
				
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id_recepcionista}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void trocaRecepcionista(@PathParam("id_recepcionista") Integer id_recepcionista, Recepcionista recepcionista) throws Exception{
		recepcionistaService.atualizarRecepcionista(id_recepcionista, recepcionista);
	}
	
	@Path("{id_recepcionista}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizaRecepcionista(@PathParam("id_recepcionista") Integer id_recepcionista, Recepcionista recepcionista) throws Exception{
		recepcionistaService.atualizarRecepcionista(id_recepcionista, recepcionista);
	}
}

