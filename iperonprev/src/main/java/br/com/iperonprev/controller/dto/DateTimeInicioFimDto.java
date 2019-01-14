package br.com.iperonprev.controller.dto;

import org.joda.time.DateTime;

public class DateTimeInicioFimDto {

	private DateTime dataInicio;
    private DateTime dataFim;

    public DateTime getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(DateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public DateTime getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(DateTime dataFim) {
        this.dataFim = dataFim;
    }

    public DateTimeInicioFimDto() {
    }

    public DateTimeInicioFimDto(DateTime dataInicio, DateTime dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
