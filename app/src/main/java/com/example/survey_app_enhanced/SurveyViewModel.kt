package com.example.survey_app_enhanced

import androidx.lifecycle.ViewModel  // imports listed

class SurveyViewModel: ViewModel() {  // creates class SurveyViewModel that is a ViewModel

    var currentIndex = 0  // initializes the listed variables
    var yesNumber = 0
    var noNumber = 0

    private val questionBank= listOf(  // initializes a questionBank variable to hold 4 questions.  Questions referenced from strings.xml file
        Question(R.string.question_1),
        Question(R.string.question_2),
        Question(R.string.question_3),
        Question(R.string.question_4)
    )

    val currentQuestionText: Int  // initializes currentQuestionText variable (int type)
        get() = questionBank[currentIndex].textResID  // gets the current question from the questionBank as a resource ID (int) of the string resource

    fun moveToNext() {  // defines the moveToNext function
        currentIndex = (currentIndex + 1) % questionBank.size  // increments the currentIndex to update the current question displayed in the TextView
    }

    fun clickYes(): Int {  // defines the clickYes function
        yesNumber++  // adds 1 to yesNumber upon button click
        return yesNumber  // returns the yesNumber variable as an Int

    }

    fun clickNo(): Int {  // defines the clickNo function
        noNumber++  // adds 1 to the noNumber upon button click
        return noNumber  // returns noNumber variable as an Int
    }

    fun reset(): Pair<Int, Int> {  // defines the reset function
        yesNumber = 0  // sets yesNumber to 0
        noNumber = 0 // sets noNumber to 0
        return Pair(yesNumber,noNumber)  // returns yesNumber and noNumber variables

    }


}