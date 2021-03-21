package com.crud.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "usuarioFoto")
public class UsuarioFoto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curf_seq")
	@SequenceGenerator(name = "curf_seq", sequenceName = "seq_curf", allocationSize = 1)
	@Column(name = "usuariofotoid")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "extensao")
	private String extensao;

	@Column(name = "contenttype")
	private String contentType;
	
	@Column(name = "datacadastro")
	private LocalDate dataCadastro;
	
	@Column(name = "file")
	private Byte[] file;

	
	public UsuarioFoto() {
		super();
	}

	public UsuarioFoto(String nome, String extensao, LocalDate dataCadastro, Byte[] file) {
		super();
		this.nome = nome;
		this.extensao = extensao;
		this.dataCadastro = dataCadastro;
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Byte[] getFile() {
		return file;
	}

	public void setFile(Byte[] file) {
		this.file = file;
	}
}
