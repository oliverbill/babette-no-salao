package br.com.oliversys.microservices.babettenosalao.agendamentos.domain;

public enum EnumLogradouro {
	RUA, R, AV, AVENIDA, PCA, PRACA, AL, ALAMEDA, TRAVESSA, TRA;
	
	public static EnumLogradouro aproximado(String n){
		for(EnumLogradouro e: values()){
			if(n.contains(e.toString()))
				return e;
		}
		return EnumLogradouro.RUA; // se nao achar, retorna RUA
	}
}