package jp.co.internous.chocolate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import jp.co.internous.chocolate.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.chocolate.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.chocolate.model.session.LoginSession;

@Controller
@RequestMapping("/chocolate/history")
public class PurchaseHistoryController {

	//DAOを追加
	@Autowired
	private TblPurchaseHistoryMapper purchaseHistoryMapper;
	
	@Autowired
	private LoginSession loginSession;

	//purchase_history.htmlに遷移するメソッド
	//user_idに紐づいた購入履歴を表示するメソッド
	@RequestMapping("/")
	public String index(Model m) {
		int userId = loginSession.getUserId();
		List<PurchaseHistoryDto> historyList = purchaseHistoryMapper.findByUserId(userId);
		
		m.addAttribute("historyList", historyList);
		m.addAttribute("loginSession", loginSession);
		
		return "purchase_history";
	}
	
	//購入履歴削除呼び出し status 0で購入履歴情報をテーブルから削除
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		int userId = loginSession.getUserId();
		int result = purchaseHistoryMapper.logicalDeleteByUserId(userId);
		
		return result > 0;
	}
}
