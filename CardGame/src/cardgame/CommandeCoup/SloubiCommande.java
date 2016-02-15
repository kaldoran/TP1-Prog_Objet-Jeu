package cardgame.CommandeCoup;

import cardgame.ResultUtils.RefuseResult;
import cardgame.ResultUtils.Resultat;
import cardgame.Vue.GUI.InfoDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathieu
 */
public class SloubiCommande implements Commande {

    private  String messageImportant = "";
    public static File pathDonnees = new File("assets");
    
    public SloubiCommande(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(pathDonnees.getAbsolutePath() + "/sloubi.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            messageImportant = sb.toString();
            
        } catch (Exception ex) {
            Logger.getLogger(SloubiCommande.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(SloubiCommande.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
    
    @Override
    public Boolean coupPossible() {
        return true;
    }

    @Override
    public Boolean coupFinitTour() {
        return false;
    }

    @Override
    public Resultat placerCoup() {
        InfoDialog inf = new InfoDialog(null, true, messageImportant);
        //inf.setFont(new java.awt.Font("Monospaced", 3, 10)); // NOI18N);
        inf.setVisible(true);
        return new RefuseResult("Bel essai.");
    }
}
