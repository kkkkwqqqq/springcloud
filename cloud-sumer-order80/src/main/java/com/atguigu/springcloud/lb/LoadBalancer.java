package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/*提供具体哪个服务器*/


public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
