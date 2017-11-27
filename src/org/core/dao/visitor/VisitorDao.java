package org.core.dao.visitor;

import static org.core.util.GlobleConstants.BLACKLISTTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.visitor.provider.BlacklistDynaSqlProvider;
import org.core.domain.visitor.VisitorInfo;
import org.core.domain.webapp.Blacklist;

public interface VisitorDao {
	//黑名单查总条数
	@SelectProvider(type=BlacklistDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	//查具体的黑名单信息
	@SelectProvider(type=BlacklistDynaSqlProvider.class,method="selectWhitParam")
	List<Blacklist> selectByPage(Map<String, Object> params);
	
	//取消黑名单
	@Delete(" delete from "+BLACKLISTTABLE+" where blacklistID = #{id} ")
	void remove(Integer id);
	
	//手动添加黑名单
	@SelectProvider(type=BlacklistDynaSqlProvider.class,method="insertBlack")
	void addBlacklist(Blacklist blacklist);
	
	
	//实现将访客信息添加到黑名单！
		//查访客信息表！
	@SelectProvider(type=BlacklistDynaSqlProvider.class,method="myCount")
	Integer entityCount(Map<String, Object> params);
	@SelectProvider(type=BlacklistDynaSqlProvider.class,method="myselectByPage")
	List<VisitorInfo> myselectByPage(Map<String, Object> params);
	
	@Select("select * from "+BLACKLISTTABLE+" where idNumber = #{cardid}")
	List<Blacklist> selectBlackByCardId(String cardid);
	
	
	

}
