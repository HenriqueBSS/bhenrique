package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "enfermeiro")
@JsonInclude(Include.NON_EMPTY)

@PrimaryKeyJoinColumn(name = "id_enfermeiro", referencedColumnName = "id_f")
public class Enfermeiro extends Funcionario {
	 
	
	private static final long serialVersionUID = 8664272802645413118L;
	
	
	@Column(name = "id_enfermeiro", nullable = false)
	private Integer id_enfermeiro;
	
	@NotBlank(message = "COREN é obrigatório!")
	@Column(name = "coren", nullable = false)
	private  String coren;

	 
	public Enfermeiro() {
		
	}

	public Enfermeiro(Integer codigo, double salario, String nome, String sexo, Integer CPF, Integer RG, Integer coren, Integer id_enfermeiro) {
		super();
		
	}

	public String getCoren() {
		return coren;
	}

	public void setCoren(String coren) {
		this.coren = coren;
	}

	public Integer getId_enfermeiro() {
		return id_enfermeiro;
	}

	public void setId_enfermeiro(Integer id_enfermeiro) {
		this.id_enfermeiro = id_enfermeiro;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((coren == null) ? 0 : coren.hashCode());
		result = prime * result + ((id_enfermeiro == null) ? 0 : id_enfermeiro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enfermeiro other = (Enfermeiro) obj;
		if (coren == null) {
			if (other.coren != null)
				return false;
		} else if (!coren.equals(other.coren))
			return false;
		if (id_enfermeiro == null) {
			if (other.id_enfermeiro != null)
				return false;
		} else if (!id_enfermeiro.equals(other.id_enfermeiro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enfermeiro [id_enfermeiro=" + id_enfermeiro + ", coren=" + coren + "]";
	}

	public void atualizarCampos(Enfermeiro enfermeiro) {
		if(enfermeiro == null) return;
		
		if(enfermeiro.getNome()!= null) {
			this.setNome(enfermeiro.getNome());
		}
		if(enfermeiro.getCoren() != null) {
			this.setCoren(enfermeiro.getCoren());
		}
		if(enfermeiro.getLogin() != null) {
			this.setLogin(enfermeiro.getLogin());
		}
		if(enfermeiro.getCPF() != null) {
			this.setCPF(enfermeiro.getCPF());
		}
		if(enfermeiro.getSalario() != 0) {
			this.setSalario(enfermeiro.getSalario());
		}
		if(enfermeiro.getRG() != null) {
			this.setRG(enfermeiro.getRG());
		}
		if(enfermeiro.getSenha() != null) {
			this.setSenha(enfermeiro.getSenha());
		}
		if(enfermeiro.getSexo() != null) {
			this.setSexo(enfermeiro.getSexo());
		}
		if(enfermeiro.getTipo_funcionario() != null) {
			this.setTipo_funcionario(enfermeiro.getTipo_funcionario());
		}
	
	}
}


