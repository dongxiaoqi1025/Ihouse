package edu.nuc.ihouse_01.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.nuc.ihouse_01.model.entity.Complain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComplainDao extends BaseMapper<Complain> {
}
