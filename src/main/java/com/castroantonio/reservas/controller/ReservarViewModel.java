package com.castroantonio.reservas.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.castroantonio.reservas.model.Quarto;
import com.castroantonio.reservas.service.ServicoReserva;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class) // permite injeção de dependencia
public class ReservarViewModel {

	private Date inicio;
	private Date fim;
	private List<Quarto> quartosDisponiveis;

	@WireVariable // faz injeção de dependencia do servico para o View Model
	private ServicoReserva servicoReserva; // a classe deve estar anotada com @Service

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Date getFim() {
		return fim;
	}

	public List<Quarto> getQuartosDisponiveis() {
		return quartosDisponiveis;
	}

	@Command // disponibiliza uma funcao a ser executada na view
	@NotifyChange("quartosDisponiveis") // alerta a view que altera o conteudo da variavel
	public void buscar() {
		if (inicio != null && fim != null) {
			quartosDisponiveis = servicoReserva.disponiveis(inicio, fim);
		}
	}

	@Command
	public void selecionar(@BindingParam("quarto") Quarto quarto) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("quarto", quarto);
		parametros.put("inicio", inicio);
		parametros.put("fim", fim);
		Executions.createComponents("~./confirmar.zul", null, parametros); // cria a página passando parâmetros
	}
}