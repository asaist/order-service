package ru.mcclinics.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.service.AuthorService;
import ru.mcclinics.orderservice.service.EmployeeDtoClientService;
import ru.mcclinics.orderservice.service.TrackService;

import java.util.List;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private EmployeeDtoClientService employeeDtoClientService;

	public EmployeeDtoClientService getEmployeeDtoClientService() {
		return employeeDtoClientService;
	}


	@Test
	void contextLoads() {
	}

	@Test
	public void findTracks() throws JsonProcessingException {
//		List<Track> list = trackService.findTracks();
		System.out.println("ОК");
		employeeDtoClientService.getEmployeeDto();
//		list.forEach(t -> System.out.println(
//				t.getAuthor() + ":" + t.getId() + ":" + t.getLectures().size()
//		));
	}

}
