package com.dominio.service;

import com.dominio.dao.IndividuoDao;
import com.dominio.domain.Individuo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndivuosService implements IIndividuoService{
    @Autowired 
    private IndividuoDao indicDao;
    public List<Individuo> listarIndividuos(){
        return (List <Individuo>)indicDao.findAll();
    }
    public Individuo buscaIndividuoPorId(Integer id){
        return indicDao.findById(id).orElse(null);
    }
}
