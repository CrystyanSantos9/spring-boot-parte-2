package med.voll.api.domain.cancelamento.validacoes;

import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.cancelamento.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.ConsultaRepository;

@Component
@RequiredArgsConstructor
public class ValidadorCancelamentoDeConsultaDentroDoPrazo implements ValidadorCancelamentoDeConsulta {

    private final ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.findById(dados.idConsulta()).get();

        var dataConsulta = consulta.getData();

        var dataCancelamento = dados.dataCancelamento().until(dataConsulta, ChronoUnit.HOURS);

        System.out.println(" ------ Passou no validador de consulta fora do prazo ------");

        if (dataCancelamento < 24) {
            throw new ValidacaoException(
                    "Consulta não pode ser cancelada. Período de cancelamento é de 24 horas antes da data de consulta.");
        }

    }

}
