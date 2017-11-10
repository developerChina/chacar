package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.TotleAuthTABLE;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.TotleAuthorization;

public class TotleAuthorizationProvider {
	// 添加
	public String saveTotleAuthorization(TotleAuthorization ta) {
		return new SQL() {
			{
				INSERT_INTO(TotleAuthTABLE);
				VALUES("id", "#{id}");
				if (ta.getUserids() != null && !ta.getUserids().equals("")) {
					VALUES("userids", "#{userids}");
				}
				if (ta.getCarno() != null && !ta.getCarno().equals("")) {
					VALUES("carno", "#{carno}");
				}
				if (ta.getMjids() != null && !ta.getMjids().equals("")) {
					VALUES("mjids", "#{mjids}");
				}
				if (ta.getDtids()!= null && !ta.getDtids().equals("")) {
					VALUES("dtids", "#{dtids}");
				}
				if (ta.getTdids() != null && !ta.getTdids().equals("")) {
					VALUES("tdids", "#{tdids}");
				}
			}
		}.toString();
	}
	//修改
		public String updateTotleAuthorization(TotleAuthorization ta){
			return new SQL(){
				{
					{	
						UPDATE(TotleAuthTABLE);

						if (ta.getUserids() != null) {
							SET(" userids = #{userids} ");
						}
						if (ta.getCarno() != null) {
							SET(" carno = #{carno} ");
						}
						if (ta.getMjids() != null) {
							SET(" mjids = #{mjids} ");
						}
						if (ta.getDtids()!= null ) {
							SET(" dtids = #{dtids} ");
						}
						if (ta.getTdids() != null) {
							SET(" tdids = #{tdids} ");
						}
						WHERE(" id = #{id}");
					}

				}
			}.toString();
		}
}
