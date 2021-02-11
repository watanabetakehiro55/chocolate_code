package jp.co.internous.chocolate.model.domain.dto;

/* DBから取得した履歴データをフロントに渡す
	出力する内容[購入日時、商品名、価格、個数、宛名(familyName,FirstName)、住所]
	テーブルにある情報[ID,ユーザーID,商品ID,個数、価格、宛先情報ID,ステータス,購入日時]
 */
public class PurchaseHistoryDto {

	private String purchasedAt;
	private String productName;
	private long price;
	private long productCount;
	private String familyName;
	private String firstName;
	private String address;
		
	//getter,setter
	public String getPurchasedAt() {
		return purchasedAt;
	}
	
	public void setPurchasedAt(String purchasedAt) {
		this.purchasedAt = purchasedAt;
	}		
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
		
	public long getPrice() {
		return price;
	}
		
	public void setPrice(long price) {
		this.price = price;
	}
		
	public long getProductCount() {
		return productCount;
	}
		
	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}
		
	public String getFirstName() {
		return firstName;
	}
		
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
		
	public String getFamilyName() {
		return familyName;
	}
		
	public void setfamilyName(String familyName) {
		this.familyName = familyName;
	}
		
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}