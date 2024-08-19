package com.alves.login.repository;

import com.alves.login.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    UsuarioModel findByCpf(String cpf);

    UsuarioModel findByEmail(String email);
}
