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

import dao.MedicoDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import modelo.Medico;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MedicoService implements Serializable{
	private static final long serialVersionUID = 1L;
		
		@Inject
		private MedicoDAO dao;
		
		public MedicoService() {}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void cadastarMedico(Medico medico) throws ValidacaoException{
			dao.Cadastrar(medico);
		}

		public List<Medico> listarMedicos() {
			return dao.listaTodos();
		}

		public Medico getBuscaPorId(Integer id_medico) {
			return dao.BuscaPorId(id_medico);
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removerMedico(Integer id_medico) {
			boolean resultado = dao.removePorId(id_medico);
			dao.comitarCache();
			return resultado;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarMedico(Medico medico) throws Exception{
			dao.atualiza(medico);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarMedico(Integer id_medico, Medico medico) throws Exception {
			Medico medicomodificado = medico;
			medicomodificado.setId_medico(id_medico);
			dao.atualiza(medicomodificado);
			dao.comitarCache();
		}
		
		public void validaMedico(Medico medico) throws ValidacaoException {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<Medico>> violations = validator.validate(medico);
			
			if(violations.size() > 0) {
				List<String> mensagens = new ArrayList<String>();
				
				for (ConstraintViolation<Medico> vi : violations) {
					mensagens.add(vi.getMessage());
				}
				
				throw new ValidacaoException(new ViolacoesValidacao(mensagens));
			}
		}
}
