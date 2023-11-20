//package com.dovi.studentapi.feignClients;
//
//import feign.Feign;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.context.annotation.Bean;
//
//@LoadBalancerClient(value = "ADDRESS-SERVICE")
//public class AddressServiceLoadBalancer {
//
//    @Bean
//    @LoadBalanced
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
//}
