package edu.nuc.ihouse_01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.nuc.ihouse_01.dao.RentalDao;
import edu.nuc.ihouse_01.model.entity.Rental;
import edu.nuc.ihouse_01.service.RentalService;
import org.springframework.stereotype.Service;

@Service
public class RentalServerImpl extends ServiceImpl<RentalDao, Rental> implements RentalService {
}
