package kr.tjeit.colosseum.utils

import android.content.Context

class ContextUtil {

    companion object {
        private val prefName = "colosseumPreference"

        private val USER_TOKEN = "USER_TOKEN"
        private val IS_AUTO_LOGIN = "IS_AUTO_LOGIN"

        fun setUserToken(context: Context, token:String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(USER_TOKEN, token).apply()
        }

        fun getUserToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN, "")!!
        }


        fun setAutoLogin(context: Context, isAuto:Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(IS_AUTO_LOGIN, isAuto).apply()
        }

        fun isAutoLogin(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(IS_AUTO_LOGIN, false)
        }


    }

}