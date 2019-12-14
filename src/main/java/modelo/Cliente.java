package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@Entity
@Table(name = "cliente")
@JsonInclude(Include.NON_EMPTY)
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id_cliente")
	  private Integer id_cliente;

	  @NotBlank(message = "CPF é obrigatório!")
	  @Column(name = "CPF")
	  private String  CPF;
	  
	  @NotBlank(message = "Nome é obrigatório!")
	  @Column(name = "nome")
	  private String nome;
		
	  @NotBlank(message = "RG é obrigatório!")
	  @Column(name = "RG")
	  private String RG;
	  
	  @Column(name = "Ncasa")
	  private Integer Ncasa;
	  
	  @Column(name = "cidade")
	  private String cidade;
	  
	  @Column(name = "CEP")
	  private Integer CEP;
	  
	  @OneToMany (mappedBy = "cliente")
	  private List<Animal> animais;
	  
	public Cliente() {
		
	}
	public Cliente(Integer id_cliente, String cPF, String nome, String rG, Integer ncasa, String cidade, Integer cEP) {
		super();
		this.id_cliente = id_cliente;
		this.CPF = cPF;
		this.nome = nome;
		this.RG = rG;
		this.Ncasa = ncasa;
		this.cidade = cidade;
		this.CEP = cEP;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRG() {
		return RG;
	}
	public void setRG(String rG) {
		RG = rG;
	}
	public Integer getNcasa() {
		return Ncasa;
	}
	public void setNcasa(Integer ncasa) {
		Ncasa = ncasa;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Integer getCEP() {
		return CEP;
	}
	public void setCEP(Integer cEP) {
		CEP = cEP;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CEP == null) ? 0 : CEP.hashCode());
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ((Ncasa == null) ? 0 : Ncasa.hashCode());
		result = prime * result + ((RG == null) ? 0 : RG.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (CEP == null) {
			if (other.CEP != null)
				return false;
		} else if (!CEP.equals(other.CEP))
			return false;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (Ncasa == null) {
			if (other.Ncasa != null)
				return false;
		} else if (!Ncasa.equals(other.Ncasa))
			return false;
		if (RG == null) {
			if (other.RG != null)
				return false;
		} else if (!RG.equals(other.RG))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", CPF=" + CPF + ", nome=" + nome + ", RG=" + RG + ", Ncasa=" + Ncasa
				+ ", cidade=" + cidade + ", CEP=" + CEP + "]";
	}

	public void atualizarCampos(Cliente cliente) {
		if(cliente == null) return;
		
		if(cliente.getNome()!= null) {
			this.setNome(cliente.getNome());
		}
		if(cliente.getCEP() != null) {
			this.setCEP(cliente.getCEP());
		}
		if(cliente.getCidade() != null) {
			this.setCidade(cliente.getCidade());
		}
		if(cliente.getCPF() != null) {
			this.setCPF(cliente.getCPF());
		}
		if(cliente.getNcasa() != null) {
			this.setNcasa(cliente.getNcasa());
		}
		if(cliente.getRG() != null) {
			this.setRG(cliente.getRG());
		}
	}

}
