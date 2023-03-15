package med.voll.api.domain.cancelamento;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoCancelamentoConsulta(

        @NotNull Long idCancelamento,
        
        @NotNull Long idConsulta,

        @NotNull MotivoCancelamento motivo,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") @NotNull LocalDateTime dataCancelamento) {
}
