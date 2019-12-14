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

import modelo.Enfermeiro;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EnfermeiroDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Enfermeiro> dao;
	
	public EnfermeiroDAO() {}

	public EnfermeiroDAO(EntityManager manager){
		dao = new DAO<Enfermeiro>(manager, Enfermeiro.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new DAO<Enfermeiro>(manager, Enfermeiro.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Cadastrar(Enfermeiro t) {
		System.out.println("ola");
		dao.Cadastrar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Enfermeiro enfermeiro) throws Exception{
		dao.Excluir(enfermeiro);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Enfermeiro t) throws Exception{
		dao.atualizar(t);
	}

	public List<Enfermeiro> listaTodos() {
		return dao.listaTodos();
	}
	
			
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Enfermeiro BuscaPorId(Integer cod_enfermeiro) {
		return dao.buscaPorId(cod_enfermeiro);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer cod_enfermeiro) {
		String hql = "DELETE FROM Animal WHERE cod_enfermeiro = :cod_enfermeiro";
		Query query = manager.createQuery(hql);
		query.setParameter("cod_enfermeiro", cod_enfermeiro);
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
