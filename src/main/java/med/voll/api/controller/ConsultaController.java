package med.voll.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.cancelamento.CancelaConsulta;
import med.voll.api.domain.cancelamento.DadosCancelamentoConsulta;
import med.voll.api.domain.cancelamento.DadosDetalhamentoCancelamentoConsulta;
import med.voll.api.domain.consulta.AgendaDeConsulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

        @Autowired
        private AgendaDeConsulta agendaDeConsulta;

        @Autowired
        private ConsultaRepository consultaRepository;

        @Autowired
        CancelaConsulta cancelaConsulta;

        @GetMapping
        public ResponseEntity<Page<DadosDetalhamentoConsulta>> listar(
                        @PageableDefault(size = 10, sort = { "data" }) Pageable paginacao) {
                var page = consultaRepository.findAll(paginacao).map(DadosDetalhamentoConsulta::new);
                return ResponseEntity.ok(page);
        }

        @PostMapping

        public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
                DadosDetalhamentoConsulta consultaSalvaDTO = agendaDeConsulta.agendar(dados);
                return ResponseEntity.ok(consultaSalvaDTO);
        }

        @PostMapping("/cancelamento")
        @Transactional
        public ResponseEntity<DadosDetalhamentoCancelamentoConsulta> cancelar(
        @RequestBody @Valid DadosCancelamentoConsulta dados) {

        DadosDetalhamentoCancelamentoConsulta consultaCancelada =
        cancelaConsulta.cancelarConsulta(dados);

        return ResponseEntity
        .ok(consultaCancelada);
        }
   
}
