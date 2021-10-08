package com.castroantonio.reservas.controller;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.castroantonio.reservas.model.Reserva;
import com.castroantonio.reservas.service.ServicoReserva;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class) // permite injeção de dependencia
public class DetalharViewModel {

	private Reserva reserva;

	@WireVariable // faz injeção de dependencia do servico para o View Model
	private ServicoReserva servicoReserva; // a classe deve estar anotada com @Service

	@Init
	public void init() {
		String protocolo = Executions.getCurrent().getParameter("protocolo");
		if (protocolo != null) {
			reserva = servicoReserva.buscar(protocolo);
		}
	}

	public Reserva getReserva() {
		return reserva;
	}

	@Command
	public void voltar() {
		irPaginaDetalhar();
	}

	@Command
	public void remover() {
		servicoReserva.remover(reserva);
		irPaginaDetalhar();
	}

	private void irPaginaDetalhar() {
		Executions.getCurrent().sendRedirect("~./gerenciar.zul");
	}
}