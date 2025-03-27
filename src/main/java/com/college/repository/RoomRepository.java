/* RoomRepository.java
RoomRepository Model class
Author: joshua twigg (222153881)
Date: 27 March 2025
*/

package com.college.repository;

import java.util.HashMap;
import java.util.Map;


public class RoomRepository {

    public static void main(String[] args) {
        /// have collection class eg arraylist,set,hashmap to act as local database
        Map<String, String> map = new HashMap<>();
        map.put("x","yes");

        System.out.println(map);
        System.out.println(map.get("x"));



    }

    public Map<String, String> getValue() {  // ✅ Now type-safe
        Map<String, String> map2 = new HashMap<>();
        return map2;
    }

}
