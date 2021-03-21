package com.crud.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cur_seq")
	@SequenceGenerator(name = "cur_seq", sequenceName = "seq_cur", allocationSize = 1)
	private Long id;
	
	@Column(name = "codigo")
	private Integer codigo;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "datanascimento")
	private LocalDate dataNascimento;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuariofotoid")
	private UsuarioFoto foto;
	
	
	public Usuario() {
		super();
	}

	public Usuario(Integer codigo, String nome, LocalDate dataNascimento, UsuarioFoto foto) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.foto = foto;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public UsuarioFoto getFoto() {
		return foto;
	}
	public void setFoto(UsuarioFoto foto) {
		this.foto = foto;
	}
	
}
