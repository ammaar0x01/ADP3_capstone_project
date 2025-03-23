/*

Project:    temp
Desc:       temp
Started:    11.03.25
Updated:    11.03.25
---

Members
-------
Name and surname | Student id | GitHub profile                |
---------------------------------------------------------------
Ammaar Swartland    | 230159478     | (original) https://github.com/ammaar0x01/ADP3_capstone_project    |
Zaid Theunissen     | 221084142     | https://github.com/zaid-xt/ADP3_capstone_project                  |
Joshua Twigg        | 222153881     |                                                                   |
Talia Theresa Smuts | 221126082     | https://github.com/Taliasmuts28/ADP3_capstone_project.git         |
Muaath Slamong      | 230074138     | https://github.com/MuaathSlamong-alt/ADP3_capstone_project.git    |
---------------------------------------------------------------
*/

package com.college;

import com.college.entity.Employee;
import com.college.entity.Housekeeper;
import com.college.factory.EmployeeFactory;
import com.college.factory.HousekeeperFactory;

public class Main {
    public static void main(String[] args) {

        Housekeeper housekeeper = HousekeeperFactory.createHousekeeper(56, "John", "Smith");

        System.out.println("Hello world!");
    }
}
