package cn.itcast.core.service.seller;

import cn.itcast.core.entity.PageResult;
import cn.itcast.core.pojo.seller.Seller;

public interface SellerService {

    void save(Seller seller);


    PageResult search(Integer page, Integer rows, Seller seller);

    void add(Seller seller);

    Seller findOne(String id);

    void updateStatus(String sellerId, String status);
}
