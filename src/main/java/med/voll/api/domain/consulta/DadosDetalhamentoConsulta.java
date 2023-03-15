package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

public record DadosDetalhamentoConsulta(
                Long id,
                Long idMedico,
                Long idPaciente,
                // data que aparece para o cliente
                @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data,
                String status) {

        public DadosDetalhamentoConsulta(Consulta consulta) {
                this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(),
                                consulta.getStatus());
        }

}
