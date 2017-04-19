/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuroimagingdataportal;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author ashan
 */
public class Resized extends javax.swing.JPanel {

    /**
     * Creates new form Resized
     */
    public Resized() {
        initComponents();
        //this.setLocationRelativeTo(null);
        //set to original label once we select image as original image to get canny image
           // BufferedImage getimg = ImageIO.read(new File(savedUrl));
            // read image as bufferedImage. reason is to fit selected image size to Jlabel size
            BufferedImage img = new BufferedImage(resizedlabel.getWidth(), resizedlabel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            //g2.drawImage(getimg, 0, 0, resizedlabel.getWidth(), resizedlabel.getHeight(), null);
            g2.dispose();
            ImageIcon II2 = new ImageIcon(img);
            resizedlabel.setIcon(II2);
            System.out.println(" changed !!!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resizedlabel = new javax.swing.JLabel();

        resizedlabel.setBackground(new java.awt.Color(51, 51, 51));
        resizedlabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        resizedlabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resizedlabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resizedlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resizedlabel, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resizedlabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resizedlabelMouseClicked
        
        // dissable or dospose this panel
        this.disable();
    }//GEN-LAST:event_resizedlabelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel resizedlabel;
    // End of variables declaration//GEN-END:variables
}
