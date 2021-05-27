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

    //method 1

    @PostMapping("/uploadFiles")
    public ResponseEntity<Object> fileUpload(@RequestParam("file")MultipartFile file) throws IOException, SQLException {
        System.out.println("test den kommer igennem");

        String testDir = "C:\\Users\\Anker-PC\\Documents\\test\\";
        String dir = "../resources/static/files/public/";

        File myFile = new File(testDir+file.getOriginalFilename());
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        Blob blob = new SerialBlob(file.getBytes());

        fos.write(file.getBytes());
        fos.close();

        System.out.println("file uploaded" + file.getOriginalFilename() + "blob: " + blob);
        return new ResponseEntity<Object>("The file has been uploaded", HttpStatus.OK);
    }

    //method 2

    @PostMapping(value = "/example1/upload/file",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> uploadSingleFile(MultipartFile file) throws IOException, SQLException {
        System.out.println("test det virker");
        File myFile = new File("../resources/static/files/public/"+file.getOriginalFilename());
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        Blob blob = new SerialBlob(file.getBytes());

        fos.write(file.getBytes());
        fos.close();
        return ResponseEntity.ok("Success");
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
