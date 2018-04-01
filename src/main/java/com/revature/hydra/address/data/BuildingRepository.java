package com.revature.hydra.address.data;

import org.springframework.stereotype.Repository;

import com.revature.hydra.address.beans.Building;

@Repository
public interface BuildingRepository extends ActivatableObjectRepository<Building, Integer> {

}
