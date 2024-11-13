package br.com.project.backend.uni.modal.services.utility;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MongoDBServiceDelete {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void deleteImageMongo(String photoId){
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("_id").is(new ObjectId(photoId)));
        mongoTemplate.remove(query1, "fs.files");
        Query query2 = new Query();
        query2.addCriteria(Criteria.where("files_id").is(new ObjectId(photoId)));
        mongoTemplate.remove(query2,"fs.chunks");
    }
}
