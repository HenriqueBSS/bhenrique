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

import modelo.Cliente;


@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ClienteDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "Projeto")
	private EntityManager manager;
	
	private DAO<Cliente> dao;
	
	public ClienteDAO() {}

	public ClienteDAO (EntityManager manager){
		dao = new DAO<Cliente>(manager, Cliente.class);
	}

	@PostConstruct
	private void initDao() {
		this.dao = new DAO<Cliente>(manager, Cliente.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Cadastrar(Cliente t) {
		System.out.println("ola");
		dao.Cadastrar(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void Remove(Cliente cliente) throws Exception{
		dao.Excluir(cliente);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualiza(Cliente t) throws Exception{
		dao.atualizar(t);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cliente> listaTodos() {
		return dao.listaTodos();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente ConsultarClientePorId(Integer id_cliente) {
		return dao.ConsultarClientePorId(id_cliente);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Cliente BuscaPorId(Integer id_cliente) {
		return dao.buscaPorId(id_cliente);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removePorId(Integer id_cliente) {
		String hql = "DELETE FROM Cliente WHERE id_cliente = :id_cliente";
		Query query = manager.createQuery(hql);
		query.setParameter("id_cliente", id_cliente);
		int modificados = query.executeUpdate();
		if(modificados > 0) return true;
		else return false;
	}
	public Cliente retornarPorId(Integer id_cliente){
        String sql = "FROM "+ Cliente.class.getName()+" WHERE id_cliente = :id_cliente";
        Query query = this.manager.createQuery(sql);
        query.setParameter("id_cliente", id_cliente);
        
        return (Cliente) query.getSingleResult();
    }
	
	public void close() {
		this.dao.close();
	}
	public void comitarCache() {
		dao.comitarCache();
	}

}
