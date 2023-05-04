package com.example.employeeregister

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeregister.db.Employee
import com.example.employeeregister.db.EmployeeDatabase
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var statusEditText: TextView
    private lateinit var licenceEditText: EditText
    private lateinit var dobEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button

    private lateinit var viewModel: EmployeeViewModel
    private lateinit var employeeRecyclerView: RecyclerView
    private lateinit var adapter: EmployeeRecyclerViewAdapter
    private var isListItemClicked = false

    private lateinit var selectedEmployee: Employee
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        statusEditText = findViewById(R.id.etStatus)
        licenceEditText = findViewById(R.id.etLicence)
        dobEditText = findViewById(R.id.etDob)
        saveButton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)
        employeeRecyclerView = findViewById(R.id.rvEmployee)

        val rdGroup=findViewById<RadioGroup>(R.id.radioGroup)
        val rStatus1=findViewById<RadioButton>(R.id.status1)
        val rStatus2=findViewById<RadioButton>(R.id.status2)
        rdGroup.setOnCheckedChangeListener { group, checkedId ->
              if(checkedId==R.id.status1){
                  statusEditText.text=rStatus1.text
              }
              if(checkedId==R.id.status2){
                    statusEditText.text=rStatus2.text
              }
          }
        dobEditText.setOnClickListener {


            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dobEditText.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        val dao = EmployeeDatabase.getInstance(application).employeeDao
        val factory = EmployeeViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory)[EmployeeViewModel::class.java]
        saveButton.setOnClickListener {
            if (isListItemClicked) {
                updateEmployeeData()
                clearInput()
            } else {
               saveEmployeeData()
                clearInput()
            }
        }
        clearButton.setOnClickListener {
            if (isListItemClicked) {
                deleteEmployeeData()
                clearInput()
            } else {
                clearInput()
            }
        }
        initRecyclerView()

    }

    private fun saveEmployeeData() {
        viewModel.insertEmployee(
            Employee(
                0,
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                statusEditText.text.toString(),
                licenceEditText.text.toString(),
                dobEditText.text.toString()
            )
        )
    }
    private fun updateEmployeeData() {
        viewModel.updateEmployee(
            Employee(
                selectedEmployee.id,
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                statusEditText.text.toString(),
                licenceEditText.text.toString(),
                dobEditText.text.toString()
            )
        )
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }
    private fun deleteEmployeeData() {
        viewModel.deleteEmployee(
            Employee(
                selectedEmployee.id,
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                statusEditText.text.toString(),
                licenceEditText.text.toString(),
                dobEditText.text.toString()
            )
        )
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }


    private fun clearInput() {
        nameEditText.setText("")
        emailEditText.setText("")
        statusEditText.text = ""
        licenceEditText.setText("")
        dobEditText.setText("")
    }

    private fun initRecyclerView() {

        employeeRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EmployeeRecyclerViewAdapter { selectedItem: Employee ->
             listItemClicked(selectedItem)
           }
        employeeRecyclerView.adapter = adapter

        displayEmployeeList()
    }

    private fun displayEmployeeList() {
        viewModel.employee.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }
    private fun listItemClicked(employee: Employee) {
        selectedEmployee = employee
        saveButton.text = "Update"
        clearButton.text = "Delete"
        isListItemClicked = true
        nameEditText.setText(selectedEmployee.name)
        emailEditText.setText(selectedEmployee.email)
        statusEditText.text = selectedEmployee.status
        licenceEditText.setText(selectedEmployee.licence)
        dobEditText.setText(selectedEmployee.dob)


    }


}