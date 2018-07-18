package ru.nb.mish.photostudioex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class AboutMeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.about_me)


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        overridePendingTransition(R.anim.activity_in_right, R.anim.activity_exit_right) // анимация
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // ставим для кнопки назад также анимацию
        overridePendingTransition(R.anim.activity_in_right, R.anim.activity_exit_right)
    }
}
