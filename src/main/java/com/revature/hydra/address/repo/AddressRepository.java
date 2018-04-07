package com.revature.hydra.address.repo;

import com.revature.hydra.address.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
}