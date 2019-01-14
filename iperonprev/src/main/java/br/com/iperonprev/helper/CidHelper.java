package br.com.iperonprev.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.Cid;

public class CidHelper {
	private List<Cid> queryCids = new ArrayList<>();
	
	public CidHelper(){
		queryCids = new GenericPersistence<Cid>(Cid.class).listarTodos("Cid");
	}
	
	public List<Cid> devolveResultadoConsulta(String query){
		List<Cid> listaDeCids = queryCids
				.stream()
					.filter(c-> c.getNUMR_numCid().toLowerCase().startsWith(query))
						.limit(5)
							.collect(Collectors.toList());
		return listaDeCids;
	}
}
