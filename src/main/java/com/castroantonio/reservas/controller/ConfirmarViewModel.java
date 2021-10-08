package com.castroantonio.reservas.controller;

import java.util.Date;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.castroantonio.reservas.model.Quarto;
import com.castroantonio.reservas.service.ServicoReserva;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class) // permite injeção de dependencia
public class ConfirmarViewModel {

	private String nome;
	private Quarto quarto;
	private Date inicio;
	private Date fim;

	@WireVariable // faz injeção de dependencia do servico para o View Model
	private ServicoReserva servicoReserva; // a classe deve estar anotada com @Service

	@Init
	public void init(@ExecutionArgParam("quarto") Quarto quarto, @ExecutionArgParam("inicio") Date inicio,
			@ExecutionArgParam("fim") Date fim) {
		this.quarto = quarto;
		this.inicio = inicio;
		this.fim = fim;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getFim() {
		return fim;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	@Command
	public void cancelar() {
		Executions.getCurrent().sendRedirect("~./principal.zul");
	}

	@Command
	public void confirmar() {
		servicoReserva.reservar(nome, quarto, inicio, fim);
		Executions.getCurrent().sendRedirect("~./principal.zul");
	}
}