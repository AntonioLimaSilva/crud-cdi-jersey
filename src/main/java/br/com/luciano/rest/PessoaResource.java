package br.com.luciano.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.luciano.model.Pessoa;
import br.com.luciano.util.api.PessoaDAO;

@Path("pessoa")
public class PessoaResource {

    private final PessoaDAO pessoaDAO;

    @Inject
    public PessoaResource(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") final int id) {
        Pessoa pessoa = pessoaDAO.buscarPorId(id);

        if(pessoa == null) {
        	return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(pessoa).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response lista() {
        return Response.ok(pessoaDAO.todas()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(Pessoa pessoa) throws URISyntaxException {
        pessoaDAO.inserir(pessoa);
        final long id = pessoa.getId();
        return Response.created(new URI("/service/Pessoa/" + id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterar(Pessoa pessoa) {
        pessoaDAO.alterar(pessoa);
        return Response.noContent().build();
    }

}
