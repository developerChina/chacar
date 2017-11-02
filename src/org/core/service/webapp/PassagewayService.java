package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.Passageway;
import org.core.util.tag.PageModel;

public interface PassagewayService {
		
	//获得所有通道，返回Passageway对象的集合
	List<Passageway> findPassageway(Passageway passageway,PageModel pageModel);
	//删除
	void removePassagewayById(Integer id);
	//修改
	void modifyPassageway(Passageway passageway);
	//根据id查通道
	Passageway findPassagewayById(Integer passagewayID);
	//添加通道
	void addPassageway(Passageway passageway);
}
