package com.example.BD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    Sender sender;
    @Autowired
    Manager manager;

    @GetMapping("/ping")
    public String ping(){
        return "Pong";
    }

    @GetMapping("/run")
    public String runProgram(){
        return sender.generateAndPrint();
    }

    @GetMapping("/send")
    public String runProgramUsingSMS(){
        manager.generateAndSendToEachRecipient();
        return "Done!";
    }


    @GetMapping("/timedSend")
    @Scheduled(cron = "${scheduling.runTime}")
    public String runProgramUsingScheduling() {
        manager.generateAndSendToEachRecipient();
        return "Done on time!";
    }


}
