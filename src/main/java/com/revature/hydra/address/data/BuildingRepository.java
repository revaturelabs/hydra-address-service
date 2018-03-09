package com.revature.hydra.address.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.Building;

@Repository
public interface BuildingRepository extends ActivatableObjectRepository<Building, Integer> {

}
