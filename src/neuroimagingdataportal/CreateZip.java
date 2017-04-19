///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package neuroimagingdataportal;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//import javax.swing.JFileChooser;
//
///**
// *
// * @author ashan
// */
//public class CreateZip {
//
//    public void ZipCreateExample(ArrayList<File> files, String path, String Zipname) throws Exception {
//        String savepath = path + "/" + "abcd" + ".zip";
//        FileOutputStream fos = new FileOutputStream(savepath);
//        ZipOutputStream zos = new ZipOutputStream(fos);
//        int byteread = 0;
//        byte[] b = new byte[2048];
//
//        for (int i = 0; i < files.size(); i++) {
//            FileInputStream in = new FileInputStream(files.get(i));
//            ZipEntry zi = new ZipEntry(files.get(i).getName());
//            zos.putNextEntry(zi);
//            while ((byteread = in.read(b)) > 0) {
//                System.out.println(files.get(i));
//                zos.write(b, 0, byteread);
//            }
//            zos.closeEntry();
//            in.close();
//        }
//    }
//
//}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZip {

    List<String> fileList;
    private String OUTPUT_ZIP_FILE = "";
    private String SOURCE_FOLDER = "";
    private String zipfilename;

    CreateZip(String path, String zipfilepath) {
        fileList = new ArrayList<String>();
        this.SOURCE_FOLDER = path;
        this.zipfilename = zipfilepath;
        this.OUTPUT_ZIP_FILE = path + "\\" + zipfilepath + ".zip";
        generateFileList(new File(SOURCE_FOLDER));
        zipIt(OUTPUT_ZIP_FILE);
        removeAfterCreatingZip();
 
    }

//    public static void main( String[] args )
//    {
//    	CreateZip appZip = new CreateZip();
//    	appZip.generateFileList(new File(SOURCE_FOLDER));
//    	appZip.zipIt(OUTPUT_ZIP_FILE);
//    }
    /**
     * Zip it
     *
     * @param zipFile output ZIP file location
     */
    public void zipIt(String zipFile) {

        byte[] buffer = new byte[1024];

        try {

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFile);

            for (String file : this.fileList) {

                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(file);
                zos.putNextEntry(ze);

                FileInputStream in
                        = new FileInputStream(SOURCE_FOLDER + File.separator + file);

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                in.close();
            }

            zos.closeEntry();
            //remember close it
            zos.close();

            System.out.println("Done");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Traverse a directory and get all files, and add the file into fileList
     *
     * @param node file or directory
     */
    public void generateFileList(File node) {

        //add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                generateFileList(new File(node, filename));
            }
        }

    }

    /**
     * Format the file path for zip
     *
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file) {
        return file.substring(SOURCE_FOLDER.length() + 1, file.length());
    }

    public void removeAfterCreatingZip() {
        File dir = new File(SOURCE_FOLDER);
        File zipfile = new File(OUTPUT_ZIP_FILE);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0 && files.equals(zipfile) != true) {
                for (File afile : files) {
                    File filetodelete = new File(afile.getAbsolutePath());
                    if(!afile.getAbsolutePath().equals(OUTPUT_ZIP_FILE)){
                        filetodelete.delete();
                    }
                }
            }
        }
    }

}
