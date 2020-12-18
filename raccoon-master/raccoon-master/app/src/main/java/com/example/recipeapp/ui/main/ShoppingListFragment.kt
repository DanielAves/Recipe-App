package com.example.recipeapp.ui.main

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log

import com.example.recipeapp.R

import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.core.view.isInvisible
import com.example.recipeapp.MainActivity
import com.example.recipeapp.ui.main.RecipeByFilter.Companion.v
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import java.io.*
import java.lang.Exception
import java.io.File
/**
 This fragment is responsible for a shopping list utility
 @author Dan Aves & Matt Cutts
 */
class ShoppingListFragment : Fragment() {
    var shoppingList = arrayListOf<String>()
    lateinit var adapter: ArrayAdapter<String>
    lateinit var shoppinglistview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_shopping_list, container, false)
        adapter =ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_multiple_choice, shoppingList)


        var path = context?.getFilesDir()
        var letDirectory = File(path, "LET")
        var listStored = File(letDirectory, "shoppinglist.txt")
        letDirectory.mkdirs()

        //Declare local variable for on screen list view
        shoppinglistview = v.findViewById<ListView>(R.id.shoppingListView)


        /*
        Handle a new item getting added to the list
         */
        val addBtn: Button = v.findViewById(R.id.addItem)
        // This adds the item to the list when the add btn is pressed and the edit text is populated.
        addBtn.setOnClickListener {
            if(editText.text.toString() != ""){
                shoppingList.add(editText.text.toString() + "\n")
                shoppingListView.adapter =  adapter
                adapter.notifyDataSetChanged()

                // Whenever something has been added from the input, the text wil be removed.
                editText.text.clear()

                addToShoppingList(shoppingList[shoppingList.size-1], fromRecipe = false)
            }
        }

        /*
        This clears all of the items in the shopping list when the clearBtn is pressed
         */
        val clearBtn: Button = v.findViewById(R.id.clearList)
        clearBtn.setOnClickListener {
            shoppingList.clear()
            adapter.notifyDataSetChanged()
            listStored.writeText("")
        }

        /*
        This handles a selected item being removed from the list
         */
        val deleteBtn: Button = v.findViewById(R.id.deleteItem)
        deleteBtn.setOnClickListener {

            val position: SparseBooleanArray = shoppingListView.checkedItemPositions
            val itemCount = shoppingListView.count - 1
            for (i in itemCount downTo 0){
                if (position.get(i)){
                    RemoveItemFromShoppingList(shoppingList.get(i),listStored)
                    adapter.remove(shoppingList.get(i))
                    }
            }
            adapter.notifyDataSetChanged()
        }

        /*
        Read the existing shopping list from storage if one exists
         */
        readShoppingList(listStored)

        return v
    }

    /**
     * Method for reading the shopping list from locally stored copy
     * @param filename name of the file which is stored
     */
    fun readShoppingList(filename: File){
        shoppingList.clear()
        val inputList = mutableListOf<String>()
        try{
            FileInputStream(filename).bufferedReader().useLines { lines -> lines.forEach {inputList.add(it)}  }
            for (line in inputList) {
                shoppingList.add(line)
            }
            shoppinglistview.adapter =  adapter
            adapter.notifyDataSetChanged()
        }catch (e: FileNotFoundException){
            println("File doesn't exist, program will create one")
        }
    }

    /**
     * Method for removing an item from the shopping list stored locally
     * @param item string representing item to remove
     * @param filename name of the file which is stored
     */
    fun RemoveItemFromShoppingList(item: String, filename: File){
        val inputList = mutableListOf<String>()
        FileInputStream(filename).bufferedReader().useLines { lines -> lines.forEach {inputList.add(it)}  }
        //Clear list
        filename.writeText("")
        for (line in inputList) {
            if(!line.equals(item) ){
                //inputList.add(line)
                filename.appendText(line + "\n")
            }
        }
    }


    /**
     * Method for adding an item to the shopping list stored locallyx
     * @param filename name of the file which is stored
     * @param fromRecipe flag to determine if ingredients are being added from a recipe
     */
    fun addToShoppingList(str:String?=null, recipeList:MutableList<String>?=null, fromRecipe:Boolean)
    {
        var path = context?.getFilesDir()
        var letDirectory = File(path, "LET")
        var listStored = File(letDirectory, "shoppinglist.txt")
        letDirectory.mkdirs()
        var dir = File("/data/data/com.example.recipeapp/files/" + listStored)
        println(dir)

        if(fromRecipe && recipeList != null){
            //Read existing list first
            val inputList = mutableListOf<String>()
            try{
                FileInputStream(dir).bufferedReader().useLines { lines -> lines.forEach {inputList.add(it)}  }
                if(str !in inputList){
                    for(ing in recipeList){
                        dir.appendText(ing + "\n")
                    }
                }
            }
            catch (e: FileNotFoundException){
                println("File doesn't exist, program will create one")
                println(letDirectory)
                println(listStored)
            }
        }
        else{
            if (str != null) {
                listStored.appendText(str)
            }
        }
    }
}
