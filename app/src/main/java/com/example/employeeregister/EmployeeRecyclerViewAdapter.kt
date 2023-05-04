package com.example.employeeregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeregister.db.Employee

class EmployeeRecyclerViewAdapter(
private val clickListener:(Employee)->Unit
):RecyclerView.Adapter<EmployeeViewHolder>(){
    private val employeeList = ArrayList<Employee>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_items,parent,false)
        return EmployeeViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employeeList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }
    fun setList(employees:List<Employee>){
        employeeList.clear()
        employeeList.addAll(employees)
    }

}

class EmployeeViewHolder(private val view: View):RecyclerView.ViewHolder(view){
    fun bind(employee: Employee,clickListener:(Employee)->Unit){
        val nameTextView = view.findViewById<TextView>(R.id.tvName)
        val emailTextView = view.findViewById<TextView>(R.id.tvEmail)
        val statusTextView = view.findViewById<TextView>(R.id.tvStatus)
        val licenceTextView = view.findViewById<TextView>(R.id.tvLicence)
        val dobTextView = view.findViewById<TextView>(R.id.tvDob)

        nameTextView.text = employee.name
        emailTextView.text = employee.email
        statusTextView.text = employee.status
        licenceTextView.text = employee.licence
        dobTextView.text = employee.dob
        view.setOnClickListener {
            clickListener(employee)
        }
    }
}