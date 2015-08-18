package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Venda;

public interface VendaDAO extends DAO<Venda,Long>{

	List<Venda> listar();
	
	long contarVenda(String item);
	
	long contarVenda(String item, int mes);
	
}
