package algaworks.erp.repository;

import java.util.Date;

import java.util.List;

import algaworks.erp.model.Empresa;
import algaworks.erp.model.RamoAtividade;
import algaworks.erp.model.TipoEmpresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CamadaPersistencia {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlgaWorksPU");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		// Declarando os repositórios
		DaoRamoAtividade daoRamoAtividade = new DaoRamoAtividade(em);
		DaoEmpresa daoEmpresa = new DaoEmpresa(em);
		
		// Buscando as informações do banco
		List<RamoAtividade> listaRamoAtividade = daoRamoAtividade.pesquisar("");
		List<Empresa> listaEmpresa = daoEmpresa.pesquisar("");
		System.out.println(">>>> LISTA DAS EMPRESAS <<<<");
		System.out.println(listaEmpresa);
		
		// Criando uma Empresa
		Empresa empresa = new Empresa();
		empresa.setNomeFantasia("João da Silva");
		empresa.setCnpj("41.952.519/0001-57");
		empresa.setRazaoSocial("João da Silva Razão Social");
		empresa.setTipo(TipoEmpresa.MEI);
		empresa.setDataFundacao(new Date());
		empresa.setRamoAtividade(listaRamoAtividade.get(0));
		
		
		// Salvando uma Empresa
		daoEmpresa.guardar(empresa);
		em.getTransaction().commit();
		
		// Verificando se a inserção da nova empresa Funcionou
		List<Empresa> listaEmpresa2 = daoEmpresa.pesquisar("");
		System.out.println("Verificação da nova Lista de Empresas");
		System.out.println(listaEmpresa2);
		
	}

}
