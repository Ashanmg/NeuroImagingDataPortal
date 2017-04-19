/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuroimagingdataportal;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ashan
 */
public class InternalPanel1 extends javax.swing.JPanel {

    int count = 1;
    int slidervalue1;
    int slidervalue2;
    public String url;
    String selectedphotourl;
    String savedUrl;
    String currentDirectory;
    public String endpart;

    File[] files = null;
    ArrayList<File> files3 = new ArrayList<File>();

    public InternalPanel1() {
        initComponents();
        //this.setLocationRelativeTo(null);
        this.slidervalue1 = Slider1.getValue();
        this.slidervalue2 = Slider2.getValue();
        valuedisplay1.setText(Integer.toString(slidervalue1));
        valuedisplay2.setText(Integer.toString(slidervalue2));

        //*************************** set the value of slider to the label when it is being moved***************//
        Slider1.addChangeListener(new javax.swing.event.ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slide = (JSlider) ce.getSource();
                valuedisplay1.setText(Integer.toString(slide.getValue()));

            }

        });

        Slider2.addChangeListener(new javax.swing.event.ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slide = (JSlider) ce.getSource();
                valuedisplay2.setText(Integer.toString(slide.getValue()));

            }
        });
    }

    public InternalPanel1(String path, String selectedfile) {
        initComponents();
        //this.setLocationRelativeTo(null);
        this.slidervalue1 = Slider1.getValue();
        this.slidervalue2 = Slider2.getValue();
        valuedisplay1.setText(Integer.toString(slidervalue1));
        valuedisplay2.setText(Integer.toString(slidervalue2));
        //*************************** set the value of slider to the label when it is being moved***************//
        Slider1.addChangeListener(new javax.swing.event.ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slide = (JSlider) ce.getSource();
                valuedisplay1.setText(Integer.toString(slide.getValue()));

            }

        });

        Slider2.addChangeListener(new javax.swing.event.ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slide = (JSlider) ce.getSource();
                valuedisplay2.setText(Integer.toString(slide.getValue()));

            }
        });
        String fileDirectory = path;
        String selectedImage = selectedfile;
        urlfield.setText(selectedImage);
        this.selectedphotourl = selectedImage;
        this.currentDirectory = path;

        // then load the selected image to the original Label calling the method
        imageLoad(selectedImage, 0);
    }

    // this constructor used when cancel the Imagetable by the customer or user
    public InternalPanel1(String path, String selectedfile, int value1, int value2) {
        initComponents();
        //this.setLocationRelativeTo(null);
        Slider1.setValue(value1);
        Slider2.setValue(value2);
        this.slidervalue1 = Slider1.getValue();
        this.slidervalue2 = Slider2.getValue();
        valuedisplay1.setText(Integer.toString(slidervalue1));
        valuedisplay2.setText(Integer.toString(slidervalue2));
        //*************************** set the value of slider to the label when it is being moved***************//
        Slider1.addChangeListener(new javax.swing.event.ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slide = (JSlider) ce.getSource();
                valuedisplay1.setText(Integer.toString(slide.getValue()));

            }

        });

        Slider2.addChangeListener(new javax.swing.event.ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider slide = (JSlider) ce.getSource();
                valuedisplay2.setText(Integer.toString(slide.getValue()));

            }
        });
        String fileDirectory = path;
        String selectedImage = selectedfile;
        urlfield.setText(selectedImage);
        this.selectedphotourl = selectedImage;
        this.currentDirectory = path;

        // then load the selected image to the original Label calling the method
        imageLoad(selectedImage, 0);
    }

    public void imageLoad(String url, int number) {
        // using this method we can load either original photo or canny edge photo to the relevant label. //
        String getURL = url;
        try {
            BufferedImage getimg = ImageIO.read(new File(getURL));
            System.out.println("got the url :" + getURL);
            // read image as bufferedImage. reason is to fit selected image size to Jlabel size
            BufferedImage img = new BufferedImage(298, 298, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(getimg, 0, 0, 298, 298, null);
            g2.dispose();

            //here set image to the label
            if (number == 0) {
                ImageIcon II = new ImageIcon(img);
                originalLabel.setIcon(II);
                urlfield.setText(getURL);
            }
            if (number == 1) {
                ImageIcon II = new ImageIcon(img);
                edgeLabel.setIcon(II);
            }
            // set url into text field

        } catch (IOException ex) {
            Logger.getLogger(InternalPanel2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPaneIn1 = new javax.swing.JDesktopPane();
        valuedisplay1 = new javax.swing.JLabel();
        valuedisplay2 = new javax.swing.JLabel();
        urlfield = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnProcess = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        Slider1 = new javax.swing.JSlider();
        Slider2 = new javax.swing.JSlider();
        originalLabel = new javax.swing.JLabel();
        edgeLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));

        jDesktopPaneIn1.setBackground(new java.awt.Color(88, 88, 88));
        jDesktopPaneIn1.setPreferredSize(new java.awt.Dimension(1080, 590));

        valuedisplay1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        valuedisplay1.setForeground(new java.awt.Color(255, 255, 255));
        valuedisplay1.setText("22");

        valuedisplay2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        valuedisplay2.setForeground(new java.awt.Color(255, 255, 255));
        valuedisplay2.setText("22");

        urlfield.setBackground(new java.awt.Color(153, 153, 153));
        urlfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlfieldActionPerformed(evt);
            }
        });

        btnBrowse.setBackground(new java.awt.Color(204, 204, 204));
        btnBrowse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Threshold1");

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Threshold2");

        btnProcess.setBackground(new java.awt.Color(204, 204, 204));
        btnProcess.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProcess.setText("Process");
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(204, 204, 204));
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        Slider1.setBackground(new java.awt.Color(88, 88, 88));
        Slider1.setMaximum(1000);
        Slider1.setValue(500);

        Slider2.setBackground(new java.awt.Color(88, 88, 88));
        Slider2.setMaximum(1000);
        Slider2.setValue(250);

        originalLabel.setBackground(new java.awt.Color(255, 255, 255));
        originalLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));

        edgeLabel.setBackground(new java.awt.Color(255, 255, 255));
        edgeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));

        jDesktopPaneIn1.setLayer(valuedisplay1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(valuedisplay2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(urlfield, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(btnBrowse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(btnProcess, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(btnSave, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(Slider1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(Slider2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(originalLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn1.setLayer(edgeLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneIn1Layout = new javax.swing.GroupLayout(jDesktopPaneIn1);
        jDesktopPaneIn1.setLayout(jDesktopPaneIn1Layout);
        jDesktopPaneIn1Layout.setHorizontalGroup(
            jDesktopPaneIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(urlfield, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addGroup(jDesktopPaneIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Slider2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Slider1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jDesktopPaneIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16)
                .addGroup(jDesktopPaneIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valuedisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valuedisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(originalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addGroup(jDesktopPaneIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(edgeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPaneIn1Layout.setVerticalGroup(
            jDesktopPaneIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jDesktopPaneIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(urlfield, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jDesktopPaneIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(Slider2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Slider1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(valuedisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(valuedisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jDesktopPaneIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(originalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDesktopPaneIn1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnProcess)
                        .addGap(45, 45, 45)
                        .addComponent(btnSave))
                    .addComponent(edgeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneIn1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneIn1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void urlfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_urlfieldActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // this button set the path of files and images
        // we write this code inside the try catch to handle the exceptions !!!
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
            chooser.addChoosableFileFilter(filter);
            chooser.showOpenDialog(null);
            File fpath = chooser.getSelectedFile();
            File f = chooser.getCurrentDirectory();
            urlfield.setText(fpath.getPath());
            selectedphotourl = fpath.getPath();
            //**********************************************************************************//
            //here I make a condition to set which Internal pane to the jDesktop pane
            currentDirectory = f.getPath();
            int size = new File(currentDirectory).listFiles().length; //get the size of the path
            System.out.println(size);
            //**** following lines find the count of jpg file in that directory. ****** ///
            String[] fileNames = f.list();
            int total = 0;
            for (int i = 0; i < fileNames.length; i++) {
                if (fileNames[i].contains(".jpg")) {
                    total++;
                }
            }
            System.out.println(total);

            if (selectedphotourl.contains(".zip")) {
                System.out.println(" ela ela");
                String newurl = selectedphotourl.replace(".zip", "");
                System.out.println(newurl);
                // now I am going to get seperately file name for further usage !!!
                // given String devided to two sub strings
                int index = newurl.lastIndexOf("\\");
                System.out.println(index);
                // get the save directory here and concatnate with the last path of the given image
                endpart = newurl.substring(index + 1);
                System.out.println(endpart);
                //now exract fie 
                UnzipFiles un = new UnzipFiles();
                System.out.println(selectedphotourl + " mama liuwe");
                un.unzip(selectedphotourl, newurl);

//                File folder = new File(newurl);
//                files = folder.listFiles();
//                
//                for(File x:files){
//                    files3.add(x);
//                }
//                System.out.println("arry"+files3);
                // passes curent derectroy path and selected file path for load table and set label using selected image ///
                jDesktopPaneIn1.removeAll();
                jDesktopPaneIn1.repaint();
                System.out.println(currentDirectory);
                InternalPanel2 panel = new InternalPanel2(newurl, "",endpart);
                Dimension screenSize = jDesktopPaneIn1.getSize();
                panel.setSize(screenSize.width, screenSize.height);
                jDesktopPaneIn1.add(panel);
                int width = panel.getWidth();
                int height = panel.getHeight();
                panel.setBounds(((screenSize.width / 2) - (width / 2)), ((screenSize.height / 2) - (height / 2)), width, height);
                panel.setVisible(true);

            } else if (total == 1) {
                //**********************************************************************************//
//                //set to original label once we select image as original image to get canny image
//                BufferedImage getimg = ImageIO.read(new File(selectedphotourl));
//                // read image as bufferedImage. reason is to fit selected image size to Jlabel size
//                BufferedImage img = new BufferedImage(originalLabel.getWidth(), originalLabel.getHeight(), BufferedImage.TYPE_INT_RGB);
//                Graphics2D g2 = img.createGraphics();
//                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//                g2.drawImage(getimg, 0, 0, originalLabel.getWidth(), originalLabel.getHeight(), null);
//                g2.dispose();
//
//                //here set image to the label
//                ImageIcon II = new ImageIcon(img);
//                originalLabel.setIcon(II);
                imageLoad(selectedphotourl, 0);

            } else if (total > 1) {
                // passes curent derectroy path and selected file path for load table and set label using selected image ///
                jDesktopPaneIn1.removeAll();
                jDesktopPaneIn1.repaint();
                InternalPanel2 panel = new InternalPanel2(currentDirectory, selectedphotourl);
                Dimension screenSize = jDesktopPaneIn1.getSize();
                panel.setSize(screenSize.width, screenSize.height);
                jDesktopPaneIn1.add(panel);
                int width = panel.getWidth();
                int height = panel.getHeight();
                panel.setBounds(((screenSize.width / 2) - (width / 2)), ((screenSize.height / 2) - (height / 2)), width, height);
                panel.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Please select JPG/JPEG image to process", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                urlfield.setText("");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        // when press the hold button
        // It set the original image to originalLabel and set edge detected image to edge label

        this.slidervalue1 = Slider1.getValue();
        this.slidervalue2 = Slider2.getValue();
        //String url = urlfield.getText();

        //JOptionPane.showMessageDialog(null, "Please select image to process", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        try {
            EdgedetectProcessing er = new EdgedetectProcessing(selectedphotourl, count, currentDirectory);
            savedUrl = er.process(slidervalue1, slidervalue2);
            System.out.println(slidervalue1 + "  " + slidervalue2);

//            //set to edge label once we select image as original image to get canny image
//            BufferedImage getimg = ImageIO.read(new File(savedUrl));
//            // read image as bufferedImage. reason is to fit selected image size to Jlabel size
//            BufferedImage img = new BufferedImage(edgeLabel.getWidth(), edgeLabel.getHeight(), BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2 = img.createGraphics();
//            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g2.drawImage(getimg, 0, 0, edgeLabel.getWidth(), edgeLabel.getHeight(), null);
//            g2.dispose();
//            ImageIcon II2 = new ImageIcon(img);
//            edgeLabel.setIcon(II2);//
            imageLoad(savedUrl, 1);
            System.out.println(" changed !!!");
            //count++;

            // get the matrix to write whatever places that user wants !!!!
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(null, "Please select image to process", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnProcessActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // save the processed image which is temperory saved in some place to the given path
        if (urlfield.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select image to process", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        } else if (savedUrl == null) {
            JOptionPane.showMessageDialog(null, "Please click process before saving.", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        } else {
            JFileChooser savepath = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
            savepath.addChoosableFileFilter(filter);
            savepath.showSaveDialog(null);
            File finalPath = savepath.getSelectedFile();

            try {
                File file = new File(savedUrl);
                BufferedImage saveImage = ImageIO.read(file);
                ImageIO.write(saveImage, "jpg", finalPath);
                file.delete();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider Slider1;
    private javax.swing.JSlider Slider2;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnProcess;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel edgeLabel;
    private javax.swing.JDesktopPane jDesktopPaneIn1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel originalLabel;
    private javax.swing.JTextField urlfield;
    private javax.swing.JLabel valuedisplay1;
    private javax.swing.JLabel valuedisplay2;
    // End of variables declaration//GEN-END:variables
}
