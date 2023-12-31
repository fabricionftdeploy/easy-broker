package easybroker.easybroker.repository;

import easybroker.easybroker.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    Optional<ClienteModel> findByCodigo(Long codigo);

    Optional<ClienteModel> findByCpf(String cpf);
}
