package com.bngs0.storingdata

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
        //silme alert dialog
        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Silmek istediğinize emin misiniz?")
        alert.setMessage("Sildiğiniz veriye geri ulaşamayacaksınız yine de silecek misiniz?")
        alert.setPositiveButton("Evet", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                // silme işlemi
                if (userName != null){
                    binding.resultText.text = "Hoşgeldin!"
                    Toast.makeText(this@MainActivity,"Başarıyla Silindi",Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit().remove("name").apply()
                }
            }
        })
        alert.setNegativeButton("Hayır", object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Toast.makeText(this@MainActivity,"Silinmedi",Toast.LENGTH_SHORT).show()
            }
        })
        alert.show()
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
