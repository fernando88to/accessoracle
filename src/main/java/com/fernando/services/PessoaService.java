package com.fernando.services;

import com.fernando.domain.Pessoa;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@ApplicationScoped
public class PessoaService {


    @Transactional
    public boolean salvar(Pessoa pessoa){
        try {
            pessoa.persistAndFlush();
            return true;
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Transactional
    public boolean salvarBloco(Pessoa ...pessoa){
        for (Pessoa p : pessoa) {
            try {
                p.persistAndFlush();
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }

        String msg = null;
        System.out.println(msg.equals("sdfsfd"));



        return false;

    }

}
