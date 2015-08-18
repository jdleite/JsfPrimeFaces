package br.com.fiap.bean;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.dao.VendaDAO;
import br.com.fiap.dao.impl.VendaDAOImpl;
import br.com.fiap.entity.Venda;
import br.com.fiap.exceptions.DBCommitException;
import br.com.fiap.singleton.EMFactorySingleton;

@ManagedBean
@RequestScoped
public class VendaBean {

	private VendaDAO dao;
	private Venda venda;
	
	//Método de inicialização do ManagedBean
	@PostConstruct
	private void init(){
		EntityManager em = EMFactorySingleton
				.getInstance().createEntityManager();
		dao = new VendaDAOImpl(em);
		venda = new Venda();
		venda.setData(Calendar.getInstance());
	}
	
	//Método para o clique do botão
	public void cadastrar(){
		FacesMessage msg;
		try {
			dao.create(venda);
			msg = new FacesMessage("Cadastrado!");
		} catch (DBCommitException e) {
			e.printStackTrace();
			msg = new FacesMessage("Erro");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}










