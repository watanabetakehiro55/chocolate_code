// カート機能の全体的な制御・ページ左遷

package jp.co.internous.chocolate.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.chocolate.model.domain.TblCart;
import jp.co.internous.chocolate.model.domain.dto.CartDto;
import jp.co.internous.chocolate.model.form.CartForm;
import jp.co.internous.chocolate.model.mapper.TblCartMapper;
import jp.co.internous.chocolate.model.session.LoginSession;

@Controller
@RequestMapping("/chocolate/cart")
public class CartController {

//インスタンス化（TblCartMapper・LoginSession）
	@Autowired
	private TblCartMapper cartMapper;
	@Autowired
	private LoginSession loginSession;

// JavaのオブジェクトをJSON形式に変換
	private Gson gson = new Gson();


//〇初期表示（ユーザーID・カート情報を取得しcart.htmlを表示）
	@RequestMapping("/")
	public String index(Model m) {
		// ユーザーIDを取得（LoginSessionから）
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		//　カート情報を取得（userIdを使用してfindByUserIdメソッドを作動）
		List<CartDto> carts = cartMapper.findByUserId(userId);
		// ユーザーID・カート情報を格納
		m.addAttribute("loginSession",loginSession);
		m.addAttribute("carts",carts);

		return "cart";
	}


//〇追加機能（カートテーブルに挿入・更新し、cart.htmlを表示）
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {

		// ユーザーIDを（LoginSessionから）取得
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();

		f.setUserId(userId);

		//　カートテーブルに挿入/更新
		TblCart cart = new TblCart(f);
		int result = 0;

		//「ユーザーに紐づくカート情報に、追加する商品IDと一致するデータが存在するか」で分岐
		//存在する　→　tbl_cart.product_countを『tbl_cart.product_countに購入個数を足した値』で更新する。
		//存在しない　→　カート情報を登録する。
		if (cartMapper.findCountByUserIdAndProuductId(userId, f.getProductId()) > 0) {
			result = cartMapper.update(cart);
		} else {
			result = cartMapper.insert(cart);
		}
		if (result > 0) {
			List<CartDto> carts = cartMapper.findByUserId(userId);
			m.addAttribute("loginSession",loginSession);
			m.addAttribute("carts",carts);
		}
		return "cart";
	}


//〇削除機能（チェックされたカート情報をDBのカート情報テーブルから削除）
	@SuppressWarnings("unchecked")
	@RequestMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		int result = 0;

		Map<String, List<String>> map = gson.fromJson(checkedIdList, Map.class);
		List<String> checkedIds = map.get("checkedIdList");
		for (String id : checkedIds) {
			result += cartMapper.deleteById(Integer.parseInt(id));
		}

		return result > 0;
	}
}
