package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.FuncionarioDAO;
import br.com.fiap.entity.Funcionario;

public class FuncionarioDAOImpl extends DAOImpl<Funcionario, Integer>
	implements FuncionarioDAO{

	public FuncionarioDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Funcionario> buscarPorNome(String nome) {
		return em.createQuery("from Funcionario f where "
				+ "upper(f.nome) like upper(:n)",Funcionario.class)
				.setParameter("n","%"+nome+"%").getResultList();
	}

	@Override
	public List<String> autoCompletePorNome(String nome) {
		return em.createQuery("select f.nome from Funcionario f where "
				+ "upper(f.nome) like upper(:n)",String.class)
				.setParameter("n","%"+nome+"%").getResultList();
	}

}





