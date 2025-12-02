package com.umadecruz.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var usuario = usuarioService.consultarPorEmail(email);

      /*  if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }*/

        return org.springframework.security.core.userdetails.User
                .withUsername("admin@gmail.com")
                .password("{noop}123456")
                .roles("USER")
                .build();
    }
}
