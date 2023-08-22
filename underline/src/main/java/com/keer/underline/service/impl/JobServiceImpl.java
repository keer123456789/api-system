package com.keer.underline.service.impl;

import com.keer.underline.entity.Job;
import com.keer.underline.mapper.JobMapper;
import com.keer.underline.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * job信息表 服务实现类
 * </p>
 *
 * @author young
 * @since 2023年08月19日
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

}
