package exercice_reseau_bio;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

public class Graphe {


	public Graph graph;
	
	/**
	 * Récupère un graphe construit 
	 * @return graph
	 */

	public Graph getGraphe() {
		return this.graph;
	}
	
	/**
	 * Représentation graphique de la voie 
	 * @param number : argument correspondant au nom d'une réaction 
	 */

	public Graphe(String number){
		
		// On récupère les composants, et on les met dans une table de hash 
		
		RecupComp rz = new RecupComp();

		Map<String,String> comp = rz.RecupCompound();
		Map<String,String> listEspeces = rz.RecupCompound();
		
		File repertoire = new File ("/Users/Narjes/Desktop/EDC/Bacteria");
		List<Reaction> listR = new ArrayList<Reaction>();
		for(File file : repertoire.listFiles()){
			if(file.isDirectory()){
				for(File fileSup : file.listFiles()){
					if(fileSup.getName().contains(number)){
						ReadKGMLFile parse = new ReadKGMLFile(fileSup.getPath().toString());
						listR.addAll(parse.getPathway().getListReactions());
						// ajout le nom du fichier substring(5,8) a la liste des especes du pathway
						// parcourir listR
							//ajouter a ta reaction la liste d'especes
					}
				}
			}
		}


		Pathway voie = new Pathway("2", "bbb", "aaa", listR);


		graph = new MultiGraph("Tutorial1"); // Création du graphe
		

		for (int i=0; i<voie.getListReactions().size(); i++){ // récuperation de la liste des réaction d'une voie
			Reaction r = voie.getListReactions().get(i);

			for (Compound substrat : r.getListSubstrate()){		// récupération de la liste des substrats d'une voie
				if(graph.getNode(substrat.getName()) == null) {	// on vérifie que le noeud n'existe pas pour le créer
					graph.addNode(substrat.getName());
					graph.getNode(substrat.getName()).addAttribute("ui.label", comp.get(substrat.getName()));
				}


				for (Compound produit : r.getListProduct()){   // récupération des produits et création des noeuds
					if(graph.getNode(produit.getName()) == null){
						graph.addNode(produit.getName());
						graph.getNode(produit.getName()).addAttribute("ui.label", comp.get(produit.getName()));
					}

					if (graph.getEdge(substrat.getName()+"_"+produit.getName()) == null){ // creation des ponts entre substrats et produits 
						graph.addEdge(substrat.getName()+"_"+produit.getName(), substrat.getName(), produit.getName(), true)
						.addAttribute("ui.label", r.getListEnzyme().get(0));
						if(r.getType() == "reversible")
							graph.addEdge(produit.getName()+"_"+substrat.getName(), produit.getName(), substrat.getName(), true)
							.addAttribute("ui.label", r.getListEnzyme().get(0)); // ajout du numéro de l'enzyme sur le pont

					}


				}
			}
		}

	}
	
	//public void colorsheet(Pathway p, String nomEspece)
		// TU VAS PARCOURIR LES REACTIONS DE TA VOIE
			// TU VAS ENSUITE PARCOURIR TA LISTE D'ESPECES POUR CETTE REACTION
				//
}



