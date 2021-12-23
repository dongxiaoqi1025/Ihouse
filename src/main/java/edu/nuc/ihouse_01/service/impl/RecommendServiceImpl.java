package edu.nuc.ihouse_01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.nuc.ihouse_01.dao.RecommendDao;
import edu.nuc.ihouse_01.model.entity.Recommend;
import edu.nuc.ihouse_01.service.RecommendService;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendDao, Recommend> implements RecommendService {
}
