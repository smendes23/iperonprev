package br.com.iperonprev.interfaces;

import java.text.ParseException;

public interface FormataTempoServico {
	String tempoFormatado(String dtInicio, String dtFim) throws ParseException;
}
