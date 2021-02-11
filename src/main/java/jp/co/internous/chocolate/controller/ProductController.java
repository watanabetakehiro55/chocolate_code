package jp.co.internous.chocolate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.chocolate.model.domain.MstProduct;
import jp.co.internous.chocolate.model.mapper.MstProductMapper;
import jp.co.internous.chocolate.model.session.LoginSession;

@Controller
@RequestMapping("/chocolate/product")
public class ProductController {
	
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private LoginSession loginSession;

//product_detail.htmlへの遷移
	@RequestMapping("/{id}")
	public String index(@PathVariable("id") int id, Model m) {
		// 商品情報を取得
		MstProduct product = productMapper.findById(id);
		m.addAttribute("product", product);
		m.addAttribute("loginSession",loginSession);
				
		return "product_detail";
	}
}
