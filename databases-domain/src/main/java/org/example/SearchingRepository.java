package org.example;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchingRepository extends CrudRepository<SearchingEntity, Long> {
}
