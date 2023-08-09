package ru.mcclinics.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mcclinics.orderservice.domain.Author;
import ru.mcclinics.orderservice.service.AuthorService;
import ru.mcclinics.orderservice.service.EmployeeDtoClientService;
import ru.mcclinics.orderservice.service.EntityDtoParamService;
import ru.mcclinics.orderservice.service.TrackService;

import java.util.Set;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private EmployeeDtoClientService employeeDtoClientService;
	@Autowired
	private EntityDtoParamService entityDtoParamService;
	@Autowired
	private  TrackService trackService;



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
		Long id = 1L;
		Set<Author> authorsById = trackService.findAuthorsById(id);
//		employeeDtoClientService.getEmployeeDegree();
//		Map<String, Mkb10Dto> map = new HashMap<String, Mkb10Dto>();
//		List<Mkb10Dto> mkb10DtoList = entityDtoParamService.getEntityDtoList();
//		for(Mkb10Dto mkb10Dto:mkb10DtoList){
//			map.put(mkb10Dto.getId(), mkb10Dto);
//		}
//
//		Iterator<Map.Entry<String, Mkb10Dto>> iterator = map.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Map.Entry<String, Mkb10Dto> entry = iterator.next();
//			if (entry.getValue().getCode() == null) {
//				iterator.remove();
//			}
//		}
		int i = 1;
//		list.forEach(t -> System.out.println(
//				t.getAuthor() + ":" + t.getId() + ":" + t.getLectures().size()
//		));
	}

}
