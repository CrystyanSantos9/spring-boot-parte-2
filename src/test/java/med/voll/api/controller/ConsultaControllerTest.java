package med.voll.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.log4j.Log4j2;
import med.voll.api.domain.consulta.AgendaDeConsulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@Log4j2
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> DadosDetalhamentoConsultaJSON;

    @MockBean
    private AgendaDeConsulta agendaDeConsulta;

    @Test
    @DisplayName("Deveria devolver http 400 quando informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        log.debug("Response is equal === ", response);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Deveria devolver http 200 quando informacoes estao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {

        // data e hora atual, com uma hora a frente
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data, "ATIVO");

        when(agendaDeConsulta.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosAgendamentoConsultaJSON.write(
                        new DadosAgendamentoConsulta(2l, 5l, data, especialidade)).getJson()))
                .andReturn().getResponse();

        log.debug("Response is equal === ", response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        var jsonEsperado = DadosDetalhamentoConsultaJSON.write(dadosDetalhamento).getJson();

        assertEquals(jsonEsperado, response.getContentAsString());
    }

}
