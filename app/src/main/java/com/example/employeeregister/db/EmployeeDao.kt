package com.example.employeeregister.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface EmployeeDao {
    @Insert
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employee_data_table")
    fun getAllEmployee(): LiveData<List<Employee>>
}