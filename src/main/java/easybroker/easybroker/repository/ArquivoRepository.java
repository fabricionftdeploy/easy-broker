package easybroker.easybroker.repository;

import easybroker.easybroker.model.ArquivoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArquivoRepository extends JpaRepository<ArquivoModel, Long> {

    Optional<ArquivoModel> findByCodigo(Long codigo);
}
