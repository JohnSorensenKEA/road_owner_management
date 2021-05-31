package com.example.road_owner_management.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileArchiveRestController {

    //method 1 the one we use

    @PostMapping(value= "/uploadFiles")
    public ResponseEntity<Object> fileUpload(@RequestParam("file")MultipartFile file) throws IOException, SQLException {

        //folder where files get stored
        String path = "filearchive";
        File directory = new File(path);
        if(!directory.exists()){
            if(directory.mkdir()){
                System.out.println("folder has been made");
            } else{
                System.out.println("folder didnt get made");
            }
        } else{
            System.out.println("folder already exists");
        }

        //file handling

        File myFile = new File(path+"/"+file.getOriginalFilename());

        FileOutputStream fos = new FileOutputStream(myFile);

        //blob not used, but might be if its needed
        Blob blob = new SerialBlob(file.getBytes());

        fos.write(file.getBytes());
        fos.close();

        //System.out.println("file uploaded" + file.getOriginalFilename() + "blob: " + blob);
        return new ResponseEntity<>("The file has been uploaded", HttpStatus.OK);
    }

    //method 2 blob if we would store in DB

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file ) throws IOException, SQLException {
        byte[] fileAsBytes = file.getBytes();
        Blob fileAsBlob = new SerialBlob(fileAsBytes);

        System.out.println("blob: "+fileAsBlob);
        return "blob: "+fileAsBlob;
    }

    //Get all files
    @GetMapping("/getAllFiles")
    public List<String> getAllFiles(){
        File folder = new File("filearchive");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> fileNames = new ArrayList<>();


        for (File listOfFile : listOfFiles) {
            fileNames.add(listOfFile.getName());
        }



        System.out.println(fileNames);
        return fileNames;
    }

    //Download file

    String folderPath="filearchive/";

    @RequestMapping("/file/{fileName}")
    @ResponseBody
    public void show(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=" +fileName);
        response.setHeader("Content-Transfer-Encoding", "binary");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(folderPath+fileName);
            int len;
            byte[] buf = new byte[1024];
            while((len = fis.read(buf)) > 0) {
                bos.write(buf,0,len);
            }
            bos.close();
            response.flushBuffer();
        }
        catch(IOException e) {
            e.printStackTrace();

        }
    }


}
