package br.com.luciano.util.api;

import java.util.List;

import br.com.luciano.model.Pessoa;

public interface PessoaDAO {

	Pessoa inserir(Pessoa pessoa);

	Pessoa buscarPorId(long id);

	Pessoa alterar(Pessoa Pessoa);

	List<Pessoa> todas();

	long contador();

	void deletar(long id);

}
