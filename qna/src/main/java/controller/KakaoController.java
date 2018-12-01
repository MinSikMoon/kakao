package controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	
	@RequestMapping(value = "/test")
	public @ResponseBody void test() {
		KakaoDao mdao = sqlSession.getMapper(KakaoDao.class);
		for(KakaoVo vo : mdao.selectAll()){
			System.out.println(vo.getTitle());
		}
	}

	//keyboard
	@RequestMapping(value = "/keyboard", produces="application/json;charset=utf8")
	public @ResponseBody String keyboard() {
		//1. jsonarray만들기
		JSONObject responseItem = new JSONObject();
		JSONArray buttonItem = new JSONArray();

		//make buttons
		buttonItem.add("질문하기");
		buttonItem.add("답변보기");

		//make response json
		responseItem.put("type", "buttons");
		responseItem.put("buttons", buttonItem);
	
		return responseItem.toJSONString();
	}
}