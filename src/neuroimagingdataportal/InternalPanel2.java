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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ashan
 */
public class InternalPanel2 extends javax.swing.JPanel {

    int count = 1;
    public static int slidervalue1;
    public static int slidervalue2;
    public String url;
    String selectedphotourl;
    String savedUrl;

    String currentDirectory;
    String selectedImage;
    String fullpathname;
    String[] imageArray;
    String unzipedfilename;

    public InternalPanel2() {
        initComponents();
        //this.setLocationRelativeTo(null);
        this.slidervalue1 = Slider1.getValue();
        this.slidervalue2 = Slider2.getValue();
        valuedisplay1.setText(Integer.toString(slidervalue1));
        valuedisplay2.setText(Integer.toString(slidervalue2));

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

    public InternalPanel2(String path, String selectedfile) {
        initComponents();
        //this.setLocationRelativeTo(null);
        this.currentDirectory = path;
        this.selectedphotourl = selectedfile;
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
        currentDirectory = path;
        selectedImage = selectedfile;
        // now I call the method to load table elements
        getImagenames(currentDirectory);

        // then load the selected image to the original Label calling the method
        if(selectedImage != ""){
            imageLoad(selectedImage);
        }
    }
    ////************ here I make another constructor for save unziped file name to further usage ************/////
    public InternalPanel2(String path, String selectedfile, String unzipedfilename) {
        initComponents();
        //this.setLocationRelativeTo(null);
        this.currentDirectory = path;
        this.selectedphotourl = selectedfile;
        this.unzipedfilename = unzipedfilename;
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
        currentDirectory = path;
        selectedImage = selectedfile;
        // now I call the method to load table elements
        getImagenames(currentDirectory);

        // then load the selected image to the original Label calling the method
        if(selectedImage != ""){
            imageLoad(selectedImage);
        }
    }

    //*********************************** load the table ****************************************//
    public void getImagenames(String dir) {
        String dirpath = dir;
        File file = new File(dirpath);
        String[] files = file.list();
        String[] filteredJPGimageArray = new String[files.length];
        int j = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].contains(".jpg")) {
                filteredJPGimageArray[j] = files[i];
                j++;
            }
        }
        DefaultTableModel model = (DefaultTableModel) Image_LoadTable.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{" Image Name"});
        Object[] row = new Object[1];

        for (Object file1 : filteredJPGimageArray) {
            row[0] = file1;
            model.addRow(row);
        }
    }

    //********************************* Image loading (with resizing to the label)method *************************//
    public void imageLoad(String url) {
        String getURL = url;
        try {
            BufferedImage getimg = ImageIO.read(new File(getURL));
            System.out.println("got the url :" + getURL);
            // read image as bufferedImage. reason is to fit selected image size to Jlabel size
            BufferedImage img = new BufferedImage(274, 274, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(getimg, 0, 0, 274, 274, null);
            g2.dispose();

            //here set image to the label
            ImageIcon II = new ImageIcon(img);
            originalLabel2.setIcon(II);
            // set url into text field
            urlfield.setText(getURL);
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

        jDesktopPaneIn2 = new javax.swing.JDesktopPane();
        urlfield = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnProcess = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        Slider1 = new javax.swing.JSlider();
        Slider2 = new javax.swing.JSlider();
        originalLabel2 = new javax.swing.JLabel();
        edgeLabel2 = new javax.swing.JLabel();
        valuedisplay1 = new javax.swing.JLabel();
        valuedisplay2 = new javax.swing.JLabel();
        btnProcessAll = new javax.swing.JButton();
        btnSelectAll = new javax.swing.JButton();
        btnCancelSelections = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Image_LoadTable = new javax.swing.JTable();

        jDesktopPaneIn2.setBackground(new java.awt.Color(88, 88, 88));
        jDesktopPaneIn2.setPreferredSize(new java.awt.Dimension(1080, 590));

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

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Threshold1");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
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

        originalLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        edgeLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        edgeLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edgeLabel2MouseClicked(evt);
            }
        });

        valuedisplay1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        valuedisplay2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        btnProcessAll.setBackground(new java.awt.Color(204, 204, 204));
        btnProcessAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProcessAll.setText("Process All");
        btnProcessAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessAllActionPerformed(evt);
            }
        });

        btnSelectAll.setBackground(new java.awt.Color(204, 204, 204));
        btnSelectAll.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSelectAll.setText("Select all");
        btnSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectAllActionPerformed(evt);
            }
        });

        btnCancelSelections.setBackground(new java.awt.Color(204, 204, 204));
        btnCancelSelections.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelSelections.setText("Cancel");
        btnCancelSelections.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelSelectionsActionPerformed(evt);
            }
        });

        Image_LoadTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Image Name"
            }
        ));
        Image_LoadTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Image_LoadTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Image_LoadTable);

        jDesktopPaneIn2.setLayer(urlfield, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(btnBrowse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(btnProcess, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(btnSave, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(Slider1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(Slider2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(originalLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(edgeLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(valuedisplay1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(valuedisplay2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(btnProcessAll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(btnSelectAll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(btnCancelSelections, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneIn2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneIn2Layout = new javax.swing.GroupLayout(jDesktopPaneIn2);
        jDesktopPaneIn2.setLayout(jDesktopPaneIn2Layout);
        jDesktopPaneIn2Layout.setHorizontalGroup(
            jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                        .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(urlfield, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                                        .addGap(270, 270, 270)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                                .addGap(274, 274, 274)
                                .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                                            .addGap(110, 110, 110)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                                            .addComponent(Slider2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(valuedisplay2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                                        .addComponent(Slider1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(valuedisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(82, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaneIn2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(originalLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnProcessAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProcess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(55, 55, 55)
                        .addComponent(edgeLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
            .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSelectAll)
                .addGap(27, 27, 27)
                .addComponent(btnCancelSelections)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPaneIn2Layout.setVerticalGroup(
            jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(urlfield, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDesktopPaneIn2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valuedisplay1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Slider1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(valuedisplay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Slider2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(originalLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDesktopPaneIn2Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(btnProcess)
                                    .addGap(35, 35, 35)
                                    .addComponent(btnSave)
                                    .addGap(31, 31, 31)
                                    .addComponent(btnProcessAll))
                                .addComponent(edgeLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPaneIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelSelections)
                    .addComponent(btnSelectAll))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneIn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPaneIn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        // when press the hold button
        // It set the original image to originalLabel and set edge detected image to edge label

        //originalLabel.setIcon(imwrite("F:\\Final Semester\\group project\\coverted.jpg",draw));
        //ImageIcon II = new ImageIcon(getClass().getResource("F:\\Final Semester\\group project\\len_std.jpg"));
        this.slidervalue1 = Slider1.getValue();
        this.slidervalue2 = Slider2.getValue();
        //String url = urlfield.getText();

        //JOptionPane.showMessageDialog(null, "Please select image to process", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        try {
            EdgedetectProcessing er = new EdgedetectProcessing(urlfield.getText(), count, currentDirectory);
            savedUrl = er.process(slidervalue1, slidervalue2);
            System.out.println(slidervalue1 + "  " + slidervalue2);

            //set to original label once we select image as original image to get canny image
            BufferedImage getimg = ImageIO.read(new File(savedUrl));
            // read image as bufferedImage. reason is to fit selected image size to Jlabel size
            BufferedImage img = new BufferedImage(edgeLabel2.getWidth(), edgeLabel2.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(getimg, 0, 0, edgeLabel2.getWidth(), edgeLabel2.getHeight(), null);
            g2.dispose();
            ImageIcon II2 = new ImageIcon(img);
            edgeLabel2.setIcon(II2);
            System.out.println(" changed !!!");
            //count++;

            // get the matrix to write whatever places that user wants !!!!
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(null, "Please select image to process", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnProcessActionPerformed

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

            if (total > 1) {
                //set to original label once we select image as original image to get canny image
                BufferedImage getimg = ImageIO.read(new File(selectedphotourl));
                // read image as bufferedImage. reason is to fit selected image size to Jlabel size
                BufferedImage img = new BufferedImage(originalLabel2.getWidth(), originalLabel2.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = img.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(getimg, 0, 0, originalLabel2.getWidth(), originalLabel2.getHeight(), null);
                g2.dispose();

                //here set image to the label
                ImageIcon II = new ImageIcon(img);
                originalLabel2.setIcon(II);

                // load the table
                System.out.println(currentDirectory);
                getImagenames(currentDirectory);

            } else if (total == 1) {
                jDesktopPaneIn2.removeAll();
                jDesktopPaneIn2.repaint();
                InternalPanel1 panel = new InternalPanel1(currentDirectory, selectedphotourl);
                Dimension screenSize = jDesktopPaneIn2.getSize();
                panel.setSize(screenSize.width, screenSize.height);
                jDesktopPaneIn2.add(panel);
                int width = panel.getWidth();
                int height = panel.getHeight();
                panel.setBounds(((screenSize.width / 2) - (width / 2)), ((screenSize.height / 2) - (height / 2)), width, height);
                panel.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Please select JPG/JPEG image to process", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                urlfield.setText("");
            }
            // tab here for set all images I think now I dont need these code parts
            //            Browse_Image_Table BIT = new Browse_Image_Table();
            //            BIT.getImageName(f.getPath(), fpath.getPath());
            //            BIT.setVisible(true);
            //            BIT.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            /*String url = urlfield.getText();
            ImageIcon II = new ImageIcon(url);
            originalLabel.setIcon(II);
             */

 /*
            .....................
            in the following coding line ::
            using windowlistener I have tried to import the images to the main frame
            that we are going to use for canny edge detection.
            that case when the image is selected in table frame it will be displayed
            in this mainframe original image label.
            .....................
             */
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void urlfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_urlfieldActionPerformed

    private void btnCancelSelectionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSelectionsActionPerformed
        this.slidervalue1 = Slider1.getValue();
        this.slidervalue2 = Slider2.getValue();
        String newurl = urlfield.getText();
        jDesktopPaneIn2.removeAll();
        jDesktopPaneIn2.repaint();
        InternalPanel1 panel = new InternalPanel1(currentDirectory, newurl, slidervalue1, slidervalue2);
        Dimension screenSize = jDesktopPaneIn2.getSize();
        panel.setSize(screenSize.width, screenSize.height);
        jDesktopPaneIn2.add(panel);
        int width = panel.getWidth();
        int height = panel.getHeight();
        panel.setBounds(((screenSize.width / 2) - (width / 2)), ((screenSize.height / 2) - (height / 2)), width, height);
        panel.setVisible(true);
    }//GEN-LAST:event_btnCancelSelectionsActionPerformed

    private void Image_LoadTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Image_LoadTableMouseClicked

        try {
            int index = Image_LoadTable.getSelectedRow();
            TableModel tb = Image_LoadTable.getModel();
            String imagename = tb.getValueAt(index, 0).toString();
            fullpathname = currentDirectory + "/" + imagename;

            urlfield.setText(fullpathname);
            //set to original label once we select image as original image to get canny image
            BufferedImage getimg = ImageIO.read(new File(fullpathname));
            // read image as bufferedImage. reason is to fit selected image size to Jlabel size
            BufferedImage img = new BufferedImage(originalLabel2.getWidth(), originalLabel2.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(getimg, 0, 0, originalLabel2.getWidth(), originalLabel2.getHeight(), null);
            g2.dispose();

            //here set image to the label
            ImageIcon II = new ImageIcon(img);
            originalLabel2.setIcon(II);
        } catch (Exception e) {
            System.out.println(e);
            if (e.equals("java.lang.NullPointerException")) {
                JOptionPane.showMessageDialog(null, " Please select not empty row.", "warning message", WARNING_MESSAGE);
            }
        }
        // get the integers of indices of selected rows to the array.
        int[] length = Image_LoadTable.getSelectedRows();
        // make a String array which is the size of length array.
        imageArray = new String[length.length];
        for (int i = 0; i < length.length; i++) {
            imageArray[i] = currentDirectory + "\\" + (String) Image_LoadTable.getValueAt(length[i], 0);
        }

        for (int i = 0; i < imageArray.length; i++) {
            System.out.println(imageArray[i]);
        }
    }//GEN-LAST:event_Image_LoadTableMouseClicked

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

    private void btnSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectAllActionPerformed
        if (btnSelectAll.getText().equals("Select all")) {
            int end = Image_LoadTable.getRowCount();
            // Use this mode to demonstrate the following examples
            Image_LoadTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            // Needs to be set or rows cannot be selected
            Image_LoadTable.setRowSelectionAllowed(true);
            // Select rows from start to end if start is 0 we change to 1 or leave it (used to preserve coloums headers)
            Image_LoadTable.setRowSelectionInterval(0, end - 1);

            // get the integers of indices of selected rows to the array.
            int[] length = Image_LoadTable.getSelectedRows();
            // make a String array which is the size of length array.
            imageArray = new String[length.length];
            for (int i = 0; i < length.length; i++) {
                imageArray[i] = currentDirectory + "\\" + (String) Image_LoadTable.getValueAt(length[i], 0);
            }

            for (int i = 0; i < imageArray.length; i++) {
                System.out.println(imageArray[i]);
            }
            //btnSelectAll.setText("Unselect all");
        }
//        if (btnSelectAll.getText().equals("Unselect all")){
//            Image_LoadTable.getSelectionModel().clearSelection();
//        }


    }//GEN-LAST:event_btnSelectAllActionPerformed

    private void btnProcessAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessAllActionPerformed
        //when process all we should pass the imageArray to the processAll class file to execute them.
        // if no more than one selected this action not to be done. Just it processess using current interface.
        if (imageArray.length <= 0) {
            // have to write somethiing
            JOptionPane.showMessageDialog(null, "Choose two or more images.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

        } else if (imageArray.length == 1) {
            // have to write something
            JOptionPane.showMessageDialog(null, "Choose two or more images.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        } else {
            this.slidervalue1 = Slider1.getValue();
            this.slidervalue2 = Slider2.getValue();

            jDesktopPaneIn2.removeAll();
            jDesktopPaneIn2.repaint();
            ProcessAll newProcess = new ProcessAll(imageArray, slidervalue1, slidervalue2, currentDirectory, selectedphotourl,unzipedfilename);
            Dimension screenSize = jDesktopPaneIn2.getSize();
            newProcess.setSize(screenSize.width, screenSize.height);
            jDesktopPaneIn2.add(newProcess);
            int width = newProcess.getWidth();
            int height = newProcess.getHeight();
            newProcess.setBounds(((screenSize.width / 2) - (width / 2)), ((screenSize.height / 2) - (height / 2)), width, height);
            newProcess.setVisible(true);
        }
    }//GEN-LAST:event_btnProcessAllActionPerformed

    private void edgeLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edgeLabel2MouseClicked
        // Here load the resized class
        Resize RZ = new Resize(savedUrl);
        RZ.setVisible(true);
        
    }//GEN-LAST:event_edgeLabel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Image_LoadTable;
    private javax.swing.JSlider Slider1;
    private javax.swing.JSlider Slider2;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnCancelSelections;
    private javax.swing.JButton btnProcess;
    private javax.swing.JButton btnProcessAll;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSelectAll;
    private javax.swing.JLabel edgeLabel2;
    private javax.swing.JDesktopPane jDesktopPaneIn2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel originalLabel2;
    private javax.swing.JTextField urlfield;
    private javax.swing.JLabel valuedisplay1;
    private javax.swing.JLabel valuedisplay2;
    // End of variables declaration//GEN-END:variables
}
