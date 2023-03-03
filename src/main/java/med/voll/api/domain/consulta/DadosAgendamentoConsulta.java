package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(
                @JsonAlias("medico_id") Long idMedico,

                @NotNull @JsonAlias("paciente_id") Long idPaciente,

                @NotNull 
                @Future 
                @JsonAlias
                ("data_consulta") LocalDateTime data) {
}
