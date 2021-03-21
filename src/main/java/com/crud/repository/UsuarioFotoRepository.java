package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.model.UsuarioFoto;

@Repository
public interface UsuarioFotoRepository extends JpaRepository<UsuarioFoto, Long> {

}
