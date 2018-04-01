package com.revature.hydra.address.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.hydra.address.beans.Building;

@Transactional
@Service
public class BuildingDaoService extends ActivatableObjectDaoService<Building, Integer> {

}
