package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "recepcionista")
@JsonInclude(Include.NON_EMPTY)

@PrimaryKeyJoinColumn(name = "id_recepcionista", referencedColumnName = "id_f")
public class Recepcionista extends Funcionario{
	
	private static final long serialVersionUID = -7881296894622630975L;


	@NotBlank(message = "GRAU DE ESCOLARIDADE é obrigatorio não pode ser vazio!")
    @Column(name = "grau", nullable = false)
	private String grau;
	
	@NotBlank(message = "SENHA é obrigatorio não pode ser vazio!")
    @Column(name = "grau", nullable = false)
	private String password;

    @Column(name = "id_recepcionista", nullable = false)
    private Integer id_recepcionista;
	
	
	
	public Recepcionista() {
	}

	public Recepcionista(Integer codigo, double salario, String nome, String sexo, Integer CPF, Integer RG,
			 String password, String grau, Integer id_recepcionista) {
		super();
		this.password = password;
		this.grau = grau;
		this.id_recepcionista = id_recepcionista;
	}

	public String getPassword() {
		return grau;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getGrau() {
		return grau;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}
	

	public Integer getId_recepcionista() {
		return id_recepcionista;
	}

	public void setId_recepcionista(Integer id_recepcionista) {
		this.id_recepcionista = id_recepcionista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((grau == null) ? 0 : grau.hashCode());
		result = prime * result + ((id_recepcionista == null) ? 0 : id_recepcionista.hashCode());
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
		Recepcionista other = (Recepcionista) obj;
		if (grau == null) {
			if (other.grau != null)
				return false;
		} else if (!grau.equals(other.grau))
			return false;
		if (id_recepcionista == null) {
			if (other.id_recepcionista != null)
				return false;
		} else if (!id_recepcionista.equals(other.id_recepcionista))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recepcionista [escolaridade=" + grau + ", id_recepcionista=" + id_recepcionista + "]";
	}

	public void atualizarCampos(Recepcionista recepcionista) {
		if(recepcionista == null) return;
		
		if(recepcionista.getNome()!= null) {
			this.setNome(recepcionista.getNome());
		}
		
		if(recepcionista.getLogin() != null) {
			this.setLogin(recepcionista.getLogin());
		}
		if(recepcionista.getCPF() != null) {
			this.setCPF(recepcionista.getCPF());
		}
		if(recepcionista.getSalario() != 0) {
			this.setSalario(recepcionista.getSalario());
		}
		if(recepcionista.getRG() != null) {
			this.setRG(recepcionista.getRG());
		}
		if(recepcionista.getSenha() != null) {
			this.setSenha(recepcionista.getSenha());
		}
		if(recepcionista.getSexo() != null) {
			this.setSexo(recepcionista.getSexo());
		}
		if(recepcionista.getTipo_funcionario() != null) {
			this.setTipo_funcionario(recepcionista.getTipo_funcionario());
		}
	}	
	
	
}
