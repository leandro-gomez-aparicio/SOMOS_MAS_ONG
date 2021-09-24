package com.somosmas.app.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.somosmas.app.config.AmazonS3Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Map;
import java.util.UUID;

@Service
public class AmazonServiceImpl {

    private static final String DEFAULT_FILE_NAME = "default-image-name";
    private static final String FILE_NAME_PATTERN = "{0}-{1}";
    private static final String DESTINATION_URL = "destination-url";

    @Autowired
    private AmazonS3 s3client;

    @Autowired
    private AmazonS3Config amazonConfig;

    public Map<String, String> uploadFile(MultipartFile multipartFile) throws IOException {
        String fileUrl = uploadFile(multipartFile.getContentType(), multipartFile.getOriginalFilename(), multipartFile.getInputStream());
        return Map.of(DESTINATION_URL, fileUrl);
    }

    private String getFileNameOrDefault(String fileName) {
        UUID uuid = UUID.randomUUID();
        if (fileName == null) {
            fileName = DEFAULT_FILE_NAME;
        }
        return MessageFormat.format(FILE_NAME_PATTERN, uuid, fileName);
    }

    private void uploadFileInBucket(String fileName, InputStream inputStream, String contentType) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        s3client.putObject(new PutObjectRequest(amazonConfig.getBucketName(), fileName, inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(String contentType, String fileName, InputStream inputStream) {
        String fileNameOrDefault = getFileNameOrDefault(fileName);
        uploadFileInBucket(fileNameOrDefault, inputStream, contentType);
        return s3client.getUrl(amazonConfig.getBucketName(), fileNameOrDefault).toString();
    }

}