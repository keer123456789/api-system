package com.keer.underline.service.impl;

import com.keer.underline.entity.Task;
import com.keer.underline.mapper.TaskMapper;
import com.keer.underline.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务信息表 服务实现类
 * </p>
 *
 * @author young
 * @since 2023年08月19日
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
