package algaworks.erp.repository;

import java.io.Serializable;

import java.util.List;

import javax.inject.Inject;

import algaworks.erp.model.RamoAtividade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DaoRamoAtividade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public DaoRamoAtividade() {

	}

	public DaoRamoAtividade(EntityManager manager) {
		this.manager = manager;
	}

	public List<RamoAtividade> pesquisar(String descricao) {
		String jpql = "from RamoAtividade where descricao like :descricaoParametro";

		TypedQuery<RamoAtividade> query = manager.createQuery(jpql, RamoAtividade.class);
		query.setParameter("descricaoParametro", descricao + "%");
		return query.getResultList();
	}

}
