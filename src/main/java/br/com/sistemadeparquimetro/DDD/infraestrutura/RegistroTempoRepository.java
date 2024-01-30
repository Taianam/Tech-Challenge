package br.com.sistemadeparquimetro.DDD.infraestrutura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemadeparquimetro.DDD.dominio.model.Condutor;
import br.com.sistemadeparquimetro.DDD.dominio.model.RegistroTempo;

@Repository
public interface RegistroTempoRepository  extends JpaRepository<RegistroTempo, Long>{

}
