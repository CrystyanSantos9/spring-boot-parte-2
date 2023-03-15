package med.voll.api.domain.cancelamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelamentoDeConsultaRepository extends JpaRepository<CancelamentoDeConsultas, Long> {

    CancelamentoDeConsultas findByConsultaId(Long id);
}
