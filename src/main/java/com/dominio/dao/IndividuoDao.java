package com.dominio.dao;

import com.dominio.domain.Individuo;
import org.springframework.data.repository.CrudRepository;

public interface IndividuoDao extends CrudRepository<Individuo, Integer> {
}
