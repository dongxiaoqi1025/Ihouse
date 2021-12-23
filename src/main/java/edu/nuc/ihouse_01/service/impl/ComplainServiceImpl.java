package edu.nuc.ihouse_01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.nuc.ihouse_01.dao.ComplainDao;
import edu.nuc.ihouse_01.model.entity.Complain;
import edu.nuc.ihouse_01.service.ComplainService;
import org.springframework.stereotype.Service;

@Service
public class ComplainServiceImpl extends ServiceImpl<ComplainDao, Complain> implements ComplainService {
}
