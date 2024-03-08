package fernando.hernandez.todolist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    var desc: String
) : Serializable