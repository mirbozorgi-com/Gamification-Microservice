package com.mirbozorgi.core.repository.docuemnt.impl;

import com.mirbozorgi.core.repository.docuemnt.CustomMongoRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepositoryImpl implements CustomMongoRepository {

  @Autowired
  private MongoTemplate mongoTemplate;


  public <T> T add(T objectToSave) {
    return mongoTemplate.insert(objectToSave);
  }

  @Override
  public <T> T fetch(Query query, Class<T> classType) {
    return mongoTemplate.findOne(query, classType);
  }

  @Override
  public <T> List<T> fetch(
      Class<T> classType,
      String[] fields,
      Sort sort,
      Integer limit,
      CriteriaDefinition... criteria) {
    Query query = new Query();

    if (criteria != null) {
      for (CriteriaDefinition cd : criteria) {
        query.addCriteria(cd);
      }
    }

    if (fields != null) {
      for (String field : fields) {
        query.fields().include(field);
      }
    }

    if (sort != null) {
      query.with(sort);
    }

    if (limit != null) {
      query.limit(limit);
    }

    return mongoTemplate.find(query, classType);
  }

  @Override
  public <T> List<T> fetch(
      Class<T> classType,
      String[] fields,
      CriteriaDefinition... criteria
  ) {
    return fetch(classType, fields, null, null, criteria);
  }

  @Override
  public <T> T fetchFirst(
      Class<T> classType,
      String[] fields,
      Sort sort,
      CriteriaDefinition... criteria
  ) {

    List<T> result = fetch(classType, fields, criteria);

    if (result.isEmpty()) {
      return null;
    }

    return result.get(0);
  }

  @Override
  public <T> T fetchFirst(
      Class<T> classType,
      String[] fields,
      CriteriaDefinition... criteria
  ) {
    return fetchFirst(classType, fields, null, criteria);
  }

  @Override
  public <T> T fetchFirst(Class<T> classType, String id) {
    return fetchFirst(classType, null, Criteria.where("id").is(id));
  }

  @Override
  public <T> T fetchFirst(Class<T> classType, ObjectId id) {
    return fetchFirst(classType, null, Criteria.where("id").is(id));
  }

  @Override
  public <T> UpdateResult update(
      Class<T> classType,
      Update update,
      CriteriaDefinition... criteria
  ) {
    Query query = new Query();

    for (CriteriaDefinition cd : criteria) {
      query.addCriteria(cd);
    }

    return mongoTemplate.updateFirst(query, update, classType);
  }

  @Override
  public <T> UpdateResult update(Class<T> classType, Update update, String id) {
    return update(classType, update, Criteria.where("id").is(id));
  }

  @Override
  public <T> UpdateResult update(Class<T> classType, Update update, ObjectId id) {
    return update(classType, update, Criteria.where("id").is(id));
  }

  @Override
  public <T> UpdateResult updateAll(
      Class<T> classType,
      Update update,
      CriteriaDefinition... criteria
  ) {
    Query query = new Query();

    for (CriteriaDefinition cd : criteria) {
      query.addCriteria(cd);
    }

    return mongoTemplate.updateMulti(query, update, classType);
  }

  @Override
  public <T> void delete(T objectToDelete) {
    mongoTemplate.remove(objectToDelete);
  }

  @Override
  public Document fetchDocumentWithOutClassType(BasicDBObject whereQuery, String collectionName) {
    return mongoTemplate.getDb().getCollection(collectionName).find(whereQuery).iterator()
        .next();

  }

  @Override
  public MongoCollection<Document> findCollection(String collectionName) {
    return mongoTemplate.getDb().getCollection(collectionName);
  }

  @Override
  public void createIndex(String index, String collectionName) {
    IndexOptions indexOptions = new IndexOptions().name(index);
    MongoCollection<Document> collection = mongoTemplate.getDb().getCollection(collectionName);
    Bson keys = new Document(index, 1);
    collection.createIndex(keys, indexOptions);

  }


}
