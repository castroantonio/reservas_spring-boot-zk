package com.castroantonio.reservas.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hash {

	private static final String FUNCAO_HASH = "SHA-1";
	private static final String ENCODING = "UTF-8";

	private static Logger LOG = Logger.getLogger(Hash.class.getName());
	
	
	public static String gerarHash(String entrada) {
		MessageDigest algorithm;
		byte messageDigest[] = null;
		try {
			algorithm = MessageDigest.getInstance(FUNCAO_HASH);

			messageDigest = algorithm.digest(entrada.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOG.log(Level.SEVERE, "Codificação não suportada: " + ENCODING +".", e);
		} catch (NoSuchAlgorithmException e) {
			LOG.log(Level.SEVERE, "Função hash não suportada: " + FUNCAO_HASH +".", e);
		}

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String saida = hexString.toString();

		return saida;
	}

}
