package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private ItunesDAO dao;
	private Graph<Track, DefaultEdge> grafo;
	private Map<Integer, Track> trackIdMap;
	private List<Arco> archi;
	
	public Model() {
		
		dao = new ItunesDAO();
		
	}
	
	
	public List<String> listaGeneri() {
		
		List<String> result = new ArrayList<String>();
		
		for (Genre g : this.dao.getAllGenres()) {
			result.add(g.getName());
		}
		return result;
	}
	
	public boolean valutaMinimo(String genere, double min) {
		
		for (Genre g : this.dao.getAllGenres()) {
			if (g.getName().equals(genere)) {
				if (g.getDurataMinima() <= min) {
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public void creaGrafo(double min, double max, String genere) {
		
		archi = new ArrayList<Arco>();
		trackIdMap = new HashMap<Integer, Track>();
		grafo = new SimpleGraph<Track, DefaultEdge>(DefaultEdge.class);
		this.dao.getVertici(trackIdMap, min, max, genere);
		Graphs.addAllVertices(this.grafo, trackIdMap.values());
		
		for (Track t1 : trackIdMap.values()) {
			for (Track t2 : trackIdMap.values()) {
				if (t1.getTrackId() > t2.getTrackId()) {
					if (t1.getNumeroPlaylist() == t2.getNumeroPlaylist()) {
						Graphs.addEdgeWithVertices(this.grafo, t1, t2);
						archi.add(new Arco(t1, t2));
					}
				}
			}
		}
		
	}
	
	public String calcolaComponentiConnesse() {
		
		int num = 0;
		String s = "";
		ConnectivityInspector<Track, DefaultEdge> inspector =
				 new ConnectivityInspector<Track, DefaultEdge>(this.grafo);
		
		List<Set<Track>> lista = inspector.connectedSets();
		
		for (Set<Track> set : lista) {
			for (Track t : set) {
				num = t.getNumeroPlaylist();
			}
			
			s = s + "Componente connessa con " + set.size() + " vertici, inseriti in " + num + " playlist\n";
		}
		
		return s;
		
	}
	
	public int numeroVertici() {
		return this.grafo.vertexSet().size();
		}
		 public int numeroArchi() {
		return this.grafo.edgeSet().size();
		}

}