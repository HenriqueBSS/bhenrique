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

import modelo.Animal;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AnimalDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Animal> dao;
	
	public AnimalDAO() {}

	public AnimalDAO(EntityManager manager){
		dao = new DAO<Animal>(manager, Animal.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new DAO<Animal>(manager, Animal.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Cadastrar(Animal t) {
		System.out.println("ola");
		dao.Cadastrar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Animal animal) throws Exception{
		dao.Excluir(animal);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Atualiza(Animal animal) throws Exception{
		dao.atualizar(animal);
	}

	public List<Animal> listaTodos() {
		return dao.listaTodos();
	}
	
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Animal BuscaPorId(Integer cod_animal) {
		return dao.buscaPorId(cod_animal);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer cod_animal) {
		String hql = "DELETE FROM Animal WHERE cod_animal = :cod_animal";
		Query query = manager.createQuery(hql);
		query.setParameter("cod_animal", cod_animal);
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
