package exercice_reseau_bio;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadKGMLFile {
	
	/**
	 * Methode qui permet de parser le fichier KGML de départ et d'extraire les informations importantes pour la voie
	 */
	
	public Pathway p;
	
	ReadKGMLFile(String fichier){

		/** Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory" **/

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		

		try {
			
			final DocumentBuilder builder = factory.newDocumentBuilder(); /** Etape 2 : création d'un parseur **/

			/** Etape 2 : création d'un Document **/

			//final Document document= builder.parse(new File("path_nme00550.kgml")); /* fichier à parser */
			
			final Document document= builder.parse(new File(fichier));
			
			/* Affichage du prologue */
			System.out.println("*************PROLOGUE************");
			System.out.println("version : " + document.getXmlVersion());


			/** Etape 3 : récupération de l'élément racine **/

			final Element racine = document.getDocumentElement();

			/* Affichage de l'élément racine */

			System.out.println("\n*************RACINE************");
			System.out.println("name: " + racine.getAttribute("title"));
			System.out.println("number: " + racine.getAttribute("number"));


			/** Etape 4 : récupération des attributs de reaction **/

			final NodeList reaction = racine.getElementsByTagName("reaction");        /*récupère toutes les réactions du fichier */
			final int nbReactionElements = reaction.getLength();

			List<Reaction> listReactions = new ArrayList<Reaction>();
			for(int i = 0; i<nbReactionElements; i++) {
				final Element reactionbis = (Element) reaction.item(i);

				/** Affichage des attributs**/

				System.out.println("\n************* REACTIONS ************");
				System.out.println("id : " + reactionbis.getAttribute("id"));
				System.out.println("name : " + reactionbis.getAttribute("name"));
				System.out.println("type : " + reactionbis.getAttribute("type"));

				
				final NodeList substrate = reactionbis.getElementsByTagName("substrate");
				List<Compound> listSubstrate = new ArrayList<Compound>();

				final int nbSubstratsElements = substrate.getLength();
				for (int g = 0; g<nbSubstratsElements; g++)
				{

					final Element substratbis = (Element) substrate.item(g);
					Compound s = new Compound(substratbis.getAttribute("id"), substratbis.getAttribute("name"));
					listSubstrate.add(s);

					System.out.println("id_substrat : " + substratbis.getAttribute("id"));
					System.out.println("name_substrat : " + substratbis.getAttribute("name"));
					
					
				}

				final NodeList product = reactionbis.getElementsByTagName("product");
				final int nbProduitsElements = product.getLength();
				List<Compound> listProduct = new ArrayList<Compound>();
				for (int l = 0; l<nbProduitsElements; l++)
				{
					final Element produitbis = (Element) product.item(l);
					Compound p = new Compound(produitbis.getAttribute("id"), produitbis.getAttribute("name"));
					listProduct.add(p);
					
					System.out.println("id_produit : " + produitbis.getAttribute("id"));
					System.out.println("name_produit : " + produitbis.getAttribute("name"));
					
						
				}
				Reaction r = new Reaction(reactionbis.getAttribute("id"), reactionbis.getAttribute("name").substring(3,9), reactionbis.getAttribute("type"), listSubstrate, listProduct);
				listReactions.add(r);
				
				
				System.out.println("Substrats : " + listSubstrate);
				System.out.println("Produits : " + listProduct);
			
			}

			System.out.println("Liste réactions : " + listReactions);
			Pathway p = new Pathway(racine.getAttribute("number"),  racine.getAttribute("name"), racine.getAttribute("title"), listReactions);
			this.p = p;
			
			System.out.println(this.p);
			

		}
		catch (final ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (final SAXException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/* methode pour récupérer les reactions du pathway*/
		
public Pathway getPathway(){
	return this.p;
	
}

public void setPathway(Pathway p){
	this.p = p;
}

}
