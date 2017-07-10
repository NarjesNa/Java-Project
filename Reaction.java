package exercice_reseau_bio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Reaction implements Serializable {


	private static final long serialVersionUID = 8872487165864416563L;

	private String id; // code de la reaction
	private String name; // nom de la reaction
	private String type; // réversible ou non 
	private List<Compound> listSubstrate;
	private List<Compound> listProduct;
	private List<String> listEnzyme;
	public File output;

	public Reaction() {}
	
	/**
	 * Constructeur d'une reaction à partir de : 
	 * @param id
	 * @param name
	 * @param type
	 * @param listSubstrate
	 * @param listProduct
	 */
	public Reaction(String id, String name, String type, List<Compound> listSubstrate, List<Compound> listProduct){
		this.id = id;
		
		this.name = name;
		this.type = type;
		this.listSubstrate = listSubstrate;
		this.listProduct = listProduct;
		RecupEnzyme(getName());

	}
	/**
	 * Recuperer l'id d'une reaction
	 * @return id
	 */

	public String getId() {
		return id;
	}
	
	/**
	 * Fixer l'id d'une reaction 
	 * @param id
	 */

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Recuperer le nom d'une reaction
	 * @return name
	 */

	public String getName() {
		return name;
	}
	
	/**
	 * Fixer le nom d'une reaction
	 * @param name
	 */

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Fixer le type de la reaction
	 * @return type
	 */

	public String getType() {
		return type;
	}
	
	/**
	 * Fixer le type d'une reaction
	 * @param type
	 */

	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Recupérer la liste des substrats d'une reaction
	 * @return listSubstrate
	 */

	public List<Compound> getListSubstrate() {
		return listSubstrate;
	}
	
	/**
	 * Fixer la liste des substrats d'une reaction
	 * @param listSubstrate
	 */
	

	public void setListSubstrate(List<Compound> listSubstrate) {
		this.listSubstrate = listSubstrate;
	}
	
	/**
	 * Recupérer la liste des produits d'une réaction
	 * @param listSubstrate
	 */

	public List<Compound> getListProduct() {
		return listProduct;
	}
	
	/**
	 * Fixer la liste des substrats d'une reaction
	 * @param listProduct
	 */

	public void setListProduct(List<Compound> listProduct) {
		this.listProduct = listProduct;
	}
	
	/**
	 * Recuperer la liste des enzymes d'une reaction
	 * @return listEnzyme
	 */

	public List<String> getListEnzyme() {
		return listEnzyme;
	}
	/**
	 * Fixer la liste des enzymes d'une reaction
	 * @param listEnzyme
	 */

	public void setListEnzyme(List<String> listEnzyme) {
		this.listEnzyme = listEnzyme;
	}
	
	/**
	 * Récupérer le fichier de reaction
	 * @return output 
	 */


	public File getOutput() {
		return output;
	}
	
	/**
	 * Fixer  le fichier de reaction
	 * @param output
	 */

	public void setOutput(File output) {
		this.output = output;
	}
	
	/**
	 * Méthode qui permet de récupérer la liste des enzymes d'une réaction à partir de son nom 
	 * @param nameReaction
	 */

	public void RecupEnzyme(String nameReaction) {

		File repertoire = new File("/Users/Narjes/Desktop/EDC/reactions");

		System.out.print(repertoire.isDirectory());

		File[] fichiers = repertoire.listFiles();

		System.out.print(fichiers);

		for (File fichier : fichiers) {
			if (fichier.getName().contains(nameReaction)) {
				this.output = fichier;
				System.out.print(fichier.getPath().toString());
			}
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(this.output);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		List <String> temp = new ArrayList<String>();

		while(scanner.hasNext()){
			String[] tokens = scanner.nextLine().split(" ");
			for (String element : tokens) {
				String last1 = element;
				if ((last1.length() - last1.replace(".", "").length()==3)&&
						(!(last1.contains("[")))&&(!(last1.contains("]")))&&
						(!(last1.contains("EC"))&&(!(last1.contains(","))))) {
					temp.add(last1);
				}
			}
		}
		this.listEnzyme = temp;

	}
}

