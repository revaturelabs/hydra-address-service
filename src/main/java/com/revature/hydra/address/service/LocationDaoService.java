package com.revature.hydra.address.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.hydra.address.beans.Location;

@Transactional
@Service
public class LocationDaoService extends ActivatableObjectDaoService<Location, Integer> {

}
