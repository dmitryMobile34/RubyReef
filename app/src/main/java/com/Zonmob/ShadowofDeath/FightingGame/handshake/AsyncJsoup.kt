package com.Zonmob.ShadowofDeath.FightingGame.handshake

import android.content.Context
import com.Zonmob.ShadowofDeath.FightingGame.handshake.Constants.C11
import com.Zonmob.ShadowofDeath.FightingGame.handshake.Constants.DL1
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class AsyncJsoup (val context: Context) {

    private var jsoup: String? = "null"
    private val hawk : String? = Hawk.get(C11,"null")
    private val hawkAppLink: String? = Hawk.get(DL1, "null")

    private var forJsoupSetNaming: String = Constants.lru + Constants.carryMe + hawk
    private var forJsoupSetAppLnk: String = Constants.lru + Constants.carryMe + hawkAppLink

    suspend fun getDocSecretKey(): String?{
        withContext(Dispatchers.IO){
            if(hawk!=null) {
                val doc = Jsoup.connect(forJsoupSetNaming).get()
                jsoup = doc.text().toString()
            } else {
                val doc = Jsoup.connect(forJsoupSetAppLnk).get()
                jsoup = doc.text().toString()
            }
        }
        return jsoup
    }
}