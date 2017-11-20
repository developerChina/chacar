package org.core.dao.visitor;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.visitor.provider.RecordVisitorsProvider;
import org.core.domain.visitor.RecordVisitors;

/**   
 * @Description: Mapper接口
 */
public interface RecordVisitorsDao {
	@SelectProvider(type=RecordVisitorsProvider.class,method="save")
	void save(RecordVisitors entity);
	
	@Delete(" delete from "+RecordVisitors.tableName+" where recordVID = #{id} ")
	void deleteById(String id);
		
	@SelectProvider(type=RecordVisitorsProvider.class,method="update")
	void update(RecordVisitors entity);
	
	@Select("select * from "+RecordVisitors.tableName+" where recordVID = #{id}")
	RecordVisitors selectById(String id);
	
	@SelectProvider(type=RecordVisitorsProvider.class,method="selectByEntity")
	List<RecordVisitors> selectByEntity(RecordVisitors entity);

	@Select("select * from "+RecordVisitors.tableName+" where recordID = #{recordid}")
	List<RecordVisitors> selectVisitorByRecordId(String recordid);
	
	@Delete(" delete from "+RecordVisitors.tableName+" where recordID = #{recordid} ")
	void deleteByRecordID(String recordid);

	@Select("select * from "+RecordVisitors.tableName+" where recordID = #{recordid} and visitStatus=#{status}")
	List<RecordVisitors> selectVisitorByRID_Statuts(@Param("recordid")String recordid, @Param("status")int status);

	@Select("select * from "+RecordVisitors.tableName+" where cardID = #{cardid} and visitStatus=#{status}")
	List<RecordVisitors> selectRecordInfoBycardID_status(@Param("cardid")String cardid,@Param("status")int status);
	
	@Select("select * from "+RecordVisitors.tableName+" where recordID = #{recordid} and visitStatus=#{status} and isAudit=#{audit} ")
	List<RecordVisitors> selectVisitorByRID_Statuts_audit(@Param("recordid")String recordid, @Param("status")int status, @Param("audit")int audit);
	
	@Select("select * from "+RecordVisitors.tableName+" where cardID = #{cardid} and visitStatus=#{status}  and isAudit=#{audit} ")
	List<RecordVisitors> selectRecordInfoBycardID_status_audit(@Param("cardid")String cardid,@Param("status")int status,@Param("audit")int audit);

	
	@Select("select * from "+RecordVisitors.tableName+" where cardNo = #{cardNo} limit 1")
	RecordVisitors selectVisitorByCardNo(String cardNo);
	
}
