package com.miu.mdp.ui.main.nav.work

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.miu.mdp.data.Work
import com.miu.mdp.utils.SharedPrefConstants
import com.miu.mdp.utils.SharedPrefsUtil

class WorkViewModel(context: Application) : AndroidViewModel(context) {
    val workExperienceList = mutableListOf<Work>()

    init {
        val workList = SharedPrefsUtil.retrieveList(context, SharedPrefConstants.WORK_EXPERIENCES)
        workList?.let {
            for (item in workList) {
                if (item is Work) {
                    workExperienceList.add(item)
                }
            }
        }
    }
}