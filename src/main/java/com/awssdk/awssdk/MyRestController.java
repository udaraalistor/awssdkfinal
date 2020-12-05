package com.awssdk.awssdk;

import com.awssdk.awssdk.data.Message;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class MyRestController {

    @GetMapping("api/hello")
    public Message greeding () {
        Message mesaage = new Message();
        mesaage.setSender("Server");
        mesaage.setMessaage("Hello World");
        mesaage.getSentTime(new Date());
        return mesaage;

    }

    @GetMapping("api/message")
    public Message messageBuilder (
            @RequestParam("username")
                    String user,
            @RequestParam("message")
                    String message
    ) {

        Message mesaage = new Message();
        mesaage.setSender(user);
        mesaage.setMessaage(message);
        mesaage.getSentTime(new Date());
        return mesaage;

    }

    @RequestMapping(value= "/api/message", method = RequestMethod.POST)
    public Message createMessage(@RequestBody() Message mesaage){
        System.out.println(mesaage);
        return mesaage;
    }

}
