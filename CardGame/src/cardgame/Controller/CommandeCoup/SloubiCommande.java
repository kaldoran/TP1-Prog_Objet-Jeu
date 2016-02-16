package cardgame.Controller.CommandeCoup;

import cardgame.ResultUtils.RefuseResult;
import cardgame.ResultUtils.Resultat;
import cardgame.Vue.GUI.InfoDialog;
import cardgame.Vue.GUI.InfoDialogSloubi;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import sun.audio.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathieu
 */
public class SloubiCommande implements Commande {

    private String messageImportant = "";
    public static File pathDonnees = new File("assets");
    private File sloubiAudio;
    private AudioStream as = null;

    public SloubiCommande() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(pathDonnees.getAbsolutePath() + "/sloubi.txt"));
            sloubiAudio = new File(pathDonnees.getAbsolutePath() + "/sloubi.wav");
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            messageImportant = sb.toString();
            if (sloubiAudio.exists()) {
                InputStream in = new FileInputStream(sloubiAudio);
                 as = new AudioStream(in);
            }

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
        InfoDialogSloubi inf = new InfoDialogSloubi(null, true, messageImportant);
        if (as != null) {
            AudioPlayer.player.start(as);            
        }
        inf.setVisible(true);
        if (as != null) {
            AudioPlayer.player.stop(as);            
        }
        return new RefuseResult("Bel essai.");
    }
}
