package com.javasm.springcloud.lb;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBanlancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        //  这是一个自旋锁
        do{
            current = atomicInteger.get();
            next = current >=4464646?0:current+1;
        }while(!this.atomicInteger.compareAndSet(current,next));

        // 2021/8/21 查看元子类操作,第一次进入，current 0，next 1，
        // while 判断 atomicInteger 与 current相同
        // 1 写进 atomicInteger，返回!true 退出循环，返回 next 1
        System.out.println("第几次访问next:"+next);
        return next;
    }


    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {
        int index = this.getAndIncrement() % instances.size();
        return instances.get(index);
    }
}
