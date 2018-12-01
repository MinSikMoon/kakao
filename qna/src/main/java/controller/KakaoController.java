package controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	//message
	@RequestMapping(value = "/message", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public @ResponseBody String message(@RequestBody String request){
		JSONParser reqParser = new JSONParser();
		Object reqObj;
		JSONArray reqArray;
		String resString = "";

		String userKey, type, content; //request
		JSONObject message, keyboard; //response

		JSONObject responseItem = new JSONObject();
		keyboard = new JSONObject();
		message = new JSONObject();

		// parse request 
		try {
			System.out.println(request);
			reqObj = reqParser.parse(request);
			reqArray = (JSONArray)reqObj;
			System.out.println("hihihihi" + reqArray.size());
			//userKey = (String)reqObj.get("\"user_key\"");
			//content = (String)reqObj.get("content");
			//type = (String)reqObj.get("type");
			//System.out.println(userKey + content + type);
			// if(content.equals("질문하기")){
			// 	message.put("text", "질문을 입력해주세요.");
			// 	keyboard.put("type", "text");
			// }else{
			// 	message.put("text", "답변을 입력해주세요.");
			// 	keyboard.put("type", "text");
			// }
		} catch (ParseException e) {
			e.printStackTrace();
		}
		responseItem.put("message", message);
		responseItem.put("keyboard", keyboard);

		//make response 
		return responseItem.toJSONString();
	}
}