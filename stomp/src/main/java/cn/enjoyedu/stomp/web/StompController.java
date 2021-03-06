package cn.enjoyedu.stomp.web;

import cn.enjoyedu.stomp.domain.ChatRoomRequest;
import cn.enjoyedu.stomp.domain.ChatRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/*
 *  *@author Mark老师   享学课堂 https://enjoy.ke.qq.com
 *  *类说明：在线聊天室
 */

@Controller
public class StompController {

    @Autowired
    private SimpMessagingTemplate template;/*Spring实现的一个发送模板类*/

    /*消息群发，接受发送至自massRequest的请求*/
    @MessageMapping("/massRequest")
    //发送给客户端的接口。
    @SendTo("/mass/getResponse")
    public ChatRoomResponse mass(ChatRoomRequest chatRoomRequest){
        System.out.println("name = " + chatRoomRequest.getName());
        System.out.println("chatValue = " + chatRoomRequest.getChatValue());
        ChatRoomResponse response=new ChatRoomResponse();
        response.setName(chatRoomRequest.getName());
        response.setChatValue(chatRoomRequest.getChatValue());
        return response;
    }

    /*单独聊天，接受发送至自aloneRequest的请求*/
    @MessageMapping("/aloneRequest")
    public ChatRoomResponse alone(ChatRoomRequest chatRoomRequest){
        System.out.println("SendToUser = " + chatRoomRequest.getUserId()
                +" FromName = " + chatRoomRequest.getName()
                +" ChatValue = " + chatRoomRequest.getChatValue());
        ChatRoomResponse response=new ChatRoomResponse();
        response.setName(chatRoomRequest.getName());
        response.setChatValue(chatRoomRequest.getChatValue());
        //发送给具体某个人。
        this.template.convertAndSendToUser(chatRoomRequest.getUserId()+"",
                "/alone",response);
        return response;
    }
}