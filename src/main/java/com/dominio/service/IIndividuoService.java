package com.dominio.service;

import com.dominio.domain.Individuo;

import java.util.List;

public interface IIndividuoService {
    List<Individuo> listarIndividuos();
    Individuo buscarIndividuoPorId(Integer id);
    void guardarIndividuo(Individuo individuo);
}
