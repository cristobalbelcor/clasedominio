package com.dominio.service;

import com.dominio.dao.UsuarioDao;
import com.dominio.domain.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service

public class UsuarioDetailsServices implements UserDetailsService {
    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no existe");

        }
        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(
                        AuthorityUtils.createAuthorityList(usuario.getRol()))
                .disabled("0".equals(usuario.getActividad()))
                .build();

    }

}
