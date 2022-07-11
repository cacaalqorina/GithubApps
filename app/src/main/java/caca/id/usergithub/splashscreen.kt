package caca.id.usergithub

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import caca.id.usergithub.main.MainActivity

@SuppressLint("CustomSplashScreen")
class splashscreen : AppCompatActivity() {
    private val detik = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@splashscreen, MainActivity::class.java))
            finish()
        },detik)


    }
}