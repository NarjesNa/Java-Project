package exercice_reseau_bio;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Programme qui permet de récupérer le nom de l'enzyme dans un fichier réaction (fichier texte)
 */
public class RecupComp {

    public Map<String, String> RecupCompound() {
    	
    	Map<String,String> comp = new HashMap<String,String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/Users/Narjes/Desktop/compound.txt"));
            String line;
            while ((line = br.readLine()) != null) {

              if (line.startsWith("cpd")){
                String[] c = line.split("	");
                String [] c1 = c[c.length-1].split(";");
                if(c1.length >= 2){
           
                	comp.put(c[0], c1[0]);
                	
                }
              }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        for (String obj : comp.keySet()) {
			System.out.println("code : "+ obj + " nom: "+ comp.get(obj) );
		}
        
        return comp;
        
        
    }
}
