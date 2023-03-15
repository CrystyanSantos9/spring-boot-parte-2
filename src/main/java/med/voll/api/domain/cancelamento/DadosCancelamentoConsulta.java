package med.voll.api.domain.cancelamento;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
                @JsonAlias("consulta_id") @NotNull Long idConsulta,

                @NotNull MotivoCancelamento motivo,

                // @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                //@Future
                @NotNull  @JsonAlias("data_cancelamento") LocalDateTime dataCancelamento

) {

}