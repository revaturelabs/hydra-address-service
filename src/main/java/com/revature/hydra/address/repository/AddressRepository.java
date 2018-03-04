package com.revature.hydra.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.hydra.address.beans.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	Address findByAddressId(Integer addressId);

}
