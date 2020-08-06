package com.jt.manager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.manager.pojo.Item;

/**
 * 商品持久层
 * @author asus
 *
 */
public interface ItemMapper extends SysMapper<Item>{

	/**
	 * 查询记录总数
	 * @return
	 */
	int findCount();

	/**
	 * 分页查询
	 * @param start 起始页
	 * @param rows  页大小
	 * @return
	 */
	List<Item> findItemByPage(@Param("start")int start,@Param("rows")int rows);

	/**
	 * 查询类名
	 * @param itemId
	 * @return
	 */
	@Select("select  name  from tb_item_cat where id=#{itemId}")
	String findItemNameById(Long itemId);

	/**
	 * 批量更改商品状态
	 * @param ids
	 * @param status 
	 */
	void updateStatus(@Param("ids")Long[] ids, @Param("status")Integer status);

	
}
