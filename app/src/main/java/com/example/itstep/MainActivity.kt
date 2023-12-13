package com.example.itstep

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itstep.databinding.ActivityMainBinding
import com.example.itstep.databinding.DialogLayoutBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = UsersRV()
    private lateinit var noteDataBase: AppDataBase
    private lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        noteDataBase = AppDataBase.getInstance(this)
        noteDao = noteDataBase.noteDao()

        setContentView(binding.root)

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.button.setOnClickListener {
            showDialog()
        }

        lifecycleScope.launch {
            noteDao.getNotes().collect { notes ->
                adapter.updateList(notes)
            }
        }

        adapter.itemCallback = { name ->
            startActivity(Intent(this, SecondActivity::class.java).apply {
                putExtra("name", name)
            })
        }
    }

    private fun showDialog() {
        val dialogBinding = DialogLayoutBinding.inflate(
            LayoutInflater.from(this)
        )
        val dialog = Dialog(this)

        val displayMetrics = DisplayMetrics()
        val with = displayMetrics.widthPixels * 0.8

        dialog.setContentView(
            dialogBinding.root
        )


        dialogBinding.saveBtn.setOnClickListener {
            lifecycleScope.launch {
                noteDao.insertNote(
                    Note(
                        title = dialogBinding.titleET.text.toString(),
                        note = dialogBinding.noteET.text.toString()
                    )
                )
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}