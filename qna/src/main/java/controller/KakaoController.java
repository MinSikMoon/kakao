package controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.KakaoDao;
import vo.KakaoVo;


@Controller
public class KakaoController {
	private static final Logger logger = Logger.getLogger(KakaoController.class);
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/Test")
	public @ResponseBody void mybatisTest() {
		KakaoDao mdao = sqlSession.getMapper(KakaoDao.class);
		for(KakaoVo vo : mdao.selectAll()){
			System.out.println(vo.getTitle());
		}
	}
}