package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "animal")
@JsonInclude(Include.NON_EMPTY)
public class Animal implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_animal")
	private Integer cod_animal;
	
	@NotBlank(message = "Tipo animal é obrigatorio")
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@NotBlank(message = "Especie animal é obrigatoria")
	@Column(name = "especie", nullable = false)
	private String especie;
	
	@NotBlank(message = "Raca animal é obrigatorio")
	@Column(name = "raca", nullable = false)
	private String raca;
	
	//conex�o com recepcionista 1 x m
	
	@JoinColumn (name ="codFUN", referencedColumnName = "id_recepcionista")
	@ManyToOne (optional = false, fetch = FetchType.EAGER)
	private Recepcionista Recepcionistas;
	
	// conex�o com enfermeiro m x m
	
	@ManyToMany
	@JoinTable(name = "cirurgia",
	joinColumns = @JoinColumn( name = "cod_ani"),
	inverseJoinColumns = @JoinColumn( name = "id_fun"))
	private List<Enfermeiro> enfermeiros;
	
	//conex�o com medico m x m
	
	@ManyToMany
	@JoinTable(name = "cirurgia",
			joinColumns = @JoinColumn( name = "cod_ani"),
			inverseJoinColumns = @JoinColumn( name = "id_fun"))	
	private List<Medico> medicos;
	
	//conex�o com cliente 1 x m
	
	@JoinColumn (name = "codigo", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false , fetch = FetchType.EAGER)
	private Cliente cliente;
			
	
	public Animal() {
		
	}

	public Animal(Integer cod_animal, String tipo, String especie, String raca) {
		super();
		this.cod_animal = cod_animal;
		this.tipo = tipo;
		this.especie = especie;
		this.raca = raca;
	}

	public Integer getCod_animal() {
		return cod_animal;
	}

	public void setCod_animal(Integer cod_animal) {
		this.cod_animal = cod_animal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	
	public Recepcionista getRecepcionistas() {
		return Recepcionistas;
	}

	public void setRecepcionistas(Recepcionista recepcionistas) {
		Recepcionistas = recepcionistas;
	}

	public List<Enfermeiro> getEnfermeiros() {
		return enfermeiros;
	}

	public void setEnfermeiros(List<Enfermeiro> enfermeiros) {
		this.enfermeiros = enfermeiros;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_animal == null) ? 0 : cod_animal.hashCode());
		
		result = prime * result + ((especie == null) ? 0 : especie.hashCode());
		result = prime * result + ((raca == null) ? 0 : raca.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Animal other = (Animal) obj;
		if (cod_animal == null) {
			if (other.cod_animal != null)
				return false;
		} else if (!cod_animal.equals(other.cod_animal))
			return false;
		if (especie == null) {
			if (other.especie != null)
				return false;
		} else if (!especie.equals(other.especie))
			return false;
		if (raca == null) {
			if (other.raca != null)
				return false;
		} else if (!raca.equals(other.raca))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animal [cod_animal=" + cod_animal + ", tipo=" + tipo + ", especie=" + especie + ", descricao="
				+  ", raca=" + raca + "]";
	}
	public void atualizarCampos(Animal animal) {
		if(animal == null) return;
		
		if(animal.getTipo()!= null) {
			this.setTipo(animal.getTipo());
		}
		if(animal.getEspecie()!= null) {
			this.setEspecie(animal.getEspecie());
		}
		if(animal.getRaca()!= null) {
			this.setRaca(animal.getRaca());
		}
		
		}

}
