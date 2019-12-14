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

import dao.ClienteDAO;
import dto.ViolacoesValidacao;
import exception.ValidacaoException;
import modelo.Cliente;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ClienteService implements Serializable{
	private static final long serialVersionUID = 1L;
		
		@Inject
		private ClienteDAO dao;
		
		public ClienteService() {}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void cadastarCliente(Cliente cliente) throws ValidacaoException{
			validaCliente(cliente);
			dao.Cadastrar(cliente);
		}

		public List<Cliente> listarCliente() {
			return dao.listaTodos();
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public Cliente ConsultarClientePorId(Integer id_cliente) {
			return dao.retornarPorId(id_cliente);
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public boolean removerCliente(Integer id_cliente) {
			boolean resultado = dao.removePorId(id_cliente);
			dao.comitarCache();
			return resultado;
		}

		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarCliente(Cliente cliente) throws Exception{
			dao.atualiza(cliente);
		}
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public void atualizarCliente(Integer id_cliente, Cliente cliente) throws Exception {
			Cliente clientemodificado = cliente;
			clientemodificado.setId_cliente(id_cliente);
			dao.atualiza(clientemodificado);
			dao.comitarCache();
		}
		
		public void validaCliente(Cliente cliente) throws ValidacaoException {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			
			Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
			
			if(violations.size() > 0) {
				List<String> mensagens = new ArrayList<String>();
				
				for (ConstraintViolation<Cliente> vi : violations) {
					mensagens.add(vi.getMessage());
				}
				
				throw new ValidacaoException(new ViolacoesValidacao(mensagens));
			}
		}
}
