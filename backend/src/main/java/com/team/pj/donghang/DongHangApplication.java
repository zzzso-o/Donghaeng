package com.team.pj.donghang;

import com.team.pj.donghang.domain.entity.ShoppingDetail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class DongHangApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongHangApplication.class, args);
//        ShoppingDetail sda = null;
    }

}
