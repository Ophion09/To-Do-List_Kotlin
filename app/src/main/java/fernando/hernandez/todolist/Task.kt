package fernando.hernandez.todolist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    var desc: String
)