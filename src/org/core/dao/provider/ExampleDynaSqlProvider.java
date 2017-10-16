package org.core.dao.provider;

import static org.core.util.GlobleConstants.EXAMPLETABLE;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.core.domain.Example;
import org.core.util.DateUtil;
import org.core.util.GenId;;

/**
 * @Description: 用户动态SQL语句提供类
 */
public class ExampleDynaSqlProvider {

	// 动态插入
	public String insertExample(Example example) {
		return new SQL() {
			{
				INSERT_INTO(EXAMPLETABLE);
				if (example.getUsername() != null && !example.getUsername().equals("")) {
					VALUES("username", "#{username}");
				}
				if (example.getStatus() != null) {
					VALUES("status", "#{status}");
				}
				example.setId(GenId.UUID());
				VALUES("id", "#{id}");
				example.setCreateDate(DateUtil.getCurrentDate());
				VALUES("createDate", "#{createDate}");
			}
		}.toString();
	}

	// 动态更新
	public String updateExample(Example example) {
		return new SQL() {
			{
				UPDATE(EXAMPLETABLE);
				if (example.getUsername() != null) {
					SET(" username = #{username} ");
				}
				if (example.getStatus() != null) {
					SET(" status = #{status} ");
				}
				if (example.getCreateDate() != null) {
					SET(" create_date = #{createDate} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}

	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(EXAMPLETABLE);
				if (params.get("user") != null) {
					Example example = (Example) params.get("example");
					if (example.getUsername() != null && !example.getUsername().equals("")) {
						WHERE("  username LIKE CONCAT ('%',#{user.username},'%') ");
					}
					if (example.getStatus() != null) {
						WHERE(" status LIKE CONCAT ('%',#{user.status},'%') ");
					}
				}
			}
		}.toString();

		/**
		 * 分页后续再补 if(params.get("pageModel") != null){ sql += " limit
		 * #{pageModel.firstLimitParam} , #{pageModel.pageSize} "; }
		 */
		return sql;
	}

	// 动态查询总数量
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(EXAMPLETABLE);
				if (params.get("user") != null) {
					Example example = (Example) params.get("example");
					if (example.getUsername() != null && !example.getUsername().equals("")) {
						WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
					}
					if (example.getStatus() != null) {
						WHERE(" status LIKE CONCAT ('%',#{user.status},'%') ");
					}
				}
			}
		}.toString();
	}
}
