package com.bngs0.storingdata

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.compose.material3.AlertDialog
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

        // intent (second activity'den geldiyse)
        val intentInfo = intent.getIntExtra("back",0) //getIntent() metodu ile aynı şey intent
        if (intentInfo == 0){// Second Activity'den gelmediysem
            if (userName != null && !userName!!.isEmpty()){
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("name",userName)
                startActivity(intent)
            }
        }else if (intentInfo == 1){ //Second Activity'den geldiysem
            Toast.makeText(this,"Second Activity'den döndün",Toast.LENGTH_SHORT).show()
        }else{ // hata durumu
            println("intent hatası\nintent: ${intentInfo}")
        }



        //kaydet butonu
        binding.saveButton.setOnClickListener { saveButton() }

    }

    fun MainActivity.saveButton() {
        // kaydetme işlemi
        name = binding.nameText.text.toString()

        if (userName != null && !userName!!.isEmpty()){ //sharedpref doluysa ve save'e basıldıysa direkt seconda geç
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("name",userName)
            startActivity(intent)
        }else {
            if ((name != null && !name.isEmpty())) {
                sharedPreferences.edit().putString("name", name).apply()
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Lütfen adınızı giriniz!", Toast.LENGTH_SHORT)
                    .show()
            }
        }




    }


}
