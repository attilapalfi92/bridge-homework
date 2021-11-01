package com.getbridge.homework;

import com.getbridge.homework.controller.OneOnOneController;
import com.getbridge.homework.entity.EmployeeEntity;
import com.getbridge.homework.model.OneOneOneRequest;
import com.getbridge.homework.model.OneOneOneResponse;
import com.getbridge.homework.repository.EmployeeRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchEndpointIntegrationTest {

	private final OneOnOneController oneOnOneController;
	private final EmployeeRepository employeeRepository;

	@Autowired
	public SearchEndpointIntegrationTest(OneOnOneController oneOnOneController,
			EmployeeRepository employeeRepository) {
		this.oneOnOneController = oneOnOneController;
		this.employeeRepository = employeeRepository;
	}

	@Test
	void testSearch() {
		EmployeeEntity employee1 = new EmployeeEntity();
		employee1.setName("a");
		employee1 = employeeRepository.save(employee1);
		EmployeeEntity employee2 = new EmployeeEntity();
		employee2.setName("b");
		employee2 = employeeRepository.save(employee2);

		createOneOnOne(employee1, employee2, true);
		createOneOnOne(employee1, employee2, true);
		createOneOnOne(employee1, employee2, false);
		createOneOnOne(employee1, employee2, false);
		createOneOnOne(employee1, employee2, false);

		List<OneOneOneResponse> closedNumber = oneOnOneController.search(true);
//		assertEquals(3, closedNumber.size());

		List<OneOneOneResponse> openNumber = oneOnOneController.search(false);
//		assertEquals(2, openNumber.size());

		// TODO: add h2 database
	}

	private void createOneOnOne(EmployeeEntity employee1, EmployeeEntity employee2, boolean closed) {
		OneOneOneRequest request = new OneOneOneRequest();
		request.setParticipantId1(employee1.getId());
		request.setParticipantId2(employee2.getId());
		request.setPlannedDate(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
		request.setClosed(closed);
		request.setDescription("description");
		request.setLocation("location");
		request.setTitle("title");
		oneOnOneController.create(request);
	}

}
