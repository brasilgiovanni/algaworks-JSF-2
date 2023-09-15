package algaworks.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



import algaworks.erp.model.Empresa;
import algaworks.erp.model.RamoAtividade;
import algaworks.erp.model.TipoEmpresa;
import algaworks.erp.repository.DaoEmpresa;
import algaworks.erp.repository.DaoRamoAtividade;
import algaworks.erp.service.CadastroEmpresaService;
import algaworks.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable { // Essa classe precisa ser Serializable, pois eh chamada pela
															// pagina GestaoEmpresas.xhtml
	private static final long serialVersionUID = 1L;

	@Inject
	private DaoEmpresa daoEmpresa;

	@Inject
	private FacesMessages messages;
	
	@Inject
	private DaoRamoAtividade daoRamoAtividade;
	
	@Inject
	private CadastroEmpresaService cadastroEmpresaService;

	private List<Empresa> listaEmpresas;

	private String termoPesquisa;
	
	private Converter ramoAtividadeConverter;
	
	private Empresa empresa;
	
	public void prepararNovaEmpresa() {
		empresa = new Empresa();
	}
	
	public void salvar() {
		cadastroEmpresaService.salvar(empresa);
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todasEmpresas();
		}
		
		messages.info("Empresa salva com sucesso");
	}
	

	// Repare que esse metodo pesquisa pelos nomes de RazaoSocial, que foi
	// configurado na classe DaoEmpresa
	public void pesquisar() {
		listaEmpresas = daoEmpresa.pesquisar(termoPesquisa);
		if (listaEmpresas.isEmpty()) {
			messages.info("Sua consulta não retornou registros");
		}
	}

	public void todasEmpresas() {
		listaEmpresas = daoEmpresa.todas();
	}
	
	public List<RamoAtividade> completarRamoAtividade(String userRamoAtividade) {
		List<RamoAtividade> listaRamoAtividade = daoRamoAtividade.pesquisar(userRamoAtividade);
		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividade);
		return listaRamoAtividade;
	}
	
	private boolean jaHouvePesquisa() {
		return termoPesquisa !=null && !"".equals(termoPesquisa);
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	

	public Converter getRamoAtividadeConverter() { //get para ser acessado no xhtml no método 'converter'
		return ramoAtividadeConverter;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public TipoEmpresa[] getTipoEmpresa() {
		return TipoEmpresa.values(); // aqui estamos dizendo que vamos retornar um array de Enum do tipo TipoEmpresa
										// e para buscar os enumerados, basta fazer ".values()"
	}
	

}
