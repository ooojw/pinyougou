package cn.itcast.core.service;

import cn.itcast.core.pojo.seller.Seller;
import cn.itcast.core.service.seller.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class UserDetailsServiceImpl implements UserDetailsService {

    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        //使用假数据登录，用户名随意，密码是123456就能登录
//        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
//        return new User(username, "123456", grantedAuthorities);

        //1.根据用户名查询到Seller对象
        Seller seller = sellerService.findOne(username);
        //2.判断该商户是否审核通过
        if (seller != null && seller.getStatus().equals("1")) {
            //3.如果审核通过，那么为之赋予可以登录的角色ROLE_SELLER
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            //5.返回对象
            return new User(seller.getSellerId(), seller.getPassword(), authorities);
        }
        //6.无此用户返回null
        return null;
    }
}
