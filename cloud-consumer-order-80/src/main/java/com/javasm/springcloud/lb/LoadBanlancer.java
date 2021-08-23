package com.javasm.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBanlancer {

     ServiceInstance instance(List<ServiceInstance> instances);

}
