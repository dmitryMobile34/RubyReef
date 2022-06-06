package com.Zonmob.ShadowofDeath.FightingGame.handshake

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.Zonmob.ShadowofDeath.FightingGame.R
import com.Zonmob.ShadowofDeath.FightingGame.whitepower.MainPower
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.*

class HandMeInvite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand_me_invite)

        val textView: TextView = findViewById(R.id.txtMainMain)
        runBlocking {

            val job: Job = GlobalScope.launch(Dispatchers.IO) {
                getAsync(applicationContext)
            }
            job.join()
            val jsoup: String? = Hawk.get(Constants.aResult, "")
            Log.d("cora", "cora $jsoup")

            textView.text = jsoup

            if (jsoup == "0bDv") {
                Intent(applicationContext, MainPower::class.java).also { startActivity(it) }

            } else {
                Intent(applicationContext, BBRAct::class.java).also { startActivity(it) }
            }
            finish()
        }
    }

    private suspend fun getAsync(context: Context) {
        val asyncKey = AsyncJsoup(context)
        val asyncResult = asyncKey.getDocSecretKey()
        Hawk.put(Constants.aResult, asyncResult)
    }
}
