/*
 Name: Curt Terpstra
 Class: CIS-245-OA010 (Spring 2021)
 App: Week 8 Intents Multiple Variables
*/

package edu.rockvalleycollege.week8intentsmultiplevariables

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtString = findViewById<TextView>(R.id.txtString)
        var btnPage2 = findViewById<Button>(R.id.btnPage2)

        var spSpinner = findViewById<Spinner>(R.id.spSpinner)
        var spinList = arrayOf("East","MidWest","SouthEast", "Mountain", "West","Other")

        var radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        //Spinner adapter setup
        val spinnerAdapter = ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, spinList)
        android.R.layout.simple_spinner_item
        android.R.layout.simple_spinner_dropdown_item
        spSpinner.adapter = spinnerAdapter


        btnPage2.setOnClickListener {

            var id = -1

            // grab the id of the selected button in the radio group
            id = radioGroup.checkedRadioButtonId

            // error check to make sure that we have a string entered and a button selected.
            if(txtString.text.isEmpty() || id == -1){
                hideKeyboard()
                txtString.requestFocus()
                Toast.makeText(this,"The input string is empty or there is no radio button selected", Toast.LENGTH_LONG).show()
            }else {

                val radio: RadioButton = findViewById(id)


                // This builds the information to send to activityMain2
                var txtSendInfo = "String Entered: ${txtString.text.toString()} \nSpinner Value ${spSpinner.selectedItem.toString()} \nRadio button Selected: ${radio.text}"

                //Intent is used to send data between activities
                val intent = Intent(this, MainActivity2::class.java)
                //putExtra sets value to name SendStuff (Could be called whatever you want
                intent.putExtra("SendStuff", txtSendInfo)

                //Go to second activity
                startActivity(intent)
                hideKeyboard()
                txtString.requestFocus()
                txtString.setText("")
            }

        }// end of Onclick Listener

        findViewById<View>(android.R.id.content).setOnTouchListener { _, event ->
            hideKeyboard()
            false
        }
    }// End of OnCreate

    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }
    }// end of Hide Keyboard

}// End of main activity