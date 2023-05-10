package ru.mcclinics.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mcclinics.orderservice.domain.Track;
import ru.mcclinics.orderservice.service.TrackService;

import java.util.List;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	private TrackService trackService;

	@Test
	void contextLoads() {
	}

	@Test
	public void findTracks(){
//		List<Track> list = trackService.findTracks();
		System.out.println("ОК");
//		list.forEach(t -> System.out.println(
//				t.getAuthor() + ":" + t.getId() + ":" + t.getLectures().size()
//		));
	}

}
