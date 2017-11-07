package org.core.service.webapp;
import java.util.List;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.PassagewayGroup;
import org.core.util.tag.PageModel;
/**
 * 通道分组
 * */
public interface PassagewayGroupService {
//获得所有通道，返回Passageway对象的集合
List<PassagewayGroup> findPassagewayGroup(PassagewayGroup passagewayGroup,PageModel pageModel);
//删除
void removePassagewayGroupById(String id);
//根据id查找下级
List<Passageway> getPassagewayById(String selectids);
//查询所有通道
List<Passageway> selectPGSubordinate();
//添加通道分组
void addPGroup(String ids, String pgname);
//修改前查询下
PassagewayGroup selectPGbyId(String id);
//修改
void updatePG(PassagewayGroup passagewayGroup);
}
