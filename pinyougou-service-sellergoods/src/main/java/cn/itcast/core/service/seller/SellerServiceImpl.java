package cn.itcast.core.service.seller;

import cn.itcast.core.dao.seller.SellerDao;
import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.seller.Seller;
import cn.itcast.core.pojo.seller.SellerQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SellerServiceImpl implements SellerService {
    @Resource
    SellerDao sellerDao;

    @Override
    public void save(Seller seller) {
        seller.setStatus("0");
        seller.setCreateTime(new Date());

        sellerDao.insertSelective(seller);
    }

    @Override
    public PageResult search(Integer page, Integer rows, Seller seller) {
        //1.初始化分页插件
        PageHelper.startPage(page,rows);
        //2.获取查询条件
        SellerQuery sellerQuery = new SellerQuery();
        SellerQuery.Criteria criteria = sellerQuery.createCriteria();
        criteria.andStatusEqualTo(seller.getStatus());
        //3.查询
        Page<Seller> sellers = (Page<Seller>) sellerDao.selectByExample(sellerQuery);
        //4.返回结果
        return new PageResult(sellers.getResult(),sellers.getTotal());
    }

    @Transactional
    @Override
    public void add(Seller seller) {
        seller.setStatus("0");
        seller.setCreateTime(new Date());

        sellerDao.insertSelective(seller);
    }

    @Override
    public Seller findOne(String id) {
        Seller seller = sellerDao.selectByPrimaryKey(id);
        return seller;
    }

    @Transactional
    @Override
    public void updateStatus(String sellerId, String status) {
        Seller seller = new Seller();
        seller.setSellerId(sellerId);
        seller.setStatus(status);
        sellerDao.updateByPrimaryKeySelective(seller);
    }


}
