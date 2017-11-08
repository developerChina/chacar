package org.core.domain.webapp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AccessGroup implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String agid;	//门禁组id
		private String agname;	//门禁组名称
		private String agssxj;	//门禁组所属下级
		/*
		 * 存放门禁的集合
		 * */
		private Set<Access> orderItems = new HashSet<Access>();

		public String getAgid() {
			return agid;
		}
		public void setAgid(String agid) {
			this.agid = agid;
		}
		public String getAgname() {
			return agname;
		}
		public void setAgname(String agname) {
			this.agname = agname;
		}
		public String getAgssxj() {
			return agssxj;
		}
		public void setAgssxj(String agssxj) {
			this.agssxj = agssxj;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public AccessGroup() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "AccessGroup [agid=" + agid + ", agname=" + agname + ", agssxj=" + agssxj + "]";
		}
		public Set<Access> getOrderItems() {
			return orderItems;
		}
		public void setOrderItems(Set<Access> orderItems) {
			this.orderItems = orderItems;
		}
	}
