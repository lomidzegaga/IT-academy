package com.example.itstep

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itstep.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val list = mutableListOf<NameModel>()
    private val iconList = listOf(
        R.drawable.alarm_on,
        R.drawable.backup
    )

    private val adapter = UsersRV(list, iconList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        list.add(NameModel("sdhasd"))
        list.add(NameModel("sads"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))
        list.add(NameModel("sadsasdsa"))

        adapter.itemCallback = { icon, name ->
            startActivity(Intent(this, SecondActivity::class.java).apply {
                putExtra("icon", icon)
                putExtra("name", name)
            })
        }
    }
}