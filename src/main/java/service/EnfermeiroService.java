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

import dao.EnfermeiroDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import modelo.Enfermeiro;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EnfermeiroService implements Serializable{
	private static final long serialVersionUID = 1L;
		
		@Inject
		private EnfermeiroDAO dao;
		
		public EnfermeiroService() {}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void cadastarEnfermeiro(Enfermeiro enfermeiro) throws ValidacaoException{
			dao.Cadastrar(enfermeiro);
		}

		public List<Enfermeiro> listarEnfermeiros() {
			return dao.listaTodos();
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Enfermeiro BuscarEnfermeiroPorId(Integer id_enfermeiro) {
			return dao.BuscaPorId(id_enfermeiro);
		}

		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removerEnfermeiro(Integer id_enfermeiro) {
			boolean resultado = dao.removePorId(id_enfermeiro);
			dao.comitarCache();
			return resultado;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarEnfermeiro(Enfermeiro enfermeiro) throws Exception{
			dao.atualiza(enfermeiro);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarEnfermeiro(Integer id_enfermeiro, Enfermeiro enfermeiro) throws Exception {
			Enfermeiro enfermeiromodificado = enfermeiro;
			enfermeiromodificado.setId_enfermeiro(id_enfermeiro);
			dao.atualiza(enfermeiromodificado);
			dao.comitarCache();
		}
		
		public void validaEnfermeiro(Enfermeiro enfermeiro) throws ValidacaoException {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<Enfermeiro>> violations = validator.validate(enfermeiro);
			
			if(violations.size() > 0) {
				List<String> mensagens = new ArrayList<String>();
				
				for (ConstraintViolation<Enfermeiro> vi : violations) {
					mensagens.add(vi.getMessage());
				}
				
				throw new ValidacaoException(new ViolacoesValidacao(mensagens));
			}
		}
}
