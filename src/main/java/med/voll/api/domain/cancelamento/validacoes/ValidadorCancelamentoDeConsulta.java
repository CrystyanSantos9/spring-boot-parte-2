package med.voll.api.domain.cancelamento.validacoes;

import med.voll.api.domain.cancelamento.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
