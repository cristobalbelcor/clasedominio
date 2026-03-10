package com.dominio.service;

import com.dominio.dao.IndividuoDao;
import com.dominio.domain.Individuo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividuoService implements IIndividuoService {
    @Autowired
    private IndividuoDao individuoDao;

    @Override
    public List<Individuo> listarIndividuos() {
        return (List<Individuo>) individuoDao.findAll();
    }

    @Override
    public Individuo buscarIndividuoPorId(Integer id) {
        return individuoDao.findById(id).orElse(null);
    }

    @Override
    public void guardarIndividuo(Individuo individuo) {
        individuoDao.save(individuo);
    }
}
