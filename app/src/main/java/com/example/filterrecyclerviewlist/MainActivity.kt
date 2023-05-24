package com.example.filterrecyclerviewlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.filterrecyclerviewlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var studentList:MutableList<StudentModel>
    lateinit var studentAdapter:StudentAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
      initView()
    }

    private fun initView() {
        studentList= ArrayList<StudentModel>()
        studentAdapter = StudentAdapter(this, studentList)
        binding.studentRv.adapter = studentAdapter
        studentList.add(StudentModel(
            "ggjghj","gjhjj",
            R.drawable.users_icon,"12",
            "fytytyt","hghghjgjhgjh"))

            studentList.add(StudentModel(
                "lljghj","gjhjj",
                R.drawable.baseline_search_24,"12",
                "fytytyt","hghghjgjhgjh"))
        studentList.add(StudentModel(
            "aajghj","gjhjj",
            R.drawable.users_icon,"12",
            "fytytyt","hghghjgjhgjh"))

        studentList.add(StudentModel(
            "mmmmm","gjhjj",
            R.drawable.baseline_search_24,"12",
            "fytytyt","hghghjgjhgjh"))

        HandleFilter()
    }

    private fun HandleFilter() {
       binding.etSearch.addTextChangedListener(object :TextWatcher{
           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

           }

           override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               try {
                   if (::studentAdapter.isInitialized) {
                       if (count < before) {
                           studentAdapter.resetData()
                       }
                       Log.e("filter", s.toString())
                       studentAdapter.filter.filter(s.toString())
                   }
               } catch (e: NullPointerException) {
                   e.printStackTrace()
               }
           }

           override fun afterTextChanged(s: Editable?) {

           }


       })


    }
}