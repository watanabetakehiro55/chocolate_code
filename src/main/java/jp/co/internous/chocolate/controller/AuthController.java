package jp.co.internous.chocolate.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import jp.co.internous.chocolate.model.domain.MstUser;
import jp.co.internous.chocolate.model.form.UserForm;
import jp.co.internous.chocolate.model.mapper.MstUserMapper;
import jp.co.internous.chocolate.model.mapper.TblCartMapper;
import jp.co.internous.chocolate.model.session.LoginSession;

@RestController
@RequestMapping("/chocolate/auth")
public class AuthController {
	
	private Gson gson = new Gson();
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private TblCartMapper cartMapper;
	
	
	
	@RequestMapping("/login")
	public String login(@RequestBody UserForm f) {
		MstUser user = userMapper.findByUserNameAndPassword(f.getUserName(),f.getPassword());
		
			
			int tmpUserId = loginSession.getTmpUserId();
			if(user != null && tmpUserId != 0) {
				int count = cartMapper.findCountByUserId(tmpUserId);
				if(count > 0) {
					cartMapper.updateUserId(user.getId(),tmpUserId);
				}
			}
		
			if (user != null) {
				loginSession.setTmpUserId(0);
				loginSession.setLogined(true);
				loginSession.setUserId(user.getId());
				loginSession.setUserName(user.getUserName());
				loginSession.setPassword(user.getPassword());
			} else {
				loginSession.setLogined(false);
				loginSession.setUserId(0);
				loginSession.setUserName(null);
				loginSession.setPassword(null);
			}
			
			return gson.toJson(user);
		}
			
	

	@RequestMapping("/logout")
	public String logout() {
		loginSession.setTmpUserId(0);
		loginSession.setLogined(false);
		loginSession.setUserId(0);
		loginSession.setUserName(null);
		loginSession.setPassword(null);
		
		return "";	
	}
	
	@RequestMapping("/resetPassword")
	public String resetPassword(@RequestBody UserForm f ) {	
		String message = "?????????????????????????????????????????????";
		String newPassword = f.getNewPassword();
		String newPasswordConfirm = f.getNewPasswordConfirm();
		
		MstUser user = userMapper.findByUserNameAndPassword(f.getUserName(), f.getPassword());
		if(user == null) {
			return "???????????????????????????????????????????????????";
		}
		if(user.getPassword().equals(newPassword)) {
			return "?????????????????????????????????????????????????????????????????????";
		}
		if(!newPassword.equals(newPasswordConfirm)) {
			return "?????????????????????????????????????????????????????????????????????";
		}

		userMapper.updatePassword(user.getUserName(), f.getNewPassword());
		loginSession.setPassword(f.getNewPassword());
		
		return message;
		
	}
	

}
