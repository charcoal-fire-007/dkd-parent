package com.dkd.manage.mapper;

import java.util.List;
import com.dkd.manage.domain.Task;
import com.dkd.manage.domain.vo.TaskVO;

/**
 * 工单Mapper接口
 * 
 * @author charcoalfire
 * @date 2025-07-31
 */
public interface TaskMapper 
{
    /**
     * 查询工单
     * 
     * @param taskId 工单主键
     * @return 工单
     */
    public Task selectTaskByTaskId(Long taskId);

    /**
     * 查询工单列表
     * 
     * @param task 工单
     * @return 工单集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增工单
     * 
     * @param task 工单
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改工单
     * 
     * @param task 工单
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 删除工单
     * 
     * @param taskId 工单主键
     * @return 结果
     */
    public int deleteTaskByTaskId(Long taskId);

    /**
     * 批量删除工单
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskByTaskIds(Long[] taskIds);

    /**
     * 查询运维工单列表
     *
     * @param task 运维工单
     * @return TaskVo集合
     */
    List<TaskVO> selectTaskVoList(Task task);

}

