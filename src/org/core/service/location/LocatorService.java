package org.core.service.location;

import java.util.List;

import org.core.domain.location.LocationLocator;
import org.core.util.tag.PageModel;

public interface LocatorService {
	//查询所有定位仪
	public List<LocationLocator> findLocationLocator(LocationLocator locationLocator, PageModel pageModel);
	//删除
	public void removeLocationLocatorById(String id);
	//根据id查询定位仪
	public LocationLocator findLocationLocatorById(Integer id);
	//修改定位仪
	public void modifyLocationLocator(LocationLocator locationLocator);
	//添加定位仪
	public void addLocationLocator(LocationLocator locationLocator);
	
}
