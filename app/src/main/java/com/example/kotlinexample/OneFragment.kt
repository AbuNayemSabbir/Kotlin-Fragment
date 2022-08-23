package com.example.kotlinexample

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.*


class OneFragment : Fragment(),  View.OnClickListener{

    private var spinnerGender: Spinner? = null
    private var gender = arrayOf("Select", "Male", "Female", "Others")

    private var picker: DatePickerDialog? = null
    private var dateOfBirth: EditText? = null
    private var sname:EditText? = null
    private var sendBtn: Button? = null
    private var isAllFieldsChecked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view  = inflater.inflate(R.layout.fragment_one, container, false) as ViewGroup
        sendBtn = view.findViewById(R.id.save)
        dateOfBirth = view.findViewById(R.id.dob)

        spinnerGender = view.findViewById(R.id.spinner)
      //  spinnerGender!!.onItemClickListener = this@OneFragment



        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(context!!, android.R.layout.simple_spinner_item, gender)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerGender!!.adapter = aa


        dateOfBirth!!.isCursorVisible = false
        dateOfBirth!!.isFocusable = false
        dateOfBirth!!.setOnClickListener(this@OneFragment)

        dateOfBirth!!.setOnClickListener(View.OnClickListener {
            val cldr = Calendar.getInstance()
            val day = cldr[Calendar.DAY_OF_MONTH]
            val month = cldr[Calendar.MONTH]
            val year = cldr[Calendar.YEAR]
            // date picker dialog
            picker = DatePickerDialog(
                context!!,
                { view, year, monthOfYear, dayOfMonth -> dateOfBirth!!.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year) },
                year,
                month,
                day
            )
            picker!!.show()
        })
        sendBtn!!.setOnClickListener(View.OnClickListener {
            sname = view.findViewById(R.id.name)
            val itemText = spinnerGender!!.selectedItem as String
            val name = sname!!.text.toString()
            val dobirth = dateOfBirth!!.text.toString()
            isAllFieldsChecked = CheckAllFields()
            if (isAllFieldsChecked) {
                val bundle = Bundle()
                bundle.putString("NAME", name)
                bundle.putString("DOB", dobirth)
                bundle.putString("GENDER", itemText)
                val activity = view.context as AppCompatActivity
                val twoFragment = TwoFragment()
                twoFragment.arguments = bundle
                // getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,twoFragment).commit();
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, twoFragment).addToBackStack(null).commit()
            }
        })


        return view;
    }

//    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//
//    }
//
//    override fun onNothingSelected(p0: AdapterView<*>?) {
//
//    }

    override fun onClick(p0: View?) {

    }
    private fun CheckAllFields(): Boolean {
        if (sname!!.length() == 0) {
            sname!!.error = "This field is required"
            return false
        }
        if (dateOfBirth!!.length() == 0) {
            dateOfBirth!!.error = "This field is required"
            return false
        }
        return true
    }



}