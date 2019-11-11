package com.bfsu.myproject_01.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiveServer {
    @RabbitListener(queues = "atguigu.emps")
    public void receive(Message message){

       // byte[] body = message.getBody();
        System.out.println(message.getBody());
        String ss=new String(message.getBody());
        System.out.println(ss);
    }

    //如果有实体类，可直接映射为对象
    /*@RabbitListener(queues = "atguigu.news")
    public void receive(Book book){
        System.out.println("收到消息："+book);
    }*/
    /*@RabbitListener(queues = "atguigu.emps")
    public void receive(String message){
        //这个打印出来的都是ascall码
        // byte[] body = message.getBody();
        System.out.println("99999999");
        System.out.println(message);
    }*/


}
