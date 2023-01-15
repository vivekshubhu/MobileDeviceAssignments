package com.miu.mdp.data.local.mock

import com.miu.mdp.data.local.entity.QuizEntity

// default 15 android quiz questions list
fun defaultQuizQuestions() = listOf<QuizEntity>(
    QuizEntity(
        id = 1,
        question = "Choose the correct option related to Android.",
        options = listOf("Operating System", "Application", "Language", "SDK"),
        answer = "a",
    ),
    QuizEntity(
        id = 2,
        question = "What is an activity in android?",
        options = listOf("android package", "A single screen in an application with supporting java code", "android class", "None of the above"),
        answer = "b",
    ),
    QuizEntity(
        id = 3,
        question = "Among the following options choose the one for which Android is based on Linux.",
        options = listOf("Networking", "Portability", "Networking", "All of the above"),
        answer = "d",
    ),
    QuizEntity(
        id = 4,
        question = "Which of the following is not a layout in Android?",
        options = listOf("TableLayout", "GridLayout", "LinearLayout", "None of the above"),
        answer = "d",
    ),
    QuizEntity(
        id = 5,
        question = "Among the below virtual machines choose the one which is used by the Android operating system",
        options = listOf(
            "Dalvik operating system",
            "JVM",
            "Simple Virtual Machine",
            "None"
        ),
        answer = "a",
    ),
    QuizEntity(
        id = 6,
        question = "APK stands for ______",
        options = listOf(
            "Android Package Kit",
            "Android Package Key",
            "Android Package Kernel",
            "Android Package Keep"
        ),
        answer = "a",
    ),
    QuizEntity(
        id = 7,
        question = "Identify the language on which Android is based upon.",
        options = listOf("Python", "C++", "Java", "None"),
        answer = "c",
    ),
    QuizEntity(
        id = 8,
        question = "Which of the following is not a type of Service?",
        options = listOf("Started Service", "Bound Service", "None of the above", "Both a and b"),
        answer = "c",
    ),
    QuizEntity(
        id = 9,
        question = "Which of the following is not a type of Broadcast Receiver?",
        options = listOf(
            "Ordered Broadcast",
            "Sticky Broadcast",
            "None of the above",
            "Both a and b"
        ),
        answer = "c",
    ),
    QuizEntity(
        id = 10,
        question = "In android, mini activities are also known as",
        options = listOf("Adapter", "Activity", "Fragments", "None"),
        answer = "c",
    ),
    QuizEntity(
        id = 11,
        question = "hich of the following is not an activity lifecycle callback method?",
        options = listOf("onCreate()", "onStart()", "onResume()", "onBack()"),
        answer = "d",
    ),
    QuizEntity(
        id = 12,
        question = "What is the minimum API level required to use Android's ConstraintLayout?",
        options = listOf("API 14", "API 15", "API 16", "API 17"),
        answer = "c",
    ),
    QuizEntity(
        id = 13,
        question = "Choose the option which is contained in the src folder",
        options = listOf("Manifest", "Java Source Code", "XML", "All of them"),
        answer = "b",
    ),

    QuizEntity(
        id = 14,
        question = "Which of the following is not a valid resource type in Android?",
        options = listOf("drawable", "string", "layout", "widget"),
        answer = "d",
    ),
    QuizEntity(
        id = 15,
        question = "Which of the following is not a valid layout attribute in Android?",
        options = listOf("layout_width", "layout_height", "layout_margin", "layout_padding"),
        answer = "d",
    ),
)
