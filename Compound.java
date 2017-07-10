package exercice_reseau_bio;

import java.io.Serializable;

public class Compound implements Serializable{

	private static final long serialVersionUID = 6125371196975140800L;

	private String id;
	private String name;
	
	public Compound() {}
	
	/**
	 * Crée un nouveau composant (avec un code et un nom)
	 * @param id
	 * @param name
	 */
	public Compound(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Récupere l'id d'un composant
	 * @return id
	 */
	
	public String getId() {
		return id;
	}
	/**
	 * Permet de fixer l'id d'un composant
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Récupérer le nom d'un composant
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Permet de fixer le nom d'un composant
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}



}
