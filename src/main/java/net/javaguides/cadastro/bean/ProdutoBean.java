package net.javaguides.cadastro.bean;

public class ProdutoBean {
	protected int id;
	protected String name;
	protected String email;
	protected String fornecedor;
	
	public ProdutoBean() {
	}
	
	public ProdutoBean(String name, String email, String fornecedor) {
		super();
		this.name = name;
		this.email = email;
		this.fornecedor = fornecedor;
	}

	public ProdutoBean(int id, String name, String email, String country) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.fornecedor = fornecedor;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor =fornecedor;
	}
}
