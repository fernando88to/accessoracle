package com.fernando.controller;

import com.fernando.domain.Pessoa;
import com.fernando.repository.PessoaRepository;
import com.fernando.services.PessoaService;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaController {

    @Inject
    PessoaRepository pessoaRepository;
    @Inject
    PessoaService pessoaService;

    @GET
    public List<Pessoa> getPessoas() {
        return Pessoa.listAll(Sort.by("id").descending());
    }
    @GET
    @Path("/list")
    public List<Pessoa> getPessoasComRepositorio() {
        return pessoaRepository.consultar();
    }

    /*@POST
    @Path("/salvar")
    @Transactional
    public Pessoa salvar(@Valid Pessoa pessoa) {
        try {
            pessoa.persistAndFlush();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return pessoa;
    }*/

    @POST
    @Path("/salvar")
    public Response salvar(@Valid Pessoa pessoa) {
        if(pessoaService.salvar(pessoa)){
            return Response.ok().entity(pessoa).build();
        }
        return Response.serverError().entity(pessoa).build();
    }

    @POST
    @Path("/salvarlote")
    public Response salvarLote() {
        Pessoa pessoa1 = new Pessoa("FERNANDO", LocalDate.now());
        Pessoa pessoa2 = new Pessoa("FERNANDO", LocalDate.now());
        Pessoa pessoa3 = new Pessoa("FERNANDO", LocalDate.now());

        if(pessoaService.salvarBloco(pessoa1, pessoa2, pessoa3)){
            return Response.ok().build();
        }
        return Response.serverError().build();
    }
}
