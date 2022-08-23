package com.example.kotlinexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class TwoFragment : Fragment() {


    private var sname: TextView? = null
    private var sdob:TextView? = null
    private var sgender:TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_two, container, false) as ViewGroup

        sname = view.findViewById(R.id.saveName)
        sdob = view.findViewById(R.id.savedob)
        sgender = view.findViewById(R.id.savegender)

        val bundle = this.arguments

        val name = bundle!!.getString("NAME")
        val dateOfBirth = bundle!!.getString("DOB")
        val gender = bundle!!.getString("GENDER")




        sname!!.text = "Name: $name"
        sdob!!.text = "Selected Date: $dateOfBirth"
        sgender!!.text = "Gender Is: $gender"



        return view
    }



}