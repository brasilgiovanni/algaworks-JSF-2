package algaworks.erp.controller;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import algaworks.erp.model.RamoAtividade;

//@FacesConverter("ramoAtividadeConverter") // uma forma de associar com a página xhtml (converter="ramoAtividadeConverter")
//@FacesConverter(forClass = RamoAtividade.class) // outra forma de associa com a página xhtml (nao precisa adicionar nada na página xhtml)
// mas ao inves disso, foi optado por fazer o @Inject do DaoRamoAtividade na classe GestaoEmpresasBean
// e adicionado na mesma classe um método chamado "completarRamoAtividade"
public class RamoAtividadeConverter implements Converter {
	
	private List<RamoAtividade> listaRamoAtividade;
	
	public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividade) {
		this.listaRamoAtividade = listaRamoAtividade;
	}

	// metodo que recebe uma String e converte para um Tipo_Objeto
	// Ou seja, aqui é quando o usuário escolher um RamoAtividade na pagina (formato
	// String) e devemos converter para o tipo Objeto RamoAtividade que o Java entende
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		Long id = Long.valueOf(value); // convertendo o valor de value para tipo Long, pois Id é tipo Long
		
		for (RamoAtividade ramoAtividade: listaRamoAtividade) {
			if (id.equals(ramoAtividade.getId())) {
				return ramoAtividade;
			}
		}
		return null;
	}

	// Metodo que recebe um Tipo_Objeto e converte para uma String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		RamoAtividade ramoAtividade = (RamoAtividade) value;
		return ramoAtividade.getId().toString(); // a entidade ramoAtividade será representada no "autoComplete" do
													// formulario pelo seu Id
	}

}
