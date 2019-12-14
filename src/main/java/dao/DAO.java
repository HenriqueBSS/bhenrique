package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager manager, Class<T> classe) {
		this.em = manager;
		this.classe = classe;
	}

	public void Cadastrar(T t) {
		System.out.println("oi");
		em.persist(t);
	}

	public void Excluir(T t) throws Exception {
		em.getTransaction().begin();
		try{
			em.remove(em.merge(t));
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			throw e;
		}
	}
	public void comitarCache() {
		em.flush();
	}
	
	public void atualizar(T t)  {
			em.merge(t);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		return lista;
	}

	public T ConsultarClientePorId(Integer id_cliente) {
		T instancia = em.find(classe, id_cliente);
		return instancia;
	}
	

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

		return lista;
	}
	public T buscaPorId(Integer id_cliente) {
		T instancia = em.find(classe, id_cliente);
		return instancia;
	}
	
	public void close(){
		this.em.close();
	}


}
