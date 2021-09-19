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

    private static final String EMPTY_FILE_URL = "";
    private static final String DEFAULT_FILE_NAME = "default-image-name";
    private static final String FILE_NAME_PATTERN = "{0}-{1}";

    @Autowired
    private AmazonS3 s3client;

    @Autowired
    private AmazonS3Config amazonConfig;

    private String generateFileName(MultipartFile multiPart) {
        UUID uuid = UUID.randomUUID();
        String fileName = multiPart.getOriginalFilename();
        if (fileName == null) {
            fileName = DEFAULT_FILE_NAME;
        }
        return MessageFormat.format(FILE_NAME_PATTERN, uuid, fileName);
    }

    private void uploadFileTos3bucket(String fileName, InputStream file, String contentType) {
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentType(contentType);
        s3client.putObject(new PutObjectRequest(amazonConfig.getBucketName(), fileName, file, metaData)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public Map<String, String> uploadFile(MultipartFile multipartFile) throws IOException {
        String fileUrl = EMPTY_FILE_URL;

        String fileName = generateFileName(multipartFile);
        String contentType = multipartFile.getContentType();

        uploadFileTos3bucket(fileName, multipartFile.getInputStream(), contentType);
        fileUrl = s3client.getUrl(amazonConfig.getBucketName(), fileName).toString();
        return Map.of("destination-url", fileUrl);
    }

}
