package com.merveselva.catchtheball

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.merveselva.catchtheball.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var imageArray = ArrayList<ImageView>()
    var runnable : Runnable = Runnable {}
    var handler : Handler = Handler(Looper.getMainLooper()) //android.os
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //imageArray yapıyoruz=>
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView21)
        imageArray.add(binding.imageView22)
        imageArray.add(binding.imageView23)
        imageArray.add(binding.imageView24)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView31)
        imageArray.add(binding.imageView32)
        imageArray.add(binding.imageView33)
        imageArray.add(binding.imageView34)
        imageArray.add(binding.imageView41)
        imageArray.add(binding.imageView42)
        imageArray.add(binding.imageView43)
        imageArray.add(binding.imageView44)
        imageArray.add(binding.imageView51)
        imageArray.add(binding.imageView52)
        imageArray.add(binding.imageView53)
        imageArray.add(binding.imageView54)
        imageArray.add(binding.imageView61)
        imageArray.add(binding.imageView62)
        imageArray.add(binding.imageView63)
        imageArray.add(binding.imageView64)


        hideImages()

        object : CountDownTimer(30500,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Time: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                binding.timeText.text = "Game Over"
                handler.removeCallbacks(runnable) // runnable durdurmak için yapılır ama top hala görünür.
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                } //bu  loopla süre bitince bütün toplar kaybolur.

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Your Score: $score" )

                alert.setPositiveButton("Try Again", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val intentFromMain = intent
                        finish()
                        startActivity(intentFromMain)
                    }
                })

                alert.setNegativeButton("No") {p0, p1 ->
                    Toast.makeText(this@MainActivity, "Game Over!", Toast.LENGTH_LONG).show()
                }

                alert.show()


            }

        }.start()
    }

    fun hideImages() {

        runnable = object:Runnable{ //for döngüsünü runnable ile yazaraka süreklii tekrar etmesini sağlıyoruz
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(25) //nextInt bound da nereye kadar gideceğini belirtiyoruz. indexin 1 fazlası kadar verilir
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500 /*yarım saniye*/)//kaç saniyede bir çalışacağını sağlamak için handler la kontrol ediyoruz.
            }
        }
        handler.post(runnable) //runnable başlattık.
    }

    fun increaseScore(view : View) {
        score+=1
        binding.scoreText.text = "Score: $score"
    }

}