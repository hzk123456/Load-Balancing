package com.example.consumerdemo.dao;


import com.example.consumerdemo.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Author: 98050
 * Time: 2018-07-31 13:39
 * Feature:
 */
@Repository
public class UserDao {
    //地址变成动态的了，但是访问还是通过RestTemplate
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    public User queryUserById(Integer id) {
        //根据服务名获取服务，因为如果是集群，那么提供服务的端口可能有多个，所以返回的是列表
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//        //如果有多个，需要编写负载均衡算法，挑选一个
//        ServiceInstance serviceInstance=instances.get(0);
//        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/" + id;

        //负载均衡，只写服务名，自动寻找ip和端口

        String url="http://user-service/user/"+id;
        return this.restTemplate.getForObject(url, User.class);
    }
}
