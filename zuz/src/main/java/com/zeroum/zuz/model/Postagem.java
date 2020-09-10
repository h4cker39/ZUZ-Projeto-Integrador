package com.zeroum.zuz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TB_POSTAGEM")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 1000)
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPostagem = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	@Min(0)
	private long curtida;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public long getCurtida() {
		return curtida;
	}

	public void setCurtida(long curtida) {
		this.curtida = curtida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	
	
	

}
