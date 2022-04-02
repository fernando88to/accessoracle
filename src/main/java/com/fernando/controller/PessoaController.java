package com.fernando.controller;

import com.fernando.domain.Pessoa;
import com.fernando.repository.PessoaRepository;
import com.fernando.services.PessoaService;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import org.hibernate.Criteria;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Path("/pesquisa")
    public List<Pessoa> pesquisa() {
        /*try (Stream<Pessoa> persons = Pessoa.streamAll()) {
            List<Pessoa> namesButEmmanuels = persons
                    .filter( n -> n.nome.equals("Fernando"))
                    .filter( n -> n.id.equals(1l))
                    .collect(Collectors.toList());
            return namesButEmmanuels;
        }*/

        return pessoaRepository.consultar2();


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
