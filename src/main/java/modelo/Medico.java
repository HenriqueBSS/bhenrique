package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "medico")
@JsonInclude(Include.NON_EMPTY)

@PrimaryKeyJoinColumn(name = "id_medico", referencedColumnName = "id_f")
public class Medico extends Funcionario{
	
	private static final long serialVersionUID = -5780864109557179696L;

	@NotBlank(message = "CRM do usuário é obrigatório e não pode ser vazio!")
    @Column(name = "crm", nullable = false)
	private  String crm;
    
    @Column(name = "escolaridade", nullable = false)
    private String escolaridade;
    
    @Column(name = "id_medico", nullable = false)
    private Integer id_medico;
	
	
	public Medico() {
		
	}

	public Medico(Integer codigo, double salario, String nome, String sexo, Integer CPF, Integer RG, String crm, String escolaridade, Integer id_medico) {
		super();
        this.escolaridade = escolaridade;
        this.id_medico = id_medico;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	public Integer getId_medico() {
		return id_medico;
	}

	public void setId_medico(Integer id_medico) {
		this.id_medico = id_medico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((crm == null) ? 0 : crm.hashCode());
		result = prime * result + ((escolaridade == null) ? 0 : escolaridade.hashCode());
		result = prime * result + ((id_medico == null) ? 0 : id_medico.hashCode());
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
		Medico other = (Medico) obj;
		if (crm == null) {
			if (other.crm != null)
				return false;
		} else if (!crm.equals(other.crm))
			return false;
		if (escolaridade == null) {
			if (other.escolaridade != null)
				return false;
		} else if (!escolaridade.equals(other.escolaridade))
			return false;
		if (id_medico == null) {
			if (other.id_medico != null)
				return false;
		} else if (!id_medico.equals(other.id_medico))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medico [crm=" + crm + ", escolaridade=" + escolaridade + ", id_medico=" + id_medico + "]";
	}

	public void atualizarCampos(Medico medico) {
		if(medico == null) return;
		
		if(medico.getCrm()!= null) {
			this.setCrm(medico.getCrm());
		}
		if(medico.getEscolaridade() != null) {
			this.setEscolaridade(medico.getEscolaridade());
		}

		if(medico.getNome()!= null) {
			this.setNome(medico.getNome());
		}
		if(medico.getLogin() != null) {
			this.setLogin(medico.getLogin());
		}
		if(medico.getCPF() != null) {
			this.setCPF(medico.getCPF());
		}
		if(medico.getSalario() != 0) {
			this.setSalario(medico.getSalario());
		}
		if(medico.getRG() != null) {
			this.setRG(medico.getRG());
		}
		if(medico.getSenha() != null) {
			this.setSenha(medico.getSenha());
		}
		if(medico.getSexo() != null) {
			this.setSexo(medico.getSexo());
		}
		if(medico.getTipo_funcionario() != null) {
			this.setTipo_funcionario(medico.getTipo_funcionario());
		}
	}

	
	
    
}