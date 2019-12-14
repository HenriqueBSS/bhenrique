package dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.Recepcionista;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class RecepcionistaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Recepcionista> dao;
	
	public RecepcionistaDAO() {}

	public RecepcionistaDAO(EntityManager manager){
		dao = new DAO<Recepcionista>(manager, Recepcionista.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new DAO<Recepcionista>(manager, Recepcionista.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Cadastrar(Recepcionista t) {
		System.out.println("ola");
		dao.Cadastrar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Recepcionista recepcionista) throws Exception{
		dao.Excluir(recepcionista);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Recepcionista t) throws Exception{
		dao.atualizar(t);
	}

	public List<Recepcionista> listaTodos() {
		return dao.listaTodos();
	}
	
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Recepcionista BuscaPorId(Integer id_recepcionista) {
		return dao.buscaPorId(id_recepcionista);
	}
	
	public Recepcionista loginRecepcionista(String password , String nome)  {
		try {
			String sql = "select E from Recepcionista E where E.nome = : nome and E.password = : password";
			TypedQuery<Recepcionista> query = manager.createQuery(sql , Recepcionista.class);
			query.setParameter("nome",nome);
			query.setParameter("password",password);
			Recepcionista recepcionista = query.getSingleResult();
			if(recepcionista != null && recepcionista.getNome().equals(nome) && recepcionista.getPassword().equals(password)) {
				return recepcionista;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
		return null;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer id_recepcionista) {
		String hql = "DELETE FROM Recepcionista WHERE id_recepcionista = :id_recepcionista";
		Query query = manager.createQuery(hql);
		query.setParameter("id_recepcionista", id_recepcionista);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	
	public void close() {
		this.dao.close();
	}
	public void comitarCache() {
		dao.comitarCache();
	}

}
