package com.miu.mdp.ui.main.nav.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.mdp.utils.HomeConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val introDatas : MutableStateFlow<List<Pair<String,String?>>> = MutableStateFlow(mutableListOf())
    val toolsAndTechnology : MutableStateFlow<MutableList<Pair<String,MutableList<String>>>> = MutableStateFlow(mutableListOf())
    init {
        viewModelScope.launch {
            introDatas.emit(mutableListOf(
                Pair(HomeConstants.FULL_NAME, "Vivek Karki"),
                Pair(HomeConstants.INTRO, "Software Engineer"),
                Pair(HomeConstants.CAREER_NOTE, "I have completed my on-campus studies at Maharishi International University and I am doing my distance education now. Currently, I am on my CPT as well by which I can work full time as a W-2 employee"),
            ))

            toolsAndTechnology.emit(mutableListOf(
                Pair(HomeConstants.LANGUAGES, mutableListOf("Java","Php","Kotlin",
                    "Javascript")),
                Pair(HomeConstants.FRAMEWORKS_WEB, mutableListOf("Spring", "Spring Boot","Hibernate","Laravel", "React")),
                Pair(HomeConstants.MICROSERVICES_CLOUD, mutableListOf("AWS(s3 Bucket)", "kafka", "OpenFeign")),
                Pair(HomeConstants.DATABASES, mutableListOf("PostgreSql", "MySql", "Oracle")),
                Pair(HomeConstants.TOOLS, mutableListOf("Intellij","Gradle","VsCode","Github")),
            ))

        }

    }



}