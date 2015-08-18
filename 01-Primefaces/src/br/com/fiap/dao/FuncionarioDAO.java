package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Funcionario;

public interface FuncionarioDAO extends DAO<Funcionario,Integer>{

	List<Funcionario> buscarPorNome(String nome);
	
	List<String> autoCompletePorNome(String nome);
	
}
