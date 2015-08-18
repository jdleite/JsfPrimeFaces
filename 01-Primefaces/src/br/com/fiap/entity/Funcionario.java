package br.com.fiap.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="seqFunc", 
sequenceName="FUNC_FUNCIONARIO",
allocationSize=1)
public class Funcionario {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="seqFunc")
	private int codigo;
	
	private String cpf;
	
	private String nome;
	
	private int nivel;
	
	@Lob
	private byte[] foto;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

}






