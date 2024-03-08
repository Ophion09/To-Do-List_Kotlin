package fernando.hernandez.todolist
import android.content.Intent
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

class EditTask : AppCompatActivity() {
    lateinit var et_task: EditText
    lateinit var btn_update: Button
    lateinit var listView_task: ListView
    lateinit var tasks_list: ArrayList<String>
    lateinit var adaptor: ArrayAdapter<String>
    lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        val task = intent.getSerializableExtra("task") as Task
        // Llenar campos de interfaz con información de la tarea

        btn_update = findViewById(R.id.btn_update) // Agregar esta línea

        btn_update.setOnClickListener {
            db.taskDao().updateTask(task)
            finish()
        }
    }

}