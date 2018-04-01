package com.revature.hydra.address.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.hydra.address.beans.Room;

@Transactional
@Service
public class RoomDaoService extends ActivatableObjectDaoService<Room, Integer> {

}
