package com.revature.hydra.address.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.Room;

@Repository
public interface RoomRepository extends ActivatableObjectRepository<Room, Integer> {

}
