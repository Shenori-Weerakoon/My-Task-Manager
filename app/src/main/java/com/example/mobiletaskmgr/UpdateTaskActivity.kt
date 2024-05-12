package com.example.mobiletaskmgr

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobiletaskmgr.databinding.ActivityUpdateTaskBinding


class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TaskDatabaseHelper
    private var taskId: Int = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id", -1)
        if(taskId == -1){
            finish()
            return
        }

        val task = db.getTaskByID(taskId)
        binding.updateNameEditText.setText(task.name)
        binding.updateDescText.setText(task.description)

        binding.updateSaveButton.setOnClickListener{
            val newName = binding.updateNameEditText.text.toString()
            val newDescription = binding.updateDescText.text.toString()
            val updatedTask = Task(taskId, newName, newDescription)
            db.updateTask(updatedTask)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()

        }




    }
}