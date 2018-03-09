package com.revature.hydra.address.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.Location;

@Repository
public interface LocationRepository extends ActivatableObjectRepository<Location, Integer> {

}
