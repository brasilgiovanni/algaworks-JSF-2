package algaworks.erp.repository;

import java.io.Serializable;

import java.util.List;

import javax.inject.Inject;

import algaworks.erp.model.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DaoEmpresa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public DaoEmpresa() {
		
	}
	
	public DaoEmpresa(EntityManager manager) {
		this.manager = manager;
	}
	
	// Criação dos métodos de persistencia
	
	public Empresa buscaId(Long id) {
		return manager.find(Empresa.class, id);
	}
	
	public List<Empresa> todas() {
		String jpql = "from Empresa";
		List<Empresa> query = manager.createQuery(jpql, Empresa.class).getResultList();
		return query; 
	}
	
	public List<Empresa> pesquisar(String nome) {
		String jpql = "from Empresa where nomeFantasia like :nomeFantasia";
		
		TypedQuery<Empresa> query = manager.createQuery(jpql, Empresa.class);
		query.setParameter("nomeFantasia", nome + "%");
		return query.getResultList();
	}
	
	public Empresa guardar(Empresa empresa) {
		return manager.merge(empresa); // o merge atualiza no banco de dados (existindo) ou inserindo (se nao existir a empresa);
	}
	
	public void remover(Empresa empresa) {
		empresa = buscaId(empresa.getId()); // confere se realmente existe a empresa
		manager.remove(empresa); // remove do banco de dados
	}
	
}
