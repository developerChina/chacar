package org.core.domain.webapp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PassagewayGroup implements Serializable{
		/**
	 * 通道分组实体
	 */
	private static final long serialVersionUID = 1L;
		public PassagewayGroup() {
			super();
			// TODO Auto-generated constructor stub
		}
		private String pgid;	//通道分组id
		private String pgname;	//	通道分组名称
		private String pgssxj;	//通道分组所属下级
		/*
		 * 存放通道的集合
		 * */
		private Set<Passageway> orderItems = new HashSet<Passageway>();
		
		public String getPgid() {
			return pgid;
		}
		public void setPgid(String pgid) {
			this.pgid = pgid;
		}
		public String getPgname() {
			return pgname;
		}
		public void setPgname(String pgname) {
			this.pgname = pgname;
		}
		public String getPgssxj() {
			return pgssxj;
		}
		public void setPgssxj(String pgssxj) {
			this.pgssxj = pgssxj;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "PassagewayGroup [pgid=" + pgid + ", pgname=" + pgname + ", pgssxj=" + pgssxj + "]";
		}
		public Set<Passageway> getOrderItems() {
			return orderItems;
		}
		public void setOrderItems(Set<Passageway> orderItems) {
			this.orderItems = orderItems;
		}
}
