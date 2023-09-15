package algaworks.erp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// JEITO DE PESQUISAR ANOTAÇÕES DE RESTRIÇÃO VEM SER COLOCADAS ACIMA DE UM ATRIBUTO PARA FUNCIONAR
//@javax.validation.constraints.NotNull //Um jeito comprido de puxar a anotação NotNull do Javax, dessa forma você pode ver outras anotações de restrição que existem.
//@org.hibernate.validator.constraints.Email // um jeito comprido de puxar a anotação Email do Hibernate, dessa forma pode ser visto outras anotações do Hibernate.
// ---------------------------
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull //não permite que o usuário deixe Nulo o campo
	@Size(min = 1, max=80) //tamanho deve ser de pelo menos 1 posição (1 caracter)
	@Column(name = "nome_fantasia", nullable = false, length = 80)
	private String nomeFantasia;
	
	@NotEmpty // é uma anotação do Hibernate e não do Javax, e faz a mesma funçao do @NotNull e @Size(min=1).
	@Column(name = "razao_social", nullable = false, length = 120)
	private String razaoSocial;
	
	@CNPJ //Validação do Hibernate para CNJP 
	@NotNull
	@Column(nullable = false, length = 18)
	private String cnpj;
	
	@NotNull
	@Past //Obriga que o usuário informe uma data no passado (não pode cadastrar uma empresa que não existe)
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fundacao")
	private Date dataFundacao;
	
	@NotNull
	@ManyToOne // estou dizendo que posso ter muitas empresas para o mesmo tipo de ramo de atividade
	@JoinColumn(name = "ramo_atividade_id", nullable = false) // informando o nome da coluna de chave estrangeira do Id da classe RamoAtividade
	private RamoAtividade ramoAtividade;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private TipoEmpresa tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	

	public TipoEmpresa getTipo() {
		return tipo;
	}

	public void setTipo(TipoEmpresa tipo) {
		this.tipo = tipo;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + "]";
	}
	
	
	
}
