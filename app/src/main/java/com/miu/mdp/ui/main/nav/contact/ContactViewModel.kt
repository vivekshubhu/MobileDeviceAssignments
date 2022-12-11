package com.miu.mdp.ui.main.nav.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.miu.mdp.data.Contact
import com.miu.mdp.utils.SharedPrefConstants
import com.miu.mdp.utils.SharedPrefsUtil

class ContactViewModel(context: Application) : AndroidViewModel(context) {
    val contactList = mutableListOf<Contact>()
    init {
        val cList = SharedPrefsUtil.retrieveList(context, SharedPrefConstants.CONTACTS)
        cList?.let {
            for (item in cList) {
                if (item is Contact) {
                    contactList.add(item)
                }
            }
        }
    }
}