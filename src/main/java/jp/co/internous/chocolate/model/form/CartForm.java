// 追加機能で使用
// 扱うデータ（計3）
// userId; productId; productCount;

package jp.co.internous.chocolate.model.form;

import java.io.Serializable;

public class CartForm implements Serializable{

	private static final long serialVersionUID = 1L;

  // データを定義
	private int userId;
	private int productId;
	private int productCount;

  // 以下ゲッターセッター

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
}
