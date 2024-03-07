package fernando.hernandez.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    lateinit var et_task: EditText
    lateinit var btn_add: Button
    lateinit var listView_task: ListView
    lateinit var tasks_list: ArrayList<String>
    lateinit var adaptor: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_task = findViewById(R.id.et_task)
        btn_add = findViewById(R.id.btn_add)
        listView_task = findViewById(R.id.listview_task)

        tasks_list = ArrayList()

        tasks_list.add("prueba")
        tasks_list.add("prueba")
        tasks_list.add("prueba")


        adaptor = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks_list)
        listView_task.adapter = adaptor


        btn_add.setOnClickListener {
            var task = et_task.text.toString()

            if(!task.isNullOrEmpty()) {
                tasks_list.add(task)
                adaptor.notifyDataSetChanged()
                et_task.setText("")
            }else{
                Toast.makeText(this, "llenar campo", Toast.LENGTH_SHORT).show()
            }
        }

        listView_task.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            tasks_list.removeAt(position)
            adaptor.notifyDataSetChanged()
        }
    }
}