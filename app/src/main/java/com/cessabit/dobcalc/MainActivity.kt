package com.cessabit.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var tvSelectedDate : TextView? = null
    var tvInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvInMinutes = findViewById(R.id.tvInMinutes)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }

    }
    fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ view,SelectedYear,SelectedMonth,SelectedDayOfMonth ->
                Toast.makeText(this,"the Date is $SelectedDayOfMonth, month is ${SelectedMonth+1}, Year is $SelectedYear",Toast.LENGTH_LONG).show()

                val selectedDate = "$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"
                tvSelectedDate?.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time/60000;
                val currentDate = sdf.parse(sdf.format((System.currentTimeMillis())))
                val currentDateInMinutes = currentDate.time/60000;
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                tvInMinutes?.text = differenceInMinutes.toString()
            },
            year,month,day
        ).show()

    }
}