package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import dao.AnimalDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import modelo.Animal;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AnimalService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AnimalDAO dao;
	
	public AnimalService() {}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void cadastarAnimal(Animal animal) throws ValidacaoException{
		validaAnimal(animal);
		dao.Cadastrar(animal);
	}

	public List<Animal> listarAnimal() {
		return dao.listaTodos();
	}

	public Animal getAnimalPorId(Integer Cod_animal) {
		return dao.BuscaPorId(Cod_animal);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerAnimal(Integer Cod_animal) {
		boolean resultado = dao.removePorId(Cod_animal);
		dao.comitarCache();
		return resultado;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarAnimal(Animal animal) throws Exception{
		dao.Atualiza(animal);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void trocaAnimal(Integer Cod_animal, Animal animal) throws Exception{
		animal.setCod_animal(Cod_animal);
		dao.Atualiza(animal);
		dao.comitarCache();
	}
	

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizarAnimal(Integer Cod_animal, Animal animal) throws Exception{
		Animal AnimalDoBanco = dao.BuscaPorId(Cod_animal);
		AnimalDoBanco.atualizarCampos(animal);
		dao.Atualiza(AnimalDoBanco);
		dao.comitarCache();
	}
	
	public void validaAnimal(Animal animal) throws ValidacaoException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Animal>> violations = validator.validate(animal);
		
		if(violations.size() > 0) {
			List<String> mensagens = new ArrayList<String>();
			
			for (ConstraintViolation<Animal> vi : violations) {
				mensagens.add(vi.getMessage());
			}
			
			throw new ValidacaoException(new ViolacoesValidacao(mensagens));
		}
	}

}

