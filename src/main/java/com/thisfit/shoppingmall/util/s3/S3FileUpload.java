package com.thisfit.shoppingmall.util.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class S3FileUpload {

    // 버킷 이름 동적 할당
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 버킷 주소 동적 할당
    @Value("${cloud.aws.s3.bucket.url}")
    private String bucketUrl;

    private final AmazonS3Client amazonS3Client;

    public String imgUpLoad(MultipartFile multipartFile, String dirName) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();

        // 확장자를 찾기 위한 코드
        String ext = FilenameUtils.getExtension(originalFileName);

        // 파일이름 암호화
        String saveFileName = "upload_img_" + UUID.randomUUID() + ext;

        // System.getProperty -> 시스템 환경에 관한 정보를 얻을 수 있음 (user.dir = 현재 작업 디렉토리를 의미함)
        File file = new File(System.getProperty("user.dir") + saveFileName);

        // 파일 객체 생성
        multipartFile.transferTo(file);

        // S3 파일 업로드
        uploadOnS3(dirName, saveFileName, file);

        // 주소 할당
        String url = bucketUrl + "/" + dirName + saveFileName;

        // 파일 삭제
        file.delete();

        return url;
    }

    // S3 파일 업로드
    private void uploadOnS3(String dirName, String saveFileName, File file) {
        // AWS S3 전송 객체 생성
        TransferManager transferManager = new TransferManager(this.amazonS3Client);

        // 요청 객체 생성
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, dirName + saveFileName, file);

        // 업로드 시도
        Upload upload = transferManager.upload(putObjectRequest);

        try {
            upload.waitForCompletion();
        } catch (AmazonClientException amazonClientException) {
            log.error(amazonClientException.getMessage());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    // S3 파일 삭제
    public void imgDelete(String deleteFileName, String dirName) {
        deleteFileName = deleteFileName.substring(deleteFileName.lastIndexOf('/') + 1);

        try {
            // Delete 객체 생성
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, dirName + deleteFileName);

            // Delete
            amazonS3Client.deleteObject(deleteObjectRequest);

        } catch (AmazonClientException amazonClientException) {
            log.error(amazonClientException.getMessage());
        }
    }

}
