/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.Vue.GUI;

import cardgame.JeuxCartes.Arme;
import cardgame.JeuxCartes.Carte;
import cardgame.JeuxCartes.Enchant;
import cardgame.JeuxCartes.EnchantDegatMoins;
import cardgame.JeuxCartes.EnchantDegatPlus;
import cardgame.JeuxCartes.EnchantFacile;
import cardgame.JeuxCartes.EnchantNeutre;
import cardgame.JeuxCartes.EnchantStase;
import cardgame.JeuxCartes.Guerrier;
import cardgame.JeuxCartes.Paladin;
import cardgame.JeuxCartes.Perso;
import cardgame.JeuxCartes.Pretre;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

/**
 *
 * @author mathieu
 */
public class CartesGUI extends JCheckBox implements MouseListener {

    private final Carte card;
    private final Dimension size;
    public static File pathDonnees = new File("assets");
    public final boolean  estPerso;
    public final boolean  estArme;
    public final boolean  estMagie;
    
    public CartesGUI(Carte cart, Dimension dim) {
        super("");
        card = cart;
        size = dim;
        setSize(dim);
        estPerso = card instanceof Perso;
        estArme = card instanceof Arme;
        estMagie = card instanceof Enchant;
        
        addMouseListener(this);
        try {
            Image img = this.getImage();
            this.setIcon(new ImageIcon(img));
            this.setSelectedIcon(new ImageIcon(this.getImageSelected()));
            this.repaint();
        } catch (IOException ex) {
            Logger.getLogger(CartesGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getInfo() {
        return card.toJSON().toString();
    }

    public Carte getCarte() {
        return card;
    }
    
    private Image getImageSelected() throws IOException {
        BufferedImage img = new BufferedImage(size.width, size.height,
                BufferedImage.TYPE_INT_RGB);
        if (card instanceof Guerrier) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/guerrierS.jpeg"));
        } else if (card instanceof Paladin) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/paladinS.jpeg"));
        } else if (card instanceof Pretre) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/pretreS.jpeg"));
        } else if (card instanceof EnchantFacile) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/facileS.jpeg"));
        } else if (card instanceof EnchantNeutre) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/neutreS.jpeg"));
        } else if (card instanceof Arme) {
            Arme arm = (Arme) card;
            switch (arm.getTypeArme()) {
                case Contondant:
                    img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/armecontondanteS.jpeg"));
                    break;
                case Tranchant:
                    img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/armetranchanteS.jpeg"));
                    break;
                case Perforant:
                    img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/armeperforanteS.jpeg"));
                    break;
                case Neutre:
                    img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/armeneutreS.jpeg"));
                    break;

            }
        } else if (card instanceof EnchantStase) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/staseS.jpeg"));
        } else if (card instanceof EnchantDegatPlus) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/augdegatS.jpeg"));
        } else if (card instanceof EnchantDegatMoins) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/dimdegatsS.jpeg"));
        }
        Graphics2D gr = img.createGraphics();
        return img.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
    }

    private Image getImage() throws IOException {
        BufferedImage img = new BufferedImage(size.width, size.height,
                BufferedImage.TYPE_INT_RGB);
        if (card instanceof Guerrier) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/guerrier.jpeg"));
        } else if (card instanceof Paladin) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/paladin.jpeg"));
        } else if (card instanceof Pretre) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/pretre.jpeg"));
        } else if (card instanceof EnchantFacile) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/facile.jpeg"));
        } else if (card instanceof EnchantNeutre) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/neutre.jpeg"));
        } else if (card instanceof Arme) {
            Arme arm = (Arme) card;
            switch (arm.getTypeArme()) {
                case Contondant:
                    img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/armecontondante.jpeg"));
                    break;
                case Tranchant:
                    img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/armetranchante.jpeg"));
                    break;
                case Perforant:
                    img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/armeperforante.jpeg"));
                    break;
                case Neutre:
                    img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/armeneutre.jpeg"));
                    break;

            }
        } else if (card instanceof EnchantStase) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/stase.jpeg"));
        } else if (card instanceof EnchantDegatPlus) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/augdegat.jpeg"));
        } else if (card instanceof EnchantDegatMoins) {
            img = ImageIO.read(new File(pathDonnees.getAbsolutePath() + "/dimdegats.jpeg"));
        }
        Graphics2D gr = img.createGraphics();
        return img.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON3) {
            InfoDialog carteInfo = new InfoDialog(null, true, this.getInfo());
            carteInfo.setVisible(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
