/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuroimagingdataportal;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import javax.imageio.ImageIO;
import org.opencv.core.*;
import org.opencv.core.CvType;
import static org.opencv.core.CvType.CV_32FC1;
import static org.opencv.core.CvType.CV_8U;
import org.opencv.core.Mat;
//import org.opencv.imgcodecs.Imgcodecs;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.*;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.Canny;
import static org.opencv.imgproc.Imgproc.*;

/**
 *
 * @author ashan
 */
public class EdgedetectProcessing {

    public String url;
    public int threshold1;
    public int threshold2;
    public int count;
    Mat gray, edge,draw;
    public String saveUrl;
    String currentDirectory;

    public EdgedetectProcessing(String textfield, int count,String path) {
        this.url = textfield;
        this.count = count;
        this.currentDirectory = path;

        System.out.println("url is " + this.url);

    }

    public String process(int value1, int value2) {

        this.threshold1 = value1;
        this.threshold2 = value2;
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("m = " + m.dump());

        Mat src1;

        //if ("null".equals(this.url)) {
        //    JOptionPane.showMessageDialog(null, "Please select image to process", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        //    return null;
        //} else {
        src1 = imread(url, CV_LOAD_IMAGE_COLOR);
        System.out.println(" read the file" + url);

        // Mat gray, edge, draw;
        gray = new Mat();
        cvtColor(src1, gray, COLOR_BGR2GRAY);

        edge = new Mat();
        draw = new Mat();
        /*
         void Canny(InputArray image, OutputArray edges, double threshold1, double threshold2, int apertureSize=3, bool L2gradient=false )
         Parameters:
         image – single-channel 8-bit input image.
         edges – output edge map; it has the same size and type as image .
         threshold1 – first threshold for the hysteresis procedure.
         threshold2 – second threshold for the hysteresis procedure.
         apertureSize – aperture size for the Sobel() operator.
         L2gradient – a flag, indicating whether a more accurate norm. 
         */

        Canny(gray, edge, threshold1, threshold2, 3, false);
        edge.convertTo(draw, CV_8U);
        
        // write draw mat to the bufferedImage as I think......
        //BufferedImage drawImage = ImageIO.read( (Image) draw);
        
        Mat src2;

        saveUrl = currentDirectory+ "\\" + count + "converted.jpg";

        boolean imwrite;
        
        imwrite = imwrite(saveUrl, draw);
        
        System.out.println(" write the image to given file path !!!");

        return saveUrl;

        //}
    }

    public Mat writecannyedge() {
//        boolean imwrite;
//        imwrite = imwrite(saveUrl, draw);
//
//        System.out.println(" write the image to given file path !!!");
//
////        //remove the temporary saved image :)
////        try {
////            Files.delete(this.saveUrl);
////        } catch (IOException x) {
////            // File permission problems are caught here.
////            System.err.println(x);
////        }
        return draw;
    }
    
    public Mat returnedge(){
        return edge;
    }
}
