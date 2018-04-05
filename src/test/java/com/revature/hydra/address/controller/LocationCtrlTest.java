package com.revature.hydra.address.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.revature.hydra.address.beans.Location;
import com.revature.hydra.address.AddressRepositoryServiceApplication;
import com.revature.hydra.address.service.ActivatableObjectDaoService;
import com.revature.hydra.address.transfer.LocationDTO;
import com.revature.hydra.address.util.JsonMaker;

/**
 * Created by roger on 7/14/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AddressRepositoryServiceApplication.class)
@AutoConfigureMockMvc
public class LocationCtrlTest {

	private LocationDTO locationDTO;

	private Location locationTest;

	private JsonMaker jsonMaker = new JsonMaker();

	@Autowired
	private MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	@MockBean
	ActivatableObjectDaoService<Location, Integer> locationService;

	// @MockBean
	// CustomSecurity customSecurity;

	@Before
	public void setUp() throws Exception {
		locationDTO = new LocationDTO();
		locationDTO.setID(1);
		locationDTO.setName("Test");
		locationDTO.setCity("Test");
		locationDTO.setState("Test");
		locationDTO.setBuildings(new ArrayList<>());
		locationDTO.setActive(true);

		locationTest = new Location(locationDTO.getID(), locationDTO.getName(), locationDTO.getCity(),
				locationDTO.getState(), locationDTO.getBuildings(), locationDTO.getActive());
		// given(customSecurity.hasPermission(any(),any(),any())).willReturn(true);

	}

	@After
	public void tearDown() throws Exception {
		locationDTO = null;
		locationTest = null;
	}

	@Test
	// @WithMockUser
	public void createLocationTest() throws Exception {
		given(locationService.saveItem(any(Location.class))).willReturn(locationTest);
		mvc.perform(post("/location").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonMaker.toJsonString(locationTest)))
				// .with(csrf().asHeader())
				.andExpect(status().isOk());
	}

	@Test
	// @WithMockUser
	public void createLocationWithEmptyDTOTest() throws Exception {
		given(locationService.saveItem(any(Location.class))).willReturn(null);
		mvc.perform(post("/location")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMaker.toJsonString(locationTest)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	// @WithMockUser
	public void createLocationReturnNullTest() throws Exception {
		given(locationService.saveItem(any(Location.class))).willReturn(null);
		mvc.perform(post("/location")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
	}

	@Test
	// @WithMockUser
	public void retrieveLocationTest() throws Exception {
		given(locationService.getOneItem(any(Integer.class))).willReturn(locationTest);
		mvc.perform(get("/location/1")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(locationTest.getName())))
				.andExpect(jsonPath("$.city", is(locationTest.getCity())))
				.andExpect(jsonPath("$.state", is(locationTest.getState())));
	}

	@Test
	// @WithOAuth2Authentication
	public void retrieveLocationWithBadIdTest() throws Exception {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				// .apply(springSecurity())
				.build();

		given(locationService.getOneItem(any(Integer.class))).willReturn(null);
		mvc.perform(get("/location/1")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());

	}

	@Test
	// @WithMockUser()
	public void updateLocationTest() throws Exception {
		given(locationService.saveItem(any(Location.class))).willReturn(locationTest);
		mvc.perform(put("/location")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMaker.toJsonString(locationTest)))
				.andExpect(status().isOk());
	}

	@Test
	// @WithMockUser
	public void updateLocationWithEmptyDTOTest() throws Exception {
		locationTest = new Location();
		given(locationService.saveItem(any(Location.class))).willReturn(null);
		mvc.perform(put("/location")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMaker.toJsonString(locationTest)))
				.andExpect(status().isNotModified());
	}

	@Test
	// @WithMockUser
	public void updateLocationReturnNullTest() throws Exception {
		locationTest = new Location();
		given(locationService.saveItem(any(Location.class))).willReturn(null);
		mvc.perform(put("/location")
				// .with(csrf().asHeader())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content("")).andExpect(status().isBadRequest());
	}

	@Test
	// @WithMockUser
	public void deleteLocationTest() throws Exception {
		doNothing().when(locationService).deleteItem(any(Integer.class));
		mvc.perform(delete("/location/1"))
				// .with(csrf().asHeader())
				.andExpect(status().isOk());
	}

	@Test
	// @WithMockUser
	public void retrieveAllLocationsTest() throws Exception {
		List<Location> locations = new ArrayList<>();
		locations.add(locationTest);
		given(locationService.getAllItems()).willReturn(locations);
		mvc.perform(get("/location"))
				// .with(csrf().asHeader()))
				.andExpect(status().isOk());
	}

	@Test
	// @WithMockUser
	public void retrieveAllLocationsWithEmptyListTest() throws Exception {
		List<Location> locations = new ArrayList<>();
		given(locationService.getAllItems()).willReturn(locations);
		mvc.perform(get("/location"))
				// .with(csrf().asHeader()))
				.andExpect(status().isNotFound());
	}

	@Test
	// @WithMockUser
	public void retrieveAllLocationsReturnNullTest() throws Exception {
		given(locationService.getAllItems()).willReturn(null);
		mvc.perform(get("/location"))
				// .with(csrf().asHeader()))
				.andExpect(status().isNotFound());
	}

}
