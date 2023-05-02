package com.bridhop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepActivity : AppCompatActivity() {
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cep)

        val backButton = findViewById<Button>(R.id.back)
        val cepEditText = findViewById<EditText>(R.id.cep)
        val getAddressButton = findViewById<Button>(R.id.get_address)
        progressBar = findViewById(R.id.progress_bar)

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        getAddressButton.setOnClickListener{
            getAddressByCep(cepEditText.editableText.toString())
        }
    }

    private fun getAddressByCep(cep: String){
        progressBar?.visibility = View.VISIBLE

        val context = this
        val call = RetrofitFactory().retrofitService().getAddressByCep(cep)

        call.enqueue(object : Callback<Cep> {
            override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                Log.i("call", call.toString())

                response.body().let{
                    Toast.makeText(this@CepActivity, it?.toString() ?: "Invalid cep", Toast.LENGTH_LONG).show()
                    progressBar?.visibility = View.INVISIBLE
                }
            }

            override fun onFailure(call: Call<Cep>, throwable: Throwable) {
                Log.i("call", call.toString())
                Log.i("throwable", throwable.toString())
                Toast.makeText(context, "Error in get Cep", Toast.LENGTH_LONG).show()
                progressBar?.visibility = View.INVISIBLE
            }
        })
    }
}
