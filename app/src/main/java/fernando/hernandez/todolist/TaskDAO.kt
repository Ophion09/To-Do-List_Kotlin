package fernando.hernandez.todolist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDAO {

    @Query("SELECT * FROM tasks")
    fun getTasks(): List<Task>

    @Query("SELECT * FROM tasks where `desc` = :description")
    fun getTask(description: String): Task
    @Insert
    fun addTask(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun updateTask(task: Task)
}