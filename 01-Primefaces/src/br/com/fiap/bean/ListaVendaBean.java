package br.com.fiap.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.dao.VendaDAO;
import br.com.fiap.dao.impl.VendaDAOImpl;
import br.com.fiap.entity.Venda;
import br.com.fiap.exceptions.DBCommitException;
import br.com.fiap.exceptions.IdNotFoundException;
import br.com.fiap.singleton.EMFactorySingleton;

@ManagedBean
@ViewScoped
public class ListaVendaBean implements Serializable {

	private VendaDAO dao;
	private List<Venda> lista;
	
	private long codigo;
	
	@PostConstruct
	private void init(){
		EntityManager em = EMFactorySingleton
				.getInstance().createEntityManager();
		dao = new VendaDAOImpl(em);
		
		lista = dao.listar();
	}
	
	public void excluir(){
		FacesMessage msg;
		try {
			dao.delete(codigo);
			msg = new FacesMessage("Excluido!");
			lista = dao.listar(); //Atualizar a tabela...
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro..");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public List<Venda> getLista() {
		return lista;
	}
	public void setLista(List<Venda> lista) {
		this.lista = lista;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
}











