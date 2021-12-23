package edu.nuc.ihouse_01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.nuc.ihouse_01.dao.RepairDao;
import edu.nuc.ihouse_01.model.entity.Repair;
import edu.nuc.ihouse_01.service.RepairService;
import org.springframework.stereotype.Service;

@Service
public class RepairServiceImpl extends ServiceImpl<RepairDao, Repair> implements RepairService {
}
