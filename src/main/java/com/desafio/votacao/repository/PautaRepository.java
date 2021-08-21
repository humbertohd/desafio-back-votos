package com.desafio.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.votacao.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
