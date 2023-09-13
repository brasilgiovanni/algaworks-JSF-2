package algaworks.erp.service;

import java.io.Serializable;


import javax.inject.Inject;

import algaworks.erp.model.Empresa;
import algaworks.erp.repository.DaoEmpresa;
import algaworks.erp.util.Transacional;

public class CadastroEmpresaService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DaoEmpresa daoEmpresa;
	
	@Transacional
	public void salvar(Empresa empresa) {
		daoEmpresa.guardar(empresa);
	}
	
	@Transacional
	public void excluir(Empresa empresa) {
		daoEmpresa.remover(empresa);
	}
	
	// A regra de negócio (método BuscarId) não é necessario criar uma Transacional, pois apenas visualiza os dados do banco;
	
}
