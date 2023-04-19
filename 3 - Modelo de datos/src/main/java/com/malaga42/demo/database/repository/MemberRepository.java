package com.malaga42.demo.database.repository;


import com.malaga42.demo.database.entity.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, ObjectId> {

    Member findByName(String name);

}




