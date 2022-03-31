package com.mirbozorgi.core.repository.docuemnt;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public interface CustomMongoRepository {

  <T> T add(T objectToSave);

  <T> T fetch(Query query, Class<T> classType);

  <T> List<T> fetch(
      Class<T> classType,
      String[] fields,
      Sort sort,
      Integer limit,
      CriteriaDefinition... criteria
  );

  <T> List<T> fetch(
      Class<T> classType,
      String[] fields,
      CriteriaDefinition... criteria
  );


  <T> T fetchFirst(
      Class<T> classType,
      String[] fields,
      Sort sort,
      CriteriaDefinition... criteria
  );


  <T> T fetchFirst(
      Class<T> classType,
      String[] fields,
      CriteriaDefinition... criteria
  );

  <T> T fetchFirst(
      Class<T> classType,
      String id
  );

  <T> T fetchFirst(
      Class<T> classType,
      ObjectId id
  );

  <T> UpdateResult update(
      Class<T> classType,
      Update update,
      CriteriaDefinition... criteria
  );

  <T> UpdateResult update(
      Class<T> classType,
      Update update,
      String id
  );

  <T> UpdateResult update(
      Class<T> classType,
      Update update,
      ObjectId id
  );

  <T> UpdateResult updateAll(
      Class<T> classType,
      Update update,
      CriteriaDefinition... criteria
  );

  <T> void delete(T objectToDelete);


  Document fetchDocumentWithOutClassType(BasicDBObject whereQuery, String collectionName);

  MongoCollection<Document> findCollection(String collectionName);


  void createIndex(String index, String collectionName);

}
