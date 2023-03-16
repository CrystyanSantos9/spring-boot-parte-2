package med.voll.api.domain.cancelamento.validacoes;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.cancelamento.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.ConsultaRepository;

@Component
@RequiredArgsConstructor
@Order(1)
public class ValiodadorCancelamentoDeConsultaAtiva implements ValidadorCancelamentoDeConsulta {

    private final ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {

        var consultaExists = consultaRepository.existsById(dados.idConsulta());

        System.out.println(" ------ Passou no validador de consulta existe ------" + consultaExists);

        if (!consultaExists) {
            throw new ValidacaoException("Consulta não existe, verifique e tente novamente mais tarde!");
        }

    }

}
