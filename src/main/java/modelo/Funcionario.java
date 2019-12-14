package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@JsonInclude(Include.NON_EMPTY)
public class Funcionario implements Serializable{
		
	private static final long serialVersionUID = 117471286515280736L;


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id_f", nullable = false)
    private Integer codigo;
	
		
	@NotBlank(message = "TIPO FUNCIONARIO do funcionario é obrigatorio pode ser vazio!")
	@Column(name = "Tipo_funcionario", nullable = false)
	private String tipo_funcionario;
	
	
    @Column(name = "salario", nullable = false)
    private double salario;
    
    @NotBlank(message = "NOME é obrigatorio não pode ser vazio!")
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "sexo", nullable = false)
    private String sexo;
    
    //@NotBlank(message = "NOME é obrigatorio não pode ser vazio!")
    @Column(name = "CPF", nullable = false)
    private Integer CPF;
    
    //@NotBlank(message = "RG é obrigatorio não pode ser vazio!")
    @Column(name = "RG", nullable = false)
    private Integer RG;
    
    @NotBlank(message = "LOGIN é obrigatorio não pode ser vazio!")
    @Column(name = "login", nullable = false)
    private String login;
    
    @NotBlank(message = "SENHA é obrigat�rio e não pode ser vazio!")
    @Column(name = "senha", nullable = false)
    private String senha;
   
    
    public Funcionario() {
    	
    }
	public Funcionario(Integer codigo, double salario, String nome, String sexo, Integer CPF, Integer RG,
			 String login, String senha,String tipo_funcionario) {
		super();
		this.codigo = codigo;
		this.salario = salario;
		this.nome = nome;
		this.sexo = sexo;
		this.CPF = CPF;
		this.RG = RG;
		this.login = login;
		this.senha = senha;
		this.tipo_funcionario = tipo_funcionario;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getTipo_funcionario() {
		return tipo_funcionario;
	}
	public void setTipo_funcionario(String tipo_funcionario) {
		this.tipo_funcionario = tipo_funcionario;
	}
	public Integer getCOD() {
		return codigo;
	}
	public void setCOD(Integer codigo) {
		this.codigo= codigo;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Integer getCPF() {
		return CPF;
	}
	public void setCPF(Integer CPF) {
		this.CPF = CPF;
	}
	public Integer getRG() {
		return RG;
	}
	public void setRG(Integer RG) {
		this.RG = RG;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((RG == null) ? 0 : RG.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
	
		if (RG == null) {
			if (other.RG != null)
				return false;
		} else if (!RG.equals(other.RG))
			return false;
		if (Double.doubleToLongBits(salario) != Double.doubleToLongBits(other.salario))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", Salario=" + salario + ", nome=" + nome + ", sexo=" + sexo + ", CPF=" + CPF
				+ ", RG=" + RG +  ", login=" + login + ", senha=" + senha
				+ "]";
	}
    
    


}