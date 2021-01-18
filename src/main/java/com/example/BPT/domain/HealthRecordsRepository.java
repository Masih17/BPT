package com.example.BPT.domain;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HealthRecordsRepository extends CrudRepository<HealthRecords, Long> {

	List<HealthRecords> findById(long Id);

	List<HealthRecords> findByOrderByDateDesc();

}
