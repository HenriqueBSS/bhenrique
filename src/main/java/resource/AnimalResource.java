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
import modelo.Animal;
import service.AnimalService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("animal")
public class AnimalResource implements Serializable{

	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private AnimalService animalService;

	public AnimalResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Animal> listaAnimal() {
		List<Animal> Animal = animalService.listarAnimal();
		return Animal;
	}
	
	@GET
	@Path("{id_animal}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnimalPorId(@PathParam("id_Animal") Integer id_Animal) {
		try {
			Animal animal = animalService.getAnimalPorId(id_Animal);
			
			if(animal != null)
				return Response.ok().entity(animal).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereAnimal(Animal animal){
		try {
			animalService.cadastarAnimal(animal);
			URI uri = URI.create("/Animais/" + animal.getCod_animal());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Cod_animal}")
	@DELETE
	public Response deleteAnimal(@PathParam("Cod_animal") Integer Cod_animal) {
		try {
			boolean success = animalService.removerAnimal(Cod_animal);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
				
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Cod_animal}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void trocaAnimal(@PathParam("Cod_animal") Integer Cod_animal, Animal animal) throws Exception{
		animalService.trocaAnimal(Cod_animal, animal);
	}
	
	@Path("{Cod_animal}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizaAnimal(@PathParam("Cod_animal") Integer Cod_animal, Animal animal) throws Exception{
		animalService.atualizarAnimal(Cod_animal, animal);
	}
}
