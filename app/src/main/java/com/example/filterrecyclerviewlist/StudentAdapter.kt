package com.example.filterrecyclerviewlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.filterrecyclerviewlist.databinding.CustomItemViewBinding
import java.util.*

class StudentAdapter(var context: Context, var originalList: MutableList<StudentModel>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(),Filterable {

    var filterStudentList:List<StudentModel> =originalList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        var binding: CustomItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.custom_item_view,
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        //return studentList.size
        return filterStudentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        var studentList = filterStudentList[position]

        holder.binding.tvName.text = studentList.studentName
        holder.binding.tvAddress.text = studentList.studentName
        holder.binding.tvAge.text = studentList.studentAge
        holder.binding.tvNumber.text = studentList.studentNubmer
        holder.binding.ivImage.setImageResource(studentList.studentImage)

    }


    fun resetData(){
filterStudentList=originalList
    }


    class StudentViewHolder(var binding: CustomItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun getFilter(): Filter {
       return object:Filter(){
           override fun performFiltering(charSequence: CharSequence?): FilterResults {
               val enteredString = charSequence?.toString()?.toLowerCase(Locale.getDefault())

               val filterResults = FilterResults()
               filterResults.values = if (enteredString == null || enteredString.isEmpty())
                   originalList
               else
                  originalList.filter {
                       it.studentName!!.toLowerCase(Locale.getDefault())
                           .contains(enteredString) || it.studentAddress!!.toLowerCase(Locale.getDefault())
                           .contains(enteredString)
                   }
               return filterResults
           }

           override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
               if (filterResults != null) {
                   filterStudentList = filterResults.values as List<StudentModel>
               }
               notifyDataSetChanged()
           }

       }
    }
}