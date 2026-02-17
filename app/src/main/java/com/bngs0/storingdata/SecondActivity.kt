package com.bngs0.storingdata

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import com.bngs0.storingdata.databinding.ActivitySecondBinding

class SecondActivity : android.app.Activity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var sharedPreferences : SharedPreferences
    var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //shared preferences
        sharedPreferences = this.getSharedPreferences("package com.bngs0.storingdata",Context.MODE_PRIVATE)

        userName = sharedPreferences.getString("name","")
        if ((userName != null && !userName!!.isEmpty())){
            // intent
            val intentInfo = intent
            userName = intentInfo.getStringExtra("name")
            if (userName == null){
                binding.resultText.text = "Hoşgeldin! \nBugün Nasılsın?"
            }else{
                binding.resultText.text = "Hoşgeldin,${userName} \nBugün Nasılsın?"
            }
        }



        binding.backButton.setOnClickListener { goBack() }

        //silme butonu
        binding.deleteButton.setOnClickListener { deleteButton() }
    }

    fun deleteButton() {
        //silme alert dialog
        val alert = AlertDialog.Builder(this@SecondActivity)
        alert.setTitle("Silmek istediğinize emin misiniz?")
        alert.setMessage("Sildiğiniz veriye geri ulaşamayacaksınız yine de silecek misiniz?")
        alert.setPositiveButton("Evet", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                // silme işlemi
                if (userName != null && !userName!!.isEmpty()){
                    Toast.makeText(this@SecondActivity,"Başarıyla Silindi",Toast.LENGTH_SHORT).show()
                    sharedPreferences.edit().remove("name").apply()
                    val intent = Intent(this@SecondActivity, MainActivity::class.java)
                    intent.putExtra("back",1)
                    startActivity(intent)
                    finish()
                }
            }
        })
        alert.setNegativeButton("Hayır", object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Toast.makeText(this@SecondActivity,"Silinmedi",Toast.LENGTH_SHORT).show()
            }
        })
        alert.show()
    }

    private fun goBack() {
        var intent = Intent(this@SecondActivity, MainActivity::class.java)
        intent.putExtra("back",1)
        startActivity(intent)
        finish()
    }
}