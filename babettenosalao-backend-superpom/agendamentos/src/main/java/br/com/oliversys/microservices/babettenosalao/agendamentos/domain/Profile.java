 package br.com.oliversys.microservices.babettenosalao.agendamentos.domain;
 
 import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
 @Entity
 @Table(name="PERFIS")
 public class Profile implements Serializable {
   private static final long serialVersionUID = 7279682309591880569L;
   
   @Id
   @Column(name="PROFILE_ID")
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;
     
   @Enumerated(EnumType.STRING)
   @Column(name="ASSINATURA")
   private EnumAssinatura assinatura;
   
   @Column(name="DATA_CRIACAO")
   @Temporal(TemporalType.DATE)
   private Date dataInicialRegistro;
   
   @Column(name="DATA_VENCIMENTO_ASSINATURA")
   @Temporal(TemporalType.DATE)
   private Date dataVencimentoAssinatura;
   
   @Column(name="VALOR_ASSINATURA")
   private BigDecimal valorAssinatura;
   
   @Embedded
   private TotalVendasMes totalVendasMes;
   
   @OneToOne
   @JoinColumn(name="PROFILE_ID")
   private Estabelecimento estab;
   
   @PrePersist
   protected void onCreate()
   {
     this.dataInicialRegistro = new Date();
   }
   
   public Profile(EnumAssinatura ass)
   {
     this.assinatura = ass;
   }
   
   public Profile() {}
   
   public Long getId() {
     return this.id;
   }
   
   public void setId(Long id) {
     this.id = id;
   }
   
   public EnumAssinatura getAssinatura() {
     return this.assinatura;
   }
   
   public void setAssinatura(EnumAssinatura assinatura) {
     this.assinatura = assinatura;
   }
   
   public Date getDataInicialRegistro() {
     return this.dataInicialRegistro;
   }
   
   public void setDataInicialRegistro(Date dataInicialRegistro) {
     this.dataInicialRegistro = dataInicialRegistro;
   }
   
   public Date getDataVencimentoAssinatura() {
     return this.dataVencimentoAssinatura;
   }
   
   public TotalVendasMes getTotalVendasMes() {
     return this.totalVendasMes;
   }
   
   public void setTotalVendasMes(TotalVendasMes totalVendasMes) {
     this.totalVendasMes = totalVendasMes;
   }
   
   public Estabelecimento getEstabelecimento() {
     return this.estab;
   }
   
   public void setEstabelecimento(Estabelecimento e) {
     this.estab = e;
   }
   
   public void setDataVencimentoAssinatura(Date dataVencimentoAssinatura) {
     this.dataVencimentoAssinatura = dataVencimentoAssinatura;
   }
   
   public BigDecimal getValorAssinatura() {
     return this.valorAssinatura;
   }
   
   public void setValorAssinatura(BigDecimal valorAssinatura) {
     this.valorAssinatura = valorAssinatura;
   }
   
   public TotalVendasMes getTotalMes() {
     return this.totalVendasMes;
   }
   
   public void setTotalMes(TotalVendasMes totalMes) {
     this.totalVendasMes = totalMes;
   }
 
   public void assinarPlano() {}
   
 
   public String toString()
   {
     return "Profile [id=" + this.id + ", assinatura=" + this.assinatura + ", "
     		+ "dataInicialRegistro=" + this.dataInicialRegistro + ", dataVencimentoAssinatura=" + 
    		 this.dataVencimentoAssinatura + ", valorAssinatura=" + this.valorAssinatura + ", totalVendasMes=" + 
     		this.totalVendasMes + "]";
   }
 }