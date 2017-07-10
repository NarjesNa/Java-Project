package exercice_reseau_bio;

import java.util.List;


/**
* Classe Pathway : permet de créer une voie 
*/

public class Pathway {

		private String id;
		private String name;
		private String title;
		private List<Reaction> listReactions;
		
		public Pathway(){}
		
		/**
		 * Constructeur d'une voie à partir de : 
		 * @param id
		 * @param name
		 * @param title
		 * @param listReactions
		 */
	
		public Pathway(String id, String name, String title, List<Reaction> listReactions){
			this.id = id;
			this.name = name;
			this.title = title;
			this.listReactions = listReactions;
			
		}
		/**
		 * Recuperer l'id d'une voie
		 * @return id 
		 */

		public String getId() {
			return id;
		}
		
		/**
		 * Fixer l'id d'une voie
		 * @param id
		 */

		public void setId(String id) {
			this.id = id;
		}
		
		/**
		 * Recuperer le nom d'une voie 
		 * @return name
		 */

		public String getName() {
			return name;
		}
		
		/**
		 * Fixer le nom d'une voie
		 * @param name
		 */

		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * Recuperer l'intitulé d'une voie
		 * @return title
		 */

		public String getTitle() {
			return title;
		}
		
		/**
		 * Fixer l'intitulé d'une voie
		 * @param title
		 */

		public void setTitle(String title) {
			this.title = title;
		}
		
		/**
		 * Recuperer la liste des reactions d'une voie
		 * @return listReactions
		 */

		public List<Reaction> getListReactions() {
			return listReactions;
		}
		
		/**
		 * Fixer la liste des réactions d'une voie
		 * @param listReactions
		 */

		public void setListReactions(List<Reaction> listReactions) {
			this.listReactions = listReactions;
		}
		
		
}
		