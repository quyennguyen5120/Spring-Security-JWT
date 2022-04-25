package com.example.srpingsecurityjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

@SpringBootApplication
public class SrpingSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrpingSecurityJwtApplication.class, args);
//        javax.swing.Timer timer = new Timer(5000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Printing statement after every 2 seconds");
//            }
//        });
//        java.util.Timer tt = new java.util.Timer(false);
//        tt.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                timer.start();
//                timer.stop();
//            }
//        }, 0, 1000);
    }

}
