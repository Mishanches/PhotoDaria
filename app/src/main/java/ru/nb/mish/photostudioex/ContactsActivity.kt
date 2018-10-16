package ru.nb.mish.photostudioex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.communicate)

        val tvDisplay = findViewById(R.id.tvEmailText) as TextView
        val data = R.string.email_text

        if (tvDisplay != null) {
            tvDisplay!!.setText(data)
            Linkify.addLinks(tvDisplay!!, Linkify.ALL)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        overridePendingTransition(R.anim.activity_in_right, R.anim.activity_exit_right)
        return true
    }

    override fun onBackPressed() {
        overridePendingTransition(R.anim.activity_in_right, R.anim.activity_exit_right)
        super.onBackPressed()
    }
}