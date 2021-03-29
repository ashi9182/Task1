package com.example.task1.models

class EmployeeData (val id : Int,val employee_name : String,val employee_salary : Int,val employee_age : Int,val profile_image: String ) {
    constructor() : this(-1,"",-1,-1,"")
}