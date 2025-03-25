/* EmployeeFactory.java
   Employee Factory Class
   Author: Muaath Slamong (230074138)
   Date: 14 March 2025
*/

package com.college.factory;

import com.college.entity.Employee;

public class EmployeeFactory {

    // Create Employee
    public static Employee createEmployee(int employeeId, String employeeName, String employeeSurname, String employeeRole) {
        return new Employee.EmployeeBuilder()
                .setEmployeeId(employeeId)
                .setEmployeeName(employeeName)
                .setEmployeeSurname(employeeSurname)
                .setEmployeeRole(employeeRole)
                .build();

    }
}
