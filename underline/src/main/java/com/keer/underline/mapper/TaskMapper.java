package com.keer.underline.mapper;

import com.keer.underline.entity.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 任务信息表 Mapper 接口
 * </p>
 *
 * @author young
 * @since 2023年08月19日
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

}
