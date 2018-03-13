package com.revature.hydra.address.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	Address findByAddressId(Integer addressId);

}
