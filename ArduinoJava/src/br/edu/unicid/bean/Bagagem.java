package br.edu.unicid.bean;

public class Bagagem {
	// padrão JavaBean ou POJO – Classe com getters/setters, mais métodos construtores
	private String tagUID;
	private String nomePassageiro;
	private String poltronaPassageiro;
	
	public Bagagem() {};
	
	public Bagagem(String tagUID, String nomePassageiro, String poltronaPassageiro) {
		super();
		this.tagUID = tagUID;
		this.nomePassageiro = nomePassageiro;
		this.poltronaPassageiro = poltronaPassageiro;
	}
	
	public String getNomePassageiro() {
		return nomePassageiro;
	}
	
	public void setNomePassageiro(String nomePassageiro) {
		this.nomePassageiro = nomePassageiro;
	}
	
	public String getPoltronaPassageiro() {
		return poltronaPassageiro;
	}
	
	public void setPoltronaPassageiro(String poltronaPassageiro) {
		this.poltronaPassageiro = poltronaPassageiro;
	}

	public String getTagUID() {
		return tagUID;
	}

	public void setTagUID(String tagUID) {
		this.tagUID = tagUID;
	}
}
