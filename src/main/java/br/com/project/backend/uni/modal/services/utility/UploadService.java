package br.com.project.backend.uni.modal.services.utility;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class UploadService {

    @Autowired
    private GridFSBucket gridFSBucket;

    public String savePhoto(MultipartFile multipartFile){

        try (InputStream inputStream = multipartFile.getInputStream()){

            GridFSUploadOptions gridFSUploadOptions = new GridFSUploadOptions();
            gridFSUploadOptions
                    .chunkSizeBytes(358400)
                    .metadata(new org.bson.Document("type", multipartFile.getContentType()));

            ObjectId fileId = gridFSBucket.uploadFromStream(multipartFile.getOriginalFilename(), inputStream, gridFSUploadOptions);
            return fileId.toHexString();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
