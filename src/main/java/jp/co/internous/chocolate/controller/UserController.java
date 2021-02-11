package jp.co.internous.chocolate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.chocolate.model.domain.MstUser;
import jp.co.internous.chocolate.model.form.UserForm;
import jp.co.internous.chocolate.model.mapper.MstUserMapper;
import jp.co.internous.chocolate.model.session.LoginSession;

//samplewebをchocolateに変更

@Controller
@RequestMapping("/chocolate/user")
public class UserController {
		
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private LoginSession loginSession;
	//register_user.html表示
	@RequestMapping("/")
	public String index(Model m) {
		m.addAttribute("loginSession",loginSession);
		
		return "register_user";
	}
	
	//ユーザー名重複
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestParam String userName) {		
		int count = userMapper.findCountByUserName(userName);
		return count > 0;
	}
	//ユーザー登録
	@RequestMapping("/register")
	@ResponseBody
	public boolean register(@RequestBody UserForm f) {
		MstUser user = new MstUser(f);
		
		int count = userMapper.insert(user);
		
		return count > 0;
	}
}
