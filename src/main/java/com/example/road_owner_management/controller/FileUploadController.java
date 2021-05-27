package com.example.road_owner_management.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
public class FileUploadController {



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

        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        //blob not used, but might be if its needed
        Blob blob = new SerialBlob(file.getBytes());

        fos.write(file.getBytes());
        fos.close();

        //System.out.println("file uploaded" + file.getOriginalFilename() + "blob: " + blob);
        return new ResponseEntity<Object>("The file has been uploaded", HttpStatus.OK);
    }


    //method 3

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file ) throws IOException, SQLException {
        byte[] fileAsBytes = file.getBytes();
        Blob fileAsBlob = new SerialBlob(fileAsBytes);

        System.out.println("blob: "+fileAsBlob);
        return "blob: "+fileAsBlob;
    }


}
