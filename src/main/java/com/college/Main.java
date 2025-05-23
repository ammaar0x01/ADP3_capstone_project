/*

Project:    Hotel-management System
Started:    11.03.25
Updated:    25.03.25
---

Members
-------
Name and surname    | Student id    | GitHub repo                                                       |
---------------------------------------------------------------------------------------------------------
Ammaar Swartland    | 230159478     | (original) https://github.com/ammaar0x01/ADP3_capstone_project    |
Zaid Theunissen     | 221084142     | https://github.com/zaid-xt/ADP3_capstone_project                  |
Joshua Twigg        | 222153881     | https://github.com/JoshuaTwigg/ADP3_capstone_project              |
Talia Theresa Smuts | 221126082     | https://github.com/Taliasmuts28/ADP3_capstone_project             |
Muaath Slamong      | 230074138     | https://github.com/MuaathSlamong-alt/ADP3_capstone_project        |
---------------------------------------------------------------------------------------------------------

Responsibilities
-----------------------------------------------------
Ammaar Swartland    | Reservation classes           |
Zaid Theunissen     | Guest classes                 |
Joshua Twigg        | Room classes                  |
Talia Theresa Smuts | Payment classes               |
Muaath Slamong      | Housekeeper classes           |
-----------------------------------------------------
*/

package com.college;

import com.college.factory.RoomFactory;
import com.college.domain.Room;
import com.college.repository.RoomRepositoryJpa;
import com.college.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class Main {


    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class, args);

//        String line = "-------------------------------------------------------------------";
//
//        System.out.println();
//        System.out.println(line);
//        System.out.println("\nGo to the 'test' package to view the tests for the factory and " +
//                "repository design patterns");
//        System.out.println("\nNOTE: \nFind information about the group members in the block-comment above");
//        System.out.println(line);

    }
}
