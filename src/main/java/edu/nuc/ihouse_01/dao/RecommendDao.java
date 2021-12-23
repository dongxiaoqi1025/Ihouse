package edu.nuc.ihouse_01.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.nuc.ihouse_01.model.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommendDao extends BaseMapper<Recommend> {
}
