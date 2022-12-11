package com.miu.mdp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.miu.mdp.data.Certification
import com.miu.mdp.data.Contact
import com.miu.mdp.data.Education
import com.miu.mdp.data.Work
import com.miu.mdp.utils.SharedPrefConstants.CERTIFICATIONS
import com.miu.mdp.utils.SharedPrefConstants.CONTACTS
import com.miu.mdp.utils.SharedPrefConstants.EDUCATIONS
import com.miu.mdp.utils.SharedPrefConstants.WORK_EXPERIENCES


object SharedPrefsUtil {
    private var instance: SharedPreferences? = null

    private fun getSharedPrefInstance(context: Context): SharedPreferences? {
        if (instance ==null) instance = context.getSharedPreferences("pref", MODE_PRIVATE)
        return instance
    }

    fun saveListItem(context: Context, obzect: Any, key: String) {
        getSharedPrefInstance(context)
        var list: MutableList<Any>? = retrieveList(context, key)
        list?.add(obzect) ?: kotlin.run {
            list = mutableListOf(obzect)
        }
        val sharedPreEditor = instance?.edit()
        val gson = Gson()
        val set = mutableSetOf<String>()

        for (item in list!!) {
            set.add(gson.toJson(item))
        }
        sharedPreEditor?.putStringSet(key, set)
        sharedPreEditor?.apply()
    }

    fun retrieveList(context: Context, key: String): MutableList<Any>? {
        getSharedPrefInstance(context)
        val set = instance?.getStringSet(key, null) ?: return null

        val gson = Gson()
        val list = mutableListOf<Any>()
        val instance = when(key){
            CERTIFICATIONS -> Certification::class.java
            EDUCATIONS -> Education::class.java
            WORK_EXPERIENCES -> Work::class.java
            CONTACTS -> Contact::class.java
            else -> {null}
        }
        for (json in set) {
            val item = gson.fromJson(json, instance)
            list.add(item)
        }
        return list
    }

    fun saveUserLoggedInStatus(context: Context,key: String,isLoggedIn:Boolean){
        getSharedPrefInstance(context)
        val sharedPreEditor = instance?.edit()
        sharedPreEditor?.putBoolean(key,isLoggedIn)
        sharedPreEditor?.apply()
    }

    fun isUserLoggedIn(context: Context,key: String): Boolean? {
        getSharedPrefInstance(context)
        return instance?.getBoolean(key,false)
    }
}

object SharedPrefConstants{
    const val EDUCATIONS = "educations"
    const val CERTIFICATIONS = "certifications"
    const val WORK_EXPERIENCES = "work_experiences"
    const val CONTACTS = "contacts"

    const val IS_LOGGED_IN = "is_logged_in"
}