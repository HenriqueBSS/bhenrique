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

import dao.RecepcionistaDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import modelo.Recepcionista;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class RecepcionistaService implements Serializable{
	private static final long serialVersionUID = 1L;
		
		@Inject
		private RecepcionistaDAO dao;
		
		public RecepcionistaService() {}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void cadastarRecepcionista(Recepcionista recepcionista) throws ValidacaoException{
			dao.Cadastrar(recepcionista);
		}

		public List<Recepcionista> listarRecepcionistas() {
			return dao.listaTodos();
		}
		
		public Recepcionista loginRecepcionista(String nome, String password) {
			return dao.loginRecepcionista(nome, password);
		}
		
		public Recepcionista getBuscaPorId(Integer id_recepcionista) {
			return dao.BuscaPorId(id_recepcionista);
		}

		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removerRecepcionista(Integer id_recepcionista) {
			boolean resultado = dao.removePorId(id_recepcionista);
			dao.comitarCache();
			return resultado;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarRecepcionista(Recepcionista recepcionista) throws Exception{
			dao.atualiza(recepcionista);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarRecepcionista(Integer id_recepcionista, Recepcionista recepcionista) throws Exception {
			Recepcionista recepcionistamodificado = recepcionista;
			recepcionistamodificado.setId_recepcionista(id_recepcionista);
			dao.atualiza(recepcionistamodificado);
			dao.comitarCache();
		}
		
		public void validaRecepcionista(Recepcionista recepcionista) throws ValidacaoException {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<Recepcionista>> violations = validator.validate(recepcionista);
			
			if(violations.size() > 0) {
				List<String> mensagens = new ArrayList<String>();
				
				for (ConstraintViolation<Recepcionista> vi : violations) {
					mensagens.add(vi.getMessage());
				}
				
				throw new ValidacaoException(new ViolacoesValidacao(mensagens));
			}
		}
}
