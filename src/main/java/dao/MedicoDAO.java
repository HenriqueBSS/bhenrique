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

import modelo.Medico;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MedicoDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Medico> dao;
	
	public MedicoDAO() {}

	public MedicoDAO(EntityManager manager){
		dao = new DAO<Medico>(manager, Medico.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new DAO<Medico>(manager, Medico.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Cadastrar(Medico t) {
		System.out.println("ola");
		dao.Cadastrar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Medico medico) throws Exception{
		dao.Excluir(medico);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Medico t) throws Exception{
		dao.atualizar(t);
	}

	public List<Medico> listaTodos() {
		return dao.listaTodos();
	}
	
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Medico BuscaPorId(Integer id_medico) {
		return dao.buscaPorId(id_medico);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer id_medico) {
		String hql = "DELETE FROM Cliente WHERE id_medico = :id_medico";
		Query query = manager.createQuery(hql);
		query.setParameter("id_medico", id_medico);
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
