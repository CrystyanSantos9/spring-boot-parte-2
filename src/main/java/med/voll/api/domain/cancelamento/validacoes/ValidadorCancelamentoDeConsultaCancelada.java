package med.voll.api.domain.cancelamento.validacoes;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.cancelamento.CancelamentoDeConsultaRepository;
import med.voll.api.domain.cancelamento.DadosCancelamentoConsulta;

@Component
@RequiredArgsConstructor
@Order(2)
public class ValidadorCancelamentoDeConsultaCancelada implements ValidadorCancelamentoDeConsulta {

    private final CancelamentoDeConsultaRepository cancelamentoDeConsultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consultaJaFoiCancelada = cancelamentoDeConsultaRepository.findByConsultaId(dados.idConsulta());

        System.out.println(" ------ Passou no validador de consulta cancelada ------" + consultaJaFoiCancelada);
        if (consultaJaFoiCancelada != null) {
            throw new ValidacaoException("Consulta já foi cancelada!");
        }

    }

}
