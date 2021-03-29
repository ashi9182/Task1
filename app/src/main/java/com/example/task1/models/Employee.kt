package com.example.task1.models

class Employee(val status : String,val data: EmployeeData,val message : String){
    constructor() : this("",EmployeeData(),"")
}