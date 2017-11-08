package org.core.domain.webapp;

import java.io.Serializable;

public class Access implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Integer accessid;
		private String accessname;
		private String csn;
		private String cip;
		private Integer acno;
		private Integer floorno;
		public Integer getAccessid() {
			return accessid;
		}
		public void setAccessid(Integer accessid) {
			this.accessid = accessid;
		}
		public String getAccessname() {
			return accessname;
		}
		public void setAccessname(String accessname) {
			this.accessname = accessname;
		}
		public String getCsn() {
			return csn;
		}
		public void setCsn(String csn) {
			this.csn = csn;
		}
		public String getCip() {
			return cip;
		}
		public void setCip(String cip) {
			this.cip = cip;
		}
		public Access() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public Integer getAcno() {
			return acno;
		}
		public void setAcno(Integer acno) {
			this.acno = acno;
		}
		
		public Integer getFloorno() {
			return floorno;
		}
		public void setFloorno(Integer floorno) {
			this.floorno = floorno;
		}
		@Override
		public String toString() {
			return "Access [accessid=" + accessid + ", accessname=" + accessname + ", csn=" + csn + ", cip=" + cip
					+ ", acno=" + acno + ", floorno=" + floorno + "]";
		}
		
		
}
