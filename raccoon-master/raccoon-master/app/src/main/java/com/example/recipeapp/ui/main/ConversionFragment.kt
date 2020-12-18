package com.example.recipeapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView


import com.example.recipeapp.R
import kotlinx.android.synthetic.main.fragment_conversion.*
import kotlinx.android.synthetic.main.fragment_shopping_list.*

/**
 * ConversionFragment is fragment subclass that converts weight metrics when the conversion tab is pressed.
 */
class ConversionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var spinner1 = ""
        var spinner2 = ""
        val v = inflater.inflate(R.layout.fragment_conversion, container, false)
        val weightinput: EditText = v.findViewById(R.id.weightInput) as EditText

        //spinnerFrom
        val weightMetric1: Spinner = v.findViewById(R.id.sp_from)

        //Initializing array to populate the spinner
        val convertFrom = arrayOf("Milligrams (mg)", "Grams (g)", "Ounces (oz)")

        // Initializing adapter for the spinner
        var adapterFrom = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, convertFrom)
        weightMetric1.adapter = adapterFrom

        //This is where we check what option in the spinner is activated by assigning 'spinner1' a string to compare.
        weightMetric1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when (position) {
                    0 -> {
                        spinner1 = "fromMilli"
                    }
                    1 -> {
                        spinner1 = "fromGrams"
                    }
                    2 -> {
                        spinner1 = "fromOunces"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //spinnerTo
        var finalResult: TextView = v.findViewById(R.id.result)
        val weightMetric2: Spinner = v.findViewById(R.id.sp_to)

        //Initializing array to populate the spinner
        val convertTo = arrayOf("Milligrams (mg)", "Grams (g)", "Ounces (oz)")

        // Initializing adapter for the spinner
        var adapterTo = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, convertTo)
        weightMetric2.adapter = adapterTo

        //This is where we check what option in the spinner is activated by assigning 'spinner2' a string to compare.
        weightMetric2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long) { weightMetric2.setSelection(position)

                when (position) {
                    0 -> {
                        spinner2 = "toMilli"
                    }
                    1 -> {
                        spinner2 = "toGrams"
                    }
                    2 -> {
                        spinner2 = "toOunces"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //finding the convert button by its Id in the .xml
        val convert: Button = v.findViewById(R.id.convert1)

        //creating an onclicklistener that compares the strings held in spinner1 and spinner2 in order to decided what it is the user wants to dinvert.
        convert.setOnClickListener {
            val rawInput: String = weightinput.text.toString()
            val rawInputDouble: Double = rawInput.toDouble()

            //Compares the strings and carries out correct formula to convert the valyes given. Then displays the end result to the user.
            when {
                spinner1.equals("fromMilli") && spinner2.equals("toGrams") -> { finalResult.text = (rawInputDouble / 1000).toString()
                }
                spinner1.equals("fromMilli") && spinner2.equals("toOunces") -> { finalResult.text = (rawInputDouble / 28350).toString()
                }
                spinner1.equals("fromGrams") && spinner2.equals("toOunces") -> { finalResult.text = (rawInputDouble / 28.35).toString()
                }
                spinner1.equals("fromGrams") && spinner2.equals("toMilli") -> { finalResult.text = (rawInputDouble * 1000).toString()
                }
                spinner1.equals("fromOunces") && spinner2.equals("toMilli") -> { finalResult.text = (rawInputDouble * 28350).toString()
                }
                spinner1.equals("fromOunces") && spinner2.equals("toGrams") -> { finalResult.text = (rawInputDouble * 28.35).toString()
                }
            }
        }
        // Inflate the layout for this fragment
        return v
    }

}
