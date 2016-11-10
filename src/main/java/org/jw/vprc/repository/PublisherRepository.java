package org.jw.vprc.repository;

import org.jw.vprc.domain.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {
    Publisher findByPublisherId(String publisherId);

    List<Publisher> findBySurname(String surname);
    List<Publisher> findByFirstName(String surname);

    List<Publisher> deleteByPublisherId(String publisherId);
}
