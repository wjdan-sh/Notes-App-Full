package com.example.notesappfull

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var dbh: DBHelper

    private lateinit var RC: RecyclerView
    private lateinit var ed: EditText
    private lateinit var btn: Button
    private lateinit var masseges: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbh = DBHelper(this)

        RC = findViewById(R.id.rv)
        masseges = dbh.retriveData()


        ed = findViewById(R.id.ed)
        btn = findViewById(R.id.btn)

        RC.adapter = RVAdapter(this , masseges)
        RC.layoutManager = LinearLayoutManager(this)


        ed.text.clear()
        ed.clearFocus()
        RC.adapter?.notifyDataSetChanged()



        btn.setOnClickListener {

            val masseg = ed.text.toString()

            var status = dbh.save(masseg)
            Toast.makeText(this, "data saved! " + status , Toast.LENGTH_SHORT).show()

            masseges.add(dbh.Retrieve(masseg))
            ed.text.clear()
            ed.clearFocus()
            RC.adapter?.notifyDataSetChanged()

        }

    }

    fun RCupdate(){
        masseges = dbh.retriveData()
        RC.adapter = RVAdapter(this , masseges)
        RC.layoutManager = LinearLayoutManager(this)

    }

    fun update(note : String, message: String ){

        val int = dbh.update(note,message)
        RC.adapter?.notifyDataSetChanged()
        RCupdate()
        Toast.makeText(this, "data updated! " + int , Toast.LENGTH_SHORT).show()
    }

    fun delete(message: String) {

        dbh.delete(message)
        RC.adapter?.notifyDataSetChanged()
        RCupdate()

    }

    fun Dialog(message : String ){
        val dialogBuilder = AlertDialog.Builder(this)
        val Note = EditText(this)
        Note.hint = "Enter new text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {

                    dialog, id-> update( Note.text.toString(),message)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(Note)
        alert.show()
    }


}