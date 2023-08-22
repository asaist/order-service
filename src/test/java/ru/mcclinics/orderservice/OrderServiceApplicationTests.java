package ru.mcclinics.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mcclinics.orderservice.service.TrackService;

@SpringBootTest
class OrderServiceApplicationTests {


	@Autowired
	private  TrackService trackService;
//	@Autowired
//	private DocumentProcessingService documentProcessingService;





	@Test
	void contextLoads() {
	}

	@Test
	public void findTracks() throws JsonProcessingException {
//		List<Track> list = trackService.findTracks();
		System.out.println("ОК");
		Long id = 1L;
//		Set<Author> authorsById = trackService.findAuthorsById(id);
//		String str = documentProcessingService.launchProcess().toString();
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
