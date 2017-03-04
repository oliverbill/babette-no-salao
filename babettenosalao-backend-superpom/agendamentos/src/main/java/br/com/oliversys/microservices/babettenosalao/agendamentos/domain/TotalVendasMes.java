package br.com.oliversys.microservices.babettenosalao.agendamentos.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TotalVendasMes implements Serializable {

	private static final long serialVersionUID = -4429565357782894807L;

	@Column(name = "TOTAL_MENSAL")
	private BigDecimal totalVendasMensal;

	@Column(name = "MES")
	private Byte mes;

	public TotalVendasMes() {
	}

	public BigDecimal getTotalVendasMensal() {
		return this.totalVendasMensal;
	}

	public void setTotalVendasMensal(BigDecimal totalVendasMensal) {
		this.totalVendasMensal = totalVendasMensal;
	}

	public Byte getMes() {
		return this.mes;
	}

	public void setMes(Byte mes) {
		this.mes = mes;
	}

	public String toString() {
		return "TotalVendasMesVO [totalVendasMensal=" + this.totalVendasMensal + ", mes=" + this.mes + "]";
	}
}