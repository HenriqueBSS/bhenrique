package service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.RecepcionistaDAO;
import exception.ValidacaoException;
import modelo.Recepcionista;

@Stateless
public class LoginService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private RecepcionistaDAO recepcionistaDAO;

	public Object logar(String password, String nome) throws ValidacaoException {
		if (password.trim().isEmpty() != true && nome.trim().isEmpty() != true) {
			Recepcionista recepcionista = recepcionistaDAO.loginRecepcionista(password, nome);
			 if (recepcionista != null) {
				return recepcionista;
			} else {
				return null;
			}

		} else {
			throw new ValidacaoException("Erro de Login");
		}

	}

}
