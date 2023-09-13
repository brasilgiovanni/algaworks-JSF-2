package algaworks.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import algaworks.erp.model.Empresa;
import algaworks.erp.model.TipoEmpresa;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Empresa empresa = new Empresa();
	
	public void salvar() {
		System.out.println(">>>> EMPRESA SALVA <<<<");
		System.out.println("Razão Social: " + empresa.getRazaoSocial()
		+ " - Nome Fantasia: " + empresa.getNomeFantasia()
		+ " - Tipo Empresa: " + empresa.getTipo());
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public TipoEmpresa[] getTiposEmpresa() { // o compilador já reconhece que vai buscar todos os tipos de empresa do ENUM
		return TipoEmpresa.values();
	}
	
	public String ajuda() {
		return "AjudaGestaoEmpresas?faces-redirect=true";
	}
	

}
