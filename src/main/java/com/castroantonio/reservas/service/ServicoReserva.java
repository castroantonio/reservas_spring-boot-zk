package com.castroantonio.reservas.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.castroantonio.reservas.model.Cliente;
import com.castroantonio.reservas.model.Quarto;
import com.castroantonio.reservas.model.Reserva;

@Service
public class ServicoReserva {

	private static final List<Quarto> quartos = new ArrayList<Quarto>();
	private static final List<Reserva> reservas = new ArrayList<Reserva>();

	static { // moca os dados dos quartos
		Quarto quarto = new Quarto("101");
		quarto.setDescricao("Quarto acessível, próximo a recepção e principais serviços do hotel.");
		quarto.setPreco(new BigDecimal("1011"));
		quartos.add(quarto);

		quarto = new Quarto("102");
		quarto.setDescricao("Quarto acessível, próximo a piscina, vista dos fundos do hotel.");
		quarto.setPreco(new BigDecimal("1012"));
		quartos.add(quarto);

		quarto = new Quarto("201");
		quarto.setDescricao("Quarto de solteiro, com vista para a frente do hotel.");
		quarto.setPreco(new BigDecimal("2011"));
		quartos.add(quarto);

		quarto = new Quarto("202");
		quarto.setDescricao("Quarto de casal, com vista para o mar.");
		quarto.setPreco(new BigDecimal("2012"));
		quartos.add(quarto);
	}

	public List<Quarto> disponiveis(Date inicio, Date fim) {
		List<Quarto> disponiveis = new ArrayList<Quarto>(quartos);

		for (Reserva reserva : reservas) {
			if (!(reserva.getFim().before(inicio) || reserva.getInicio().after(fim))) {
				disponiveis.remove(reserva.getQuarto());
			}
		}
		return disponiveis;
	}

	public void reservar(String nome, Quarto quarto, Date inicio, Date fim) {
		if (disponiveis(inicio, fim).contains(quarto)) {
			Cliente cliente = new Cliente(nome);
			Reserva reserva = new Reserva(cliente, quarto, inicio, fim);
			reservas.add(reserva);
		} else {
			throw new IllegalArgumentException("Quarto " + quarto.getNumero() + " não disponível!!!");
		}
	}

	public void remover(Reserva reserva) {
		reservas.remove(reserva);
	}

	public List<Reserva> listar() {
		return reservas;
	}

	public Reserva buscar(String protocolo) {
		for (Reserva reserva : reservas) {
			if (protocolo.equalsIgnoreCase(reserva.getProtocolo())) {
				return reserva;
			}
		}
		return null;
	}
}