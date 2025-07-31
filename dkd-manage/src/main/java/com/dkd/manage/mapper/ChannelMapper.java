package com.dkd.manage.mapper;

import java.util.List;
import com.dkd.manage.domain.Channel;
import com.dkd.manage.domain.vo.ChannelVO;

/**
 * 售货机货道Mapper接口
 * 
 * @author charcoalfire
 * @date 2025-07-30
 */
public interface ChannelMapper 
{
    /**
     * 查询售货机货道
     * 
     * @param id 售货机货道主键
     * @return 售货机货道
     */
    public Channel selectChannelById(Long id);

    /**
     * 查询售货机货道列表
     * 
     * @param channel 售货机货道
     * @return 售货机货道集合
     */
    public List<Channel> selectChannelList(Channel channel);

    /**
     * 新增售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    public int insertChannel(Channel channel);

    /**
     * 修改售货机货道
     * 
     * @param channel 售货机货道
     * @return 结果
     */
    public int updateChannel(Channel channel);

    /**
     * 删除售货机货道
     * 
     * @param id 售货机货道主键
     * @return 结果
     */
    public int deleteChannelById(Long id);

    /**
     * 批量删除售货机货道
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChannelByIds(Long[] ids);

    public int batchInsertChannels(List<Channel>  channelList);

    /**
     * 根据商品管理主键查询关联的货道数量
     * @param skuIds
     * @return
     */

    int countChannelBySkuIds(Long[] skuIds);

    /**
     * 根据售货机内码查询关联的货道列表
     * @param innercode
     * @return
     */
    List<ChannelVO> selectChannelVOListInnerCode(String innercode);
}
