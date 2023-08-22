package com.keer.underline.service.impl;

import com.keer.underline.entity.Api;
import com.keer.underline.mapper.ApiMapper;
import com.keer.underline.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * api信息表 服务实现类
 * </p>
 *
 * @author young
 * @since 2023年08月19日
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {

}
