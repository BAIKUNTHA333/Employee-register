package com.example.employeeregister.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract val employeeDao: EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null
        fun getInstance(context: Context): EmployeeDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EmployeeDatabase::class.java,
                        "employee_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}