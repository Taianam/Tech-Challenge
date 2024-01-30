package br.com.sistemadeparquimetro.DDD.infraestrutura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemadeparquimetro.DDD.dominio.model.Veiculo;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long>{

}
