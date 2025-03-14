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
Zaid Theunissen | 221084142  | https://github.com/zaid-xt |
---------------------------------------------------------------
*/

package com.college;

import com.college.domain.Guest;
import com.college.factory.GuestFactory;

public class Main {
    public static void main(String[] args) {

        Guest guest1 = GuestFactory.createGuest(102, "Alice", "Smith", "9876543210", "alice@email.com", "PayPal");
        System.out.println(guest1);

    }
}
