package algaworks.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import algaworks.erp.model.Empresa;
import algaworks.erp.repository.DaoEmpresa;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DaoEmpresa daoEmpresa;
	
	private List<Empresa> listaEmpresas;
	
	public void todasEmpresas() {
		listaEmpresas = daoEmpresa.todas();
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

}
