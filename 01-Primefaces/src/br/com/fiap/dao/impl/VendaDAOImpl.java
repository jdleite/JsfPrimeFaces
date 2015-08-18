package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.VendaDAO;
import br.com.fiap.entity.Venda;

public class VendaDAOImpl extends DAOImpl<Venda, Long>
				implements VendaDAO{

	public VendaDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Venda> listar() {		
		return em.createQuery("from Venda",Venda.class)
											.getResultList();
	}

	@Override
	public long contarVenda(String item) {		
		return em.createQuery("select count(v) from Venda "
				+ "v where v.item = :p",Long.class)
				.setParameter("p", item)
				.getSingleResult();
	}

	@Override
	public long contarVenda(String item, int mes) {		
		return em.createQuery("select count(v) from Venda v "
				+ "where v.item = :p and month(v.data) = :mes",
				Long.class)
				.setParameter("mes", mes)
				.setParameter("p", item)
				.getSingleResult();
	}

}






