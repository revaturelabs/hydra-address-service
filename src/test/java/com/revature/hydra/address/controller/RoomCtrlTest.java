package com.revature.hydra.address.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.hydra.address.AddressRepositoryServiceApplication;
import com.revature.hydra.address.beans.Room;
import com.revature.hydra.address.service.ActivatableObjectDaoService;
import com.revature.hydra.address.util.JsonMaker;

/**
 * Created by gdittric on 7/13/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AddressRepositoryServiceApplication.class)
@AutoConfigureMockMvc
public class RoomCtrlTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ActivatableObjectDaoService<Room, Integer> roomService;

	// @MockBean
	// CustomSecurity customSecurity;

	private Room testRoom = null;

	private JsonMaker jsonMaker = new JsonMaker();

	@Before
	public void setUp() {
		// List<Unavailable> unavailablities = new ArrayList<Unavailable>();
		testRoom = new Room(0, "aRoom", 3/* , unavailablities */, false);

		// given(customSecurity.hasPermission(any(),any(),any())).willReturn(true);
	}

	@After
	public void tearDown() {
		testRoom = null;
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void createRoom() throws Exception {
		given(roomService.saveItem(any(Room.class))).willReturn(testRoom);
		mvc.perform(post("/api/v2/room")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON).content(jsonMaker.toJsonString(testRoom)))
				.andExpect(status().isOk());
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void createRoomWithInvalidDTO() throws Exception {
		given(roomService.saveItem(any(Room.class))).willReturn(null);
		mvc.perform(post("/api/v2/room")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON).content(jsonMaker.toJsonString(testRoom)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void retrieveRoom() throws Exception {
		given(roomService.getOneItem(anyInt())).willReturn(testRoom);
		mvc.perform(get("/api/v2/room/42"))
				// .with(csrf().asHeader()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.roomName", is(testRoom.getRoomName())));
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void retrieveInvalidRoom() throws Exception {
		given(roomService.getOneItem(anyInt())).willReturn(null);
		mvc.perform(get("/api/v2/room/42"))
				// .with(csrf().asHeader()))
				.andExpect(status().isNotFound());
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void updateRoom() throws Exception {
		given(roomService.saveItem(any(Room.class))).willReturn(testRoom);
		mvc.perform(put("/api/v2/room")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON).content(jsonMaker.toJsonString(testRoom)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.roomName", is(testRoom.getRoomName())));
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void updateInvalidRoom() throws Exception {
		given(roomService.saveItem(any(Room.class))).willReturn(null);
		mvc.perform(put("/api/v2/room")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON).content(jsonMaker.toJsonString(testRoom)))
				.andExpect(status().isNotModified());
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void deleteRoom() throws Exception {
		doNothing().when(roomService).deleteItem(anyInt());
		mvc.perform(delete("/api/v2/room/42"))
				// .with(csrf().asHeader()))
				.andExpect(status().isOk());
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void retrieveAllRooms() throws Exception {
		List<Room> rooms = new ArrayList<Room>();
		rooms.add(testRoom);
		given(roomService.getAllItems()).willReturn(rooms);
		mvc.perform(get("/api/v2/room"))
				// .with(csrf().asHeader()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(rooms.size())));
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void retrieveAllRoomsWithEmptySet() throws Exception {
		List<Room> rooms = new ArrayList<Room>();
		given(roomService.getAllItems()).willReturn(rooms);
		mvc.perform(get("/api/v2/room"))
				// .with(csrf().asHeader()))
				.andExpect(status().isNotFound());
	}

	@Test
	// @WithMockUser(roles = "admin")
	public void retrieveAllRoomsWithFailure() throws Exception {
		given(roomService.getAllItems()).willReturn(null);
		mvc.perform(get("/api/v2/room"))
				// .with(csrf().asHeader()))
				.andExpect(status().isNotFound());
	}

}