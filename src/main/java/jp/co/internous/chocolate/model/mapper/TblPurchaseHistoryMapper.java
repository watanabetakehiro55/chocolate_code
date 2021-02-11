package jp.co.internous.chocolate.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.chocolate.model.domain.dto.PurchaseHistoryDto;

@Mapper
public interface TblPurchaseHistoryMapper {
	//insert
	int insert(Map<String, Object> parameter);
	
	// userIdによって履歴検索。PurchaseHistoryDtoのリストを返却するメソッド宣言 findByUserIdの中身はXMLに記載
	List<PurchaseHistoryDto> findByUserId(@Param("userId") int userId);
	
	//購入履歴情報を表示しなくする。@updateでstatusを0にする
	@Update("UPDATE tbl_purchase_history SET status = 0, updated_at = now() WHERE user_id = #{userId}")
	int logicalDeleteByUserId(@Param("userId") int userId);
}