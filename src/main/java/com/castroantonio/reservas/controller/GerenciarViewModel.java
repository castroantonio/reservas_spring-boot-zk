package com.castroantonio.reservas.controller;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.castroantonio.reservas.model.Reserva;
import com.castroantonio.reservas.service.ServicoReserva;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class) // permite injeção de dependencia
public class GerenciarViewModel {

	private List<Reserva> reservas;

	@WireVariable // faz injeção de dependencia do servico para o View Model
	private ServicoReserva servicoReserva; // a classe deve estar anotada com @Service
	
	@Init
	public void inciar() {
		reservas = servicoReserva.listar();
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	@Command // disponibiliza uma funcao a ser executada na view
	@NotifyChange("reservas") // alerta a view que altera o conteudo da variavel
	public void remover(@BindingParam("reserva") Reserva reserva) {
		servicoReserva.remover(reserva);
	}
	
	@Command
	public void detalhar(@BindingParam("reserva") Reserva reserva) {
		Executions.getCurrent().sendRedirect("~./detalhar.zul?protocolo=" + reserva.getProtocolo());  // passagem de parâmetro por URL
	}
}