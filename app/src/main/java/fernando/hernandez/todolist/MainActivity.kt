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
import androidx.room.Room
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    lateinit var et_task: EditText
    lateinit var btn_add: Button
    lateinit var listView_task: ListView
    lateinit var tasks_list: ArrayList<String>
    lateinit var adaptor: ArrayAdapter<String>
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_task = findViewById(R.id.et_task)
        btn_add = findViewById(R.id.btn_add)
        listView_task = findViewById(R.id.listview_task)

        tasks_list = ArrayList()


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "task-db"
        ).allowMainThreadQueries().build()

        cargar_tareas()


        adaptor = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks_list)
        listView_task.adapter = adaptor


        btn_add.setOnClickListener {
            var task_str = et_task.text.toString()

            if(!task_str.isNullOrEmpty()) {
                var task = Task(desc = task_str)
                db.taskDao().addTask(task)

                tasks_list.add(task_str)
                adaptor.notifyDataSetChanged()
                et_task.setText("")
            }else{
                Toast.makeText(this, "llenar campo", Toast.LENGTH_SHORT).show()
            }
        }

        listView_task.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            var task_desc = tasks_list[position]

            var task = db.taskDao().getTask(task_desc)

            db.taskDao().delete(task)

            tasks_list.removeAt(position)
            adaptor.notifyDataSetChanged()
        }
    }

    private fun cargar_tareas() {
        var db_list = db.taskDao().getTasks()
        for(task in db_list) {
            tasks_list.add(task.desc)
        }
    }
}