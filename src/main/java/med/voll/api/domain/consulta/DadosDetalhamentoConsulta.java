package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DadosDetalhamentoConsulta(
                Long id, Long idMedico, Long idPaciente,
                @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data) {
}
