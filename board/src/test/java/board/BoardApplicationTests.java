package board;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testSq1session() throws Exception{
		System.out.println(sqlSession.toString());
	}
	
}
