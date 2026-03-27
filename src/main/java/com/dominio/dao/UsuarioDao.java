package com.dominio.dao;

import com.dominio.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario,Integer>{
    Usuario findByUsername(String username);
    
}
