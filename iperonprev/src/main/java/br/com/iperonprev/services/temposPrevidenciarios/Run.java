package br.com.iperonprev.services.temposPrevidenciarios;

import java.util.List;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.temposPrevidenciarios.TemposPrevidenciarios;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.averbacao.DiaMesAno;

public class Run {

	public static void main(String[] args) {
		List<Averbacao> lista = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao","NUMR_pessoasFuncionais", 9334);
//		List<DiaMesAno> listaPeriodo = new ArrayList<>();
		
		TemposPrevidenciarios concomitancia = new TempoRGPS();
		DiaMesAno dma = new DiaMesAno();
		
		for (Averbacao averbacao : lista) {
//			dma =  new AvaliaDevolvePeriodoAproveitado().executa(averbacao);
			dma = concomitancia.devolve(averbacao);
			System.out.println(dma.getAno()+"-"+dma.getMes()+"-"+dma.getDia());
		}
		
		/*DiaMesAnoHelper dh = new DiaMesAnoHelper();
		dh.somaPeriodos(lista);
		System.out.println(dh.devolvePeriodoFormatado());*/
		
		
	}
}
