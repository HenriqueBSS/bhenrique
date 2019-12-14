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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import exception.ValidacaoException;
import modelo.Medico;
import service.MedicoService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("cliente")
public class MedicoResource implements Serializable{

	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private MedicoService medicoService;

	public MedicoResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Medico> listaMedico() {
		List<Medico> Medicos = medicoService.listarMedicos();
		return Medicos;
	}
	
	@GET
	@Path("{id_medico}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMedicoPorId(@PathParam("id_medico") Integer id_medico) {
		try {
			Medico medico = medicoService.getBuscaPorId(id_medico);
			
			if(medico != null)
				return Response.ok().entity(medico).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereMedico(Medico medico){
		try {
			medicoService.cadastarMedico(medico);
			URI uri = URI.create("/medicos/" + medico.getId_medico());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id_medico}")
	@DELETE
	public Response deleteMedico(@PathParam("id_medico") Integer id_medico) {
		try {
			boolean success = medicoService.removerMedico(id_medico);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
				
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{id_medico}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void trocaMedico(@PathParam("id_medico") Integer id_medico, Medico medico) throws Exception{
		medicoService.atualizarMedico(id_medico, medico);
	}
	
	
}

