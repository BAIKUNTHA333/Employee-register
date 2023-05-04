package com.example.employeeregister.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_data_table")
data class Employee(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "employee_id")
    var id: Int,

    @ColumnInfo(name = "employee_name")
    var name: String,
    @ColumnInfo(name = "employee_email")
    var email: String,
    @ColumnInfo(name = "employee_status")
    var status: String,
    @ColumnInfo(name = "employee_licence")
    var licence: String,
    @ColumnInfo(name = "employee_dob")
    var dob: String,



)