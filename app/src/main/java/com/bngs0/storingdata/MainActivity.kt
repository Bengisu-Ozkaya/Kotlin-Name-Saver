package com.bngs0.storingdata

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.bngs0.storingdata.databinding.ActivityMainBinding

class MainActivity : android.app.Activity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences : SharedPreferences

    var name : String = ""

    var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //SharedPreferences
        sharedPreferences = this.getSharedPreferences("package com.bngs0.storingdata",Context.MODE_PRIVATE)

        userName = sharedPreferences.getString("name","") // sharedpref de bir değer varsa onu çekiyor
        if (userName == ""){
            binding.resultText.text = "Hoşgeldin!"
        } else{
            binding.resultText.text = "Hoşgeldin, ${userName}!"
        }

        //button listener
        buttonListener()

    }

    private fun buttonListener() {
        //kaydet butonu
        binding.saveButton.setOnClickListener { saveButton() }

        //silme butonu
        binding.deleteButton.setOnClickListener { deleteButton() }
    }

    fun MainActivity.deleteButton() {
        // silme işlemi
        if (userName != ""){
            binding.resultText.text = "Hoşgeldin!"
            sharedPreferences.edit().remove("name").apply()
        }
    }

    fun MainActivity.saveButton() {
        // kaydetme işlemi
        name = binding.nameText.text.toString()


        if (name != null && !name.isEmpty()){
            binding.resultText.text = "Hoşgeldin, ${name}!"
            sharedPreferences.edit().putString("name",name).apply()
        }




    }


}
