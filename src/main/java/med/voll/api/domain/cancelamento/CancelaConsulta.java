package med.voll.api.domain.cancelamento;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.aspectj.weaver.ast.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.cancelamento.validacoes.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.cancelamento.validacoes.ValiodadorCancelamentoDeConsultaAtiva;
import med.voll.api.domain.consulta.ConsultaRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class CancelaConsulta {

    private final List<ValidadorCancelamentoDeConsulta> validadores;

    private final ConsultaRepository consultaRepository;

    private final CancelamentoDeConsultaRepository cancelamentoDeConsultaRepository;

    public DadosDetalhamentoCancelamentoConsulta cancelarConsulta(DadosCancelamentoConsulta dados) {

        // // // verifica se consulta existe
        // var consultaExists = consultaRepository.existsById(dados.idConsulta());

        // if (!consultaExists) {
        //     throw new ValidacaoException("Consulta não existe, verifique e tente novamente mais tarde!");
        // }

        validadores.forEach(validador -> validador.validar(dados));

      //  validadores.stream().filter(ValiodadorCancelamentoDeConsultaAtiva.class::isInstance).findFirst().get().validar(dados);; 
        

        // inativa consulta
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.excluir();

        // // // cria novo cancelamento
        var cancelamento = cancelamentoDeConsultaRepository
                .save(new CancelamentoDeConsultas(null, consulta, dados.motivo(), dados.dataCancelamento()));

        var cancelamento_feito = new DadosDetalhamentoCancelamentoConsulta(cancelamento.getId(),
                cancelamento.getConsulta().getId(),
                cancelamento.getMotivo(), cancelamento.getDataCancelamento());

        return cancelamento_feito;
    }
}
