package com.awssdk.awssdk;





import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;

@Controller
public class FileUploadController {

    @GetMapping("/api/upload")
    @ResponseBody
    public String uploadedData() {
        return "DONE";
    }

    @PostMapping("/api/upload")
    @ResponseBody
    public String uploadData(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        File uploadedFile = new File(file.getOriginalFilename());

//        uploadFilesToS3(uploadedFile);

        try (OutputStream os = new FileOutputStream(uploadedFile)) {

            os.write((file.getBytes()));
            uploadFilesToS3(uploadedFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        Region region = Region.US_EAST_2;
//        S3Client s3 = S3Client.builder()
//                .region(region)
//                .build();
//
//        s3.putObject(.bu)


        return "DONE";
    }

    private static final String REGION = Regions.US_EAST_2.getName();
    private static final String BUCKET_NAME = "fileupload-alistor";
    private static final String DESTINATION_FOLDER = "example/path/to/folder/";

    public boolean uploadFilesToS3(File uploadedFile) {

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIA4LBH56SMVBTARMFR",
                "Y36mdcFXoaqoUae3VNcbMWAnCyGyyoshmfEWFqKK"
        );


        AmazonS3 s3Client =  AmazonS3ClientBuilder
                .standard()
                .withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        //Concatenate the folder and file name to get the full destination path
        String destinationPath = DESTINATION_FOLDER + uploadedFile;

        //Create a PutObjectRequest
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, destinationPath, uploadedFile);
        putObjectRequest.withCannedAcl(CannedAccessControlList.PublicReadWrite);

        //Perform the upload, and assign the returned result object for further processing
         s3Client.putObject(putObjectRequest);

        return true;
    }


}
