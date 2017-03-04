 package br.com.oliversys.microservices.babettenosalao.agendamentos.domain;
 
 import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

 @Entity
 @Table(name="COMENTARIOS")
 public class Comentario implements Serializable {
   private static final long serialVersionUID = -3466524441164651559L;
   
   @Id
   @Column(name="COMENTARIO_ID")
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

   @Column(name="MENSAGEM")
   private String mensagem;
   
   @Column(name="AUTOR")
   private String autor;
   
   @Column(name="DATA_CRIACAO")
   @Temporal(TemporalType.TIMESTAMP)
   private Date dataCriacao;
   
   @PrePersist
   protected void onCreate()
   {
     this.dataCriacao = new Date();
   }
   
   public Comentario() {}
   
   public Long getId() { return this.id; }
   public String getMensagem() { return this.mensagem; }
   public String getAutor() { return this.autor; }
   public Date getDataCriacao() { return this.dataCriacao; }
   
   public void setId(Long id) { this.id = id; }
   public void setMensagem(String m) { this.mensagem = m; }
   public void setAutor(String a) { this.autor = a; }
   public void setDataCriacao(Date d) { this.dataCriacao = d; }
  
   @Override
   public String toString() {
	   return "Comentario [id=" + id + ", mensagem=" + mensagem + ", autor="
				+ autor + ", dataCriacao=" + dataCriacao + "]";
   }
   
 }