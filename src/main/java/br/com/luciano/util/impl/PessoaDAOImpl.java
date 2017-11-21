package br.com.luciano.util.impl;

import java.util.List;

import javax.inject.Inject;

import br.com.luciano.model.Pessoa;
import br.com.luciano.util.api.BasicDAO;
import br.com.luciano.util.api.PessoaDAO;

public class PessoaDAOImpl implements PessoaDAO {

	private BasicDAO basicDAO;

	@Inject
	public PessoaDAOImpl(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	@Override
	public Pessoa inserir(Pessoa pessoa) {
		this.basicDAO.inserir(pessoa);
		return pessoa;
	}

	@Override
	public Pessoa buscarPorId(long id) {
		Pessoa pessoa = (Pessoa) this.basicDAO.buscar(Pessoa.class, id);
		return pessoa;
	}

	@Override
	public Pessoa alterar(Pessoa pessoa) {
		this.basicDAO.alterar(pessoa);
		return pessoa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> todas() {
		return (List<Pessoa>) this.basicDAO.lista(Pessoa.class);
	}

	@Override
	public long contador() {
		return this.basicDAO.contador(Pessoa.class);
	}

	@Override
	public void deletar(long id) {
		JpaUtils.transactional(entityManager -> {
			final Pessoa Pessoa = entityManager.find(Pessoa.class, id);
			entityManager.remove(Pessoa);
			return null;
		}, String.format("Could not delete Pessoa with id %s", id));
	}

}
