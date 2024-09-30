package com.example.survey_app_enhanced

import android.content.Intent
import android.os.Bundle  // imports the following
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

const val SURVEY_QUESTION_KEY = "survey-question-bundle-key"  // initializes constant variables for holding bundle keys
const val YES_KEY = "yes-answer-bundle-key"
const val NO_KEY = "no-answer-bundle-key"

const val YES_DATA = "com.example.survey_app_enhanced.YES"
const val NO_DATA = "com.example.survey_app_enhanced.NO"



class MainActivity : AppCompatActivity() {

    private lateinit var questionView: TextView  // initializes variables for the listed textview and buttons
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var yesCount: TextView
    private lateinit var noCount: TextView
    private lateinit var resetButton: Button

    private val surveyViewModel: SurveyViewModel  // initializes the surveyViewModel variable that holds the SurveyViewModel
        get() = ViewModelProvider(this).get(SurveyViewModel::class.java)  // returns an instance of the SurveyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {  // overrides the onCreate to call onCreate and pass in the received bundle
        super.onCreate(savedInstanceState)  // reads the data in the bundle back
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(SURVEY_QUESTION_KEY, 0) ?: 0 // writes the value of the currentIndex to the bundle with the constant as it's key
        surveyViewModel.currentIndex = currentIndex // if data exists in the bundle it will be set as currentIndex otherwise a default value of 0 will be used if it does not exist or is null

        val yesNumber = savedInstanceState?.getInt(YES_KEY, 0 ) ?: 0  // writes the value of yesNumber to the bundle with the constant as it's key
        surveyViewModel.yesNumber = yesNumber  // if data exists in the bundle it will be set as yesNumber otherwise a default value of 0 will be used if it does not exist or is null


        val noNumber = savedInstanceState?.getInt(NO_KEY, 0 ) ?: 0  // writes the value of the noNumber to the bundle with the constant as it's key
        surveyViewModel.noNumber = noNumber  // if data exists in the bundle it will be set as noNumber otherwise a default value of 0 will be used if it does not exist or is null


        questionView = findViewById(R.id.question_Text)  // ties the listed variables to the listed resource IDs
        yesButton = findViewById(R.id.yes_Button)
        noButton = findViewById(R.id.no_Button)
        yesCount = findViewById(R.id.yes_Count)
        noCount = findViewById(R.id.no_Count)
        resetButton = findViewById(R.id.resultsButton)

        yesButton.setOnClickListener {  // onClickListener for yesButton
            surveyViewModel.clickYes()  // calls the clickYes function in SurveyViewModel
            yesCount.text = surveyViewModel.yesNumber.toString()  // converts yesNumber to a string and displays it in the yesCount TextView
        }

        noButton.setOnClickListener {
            surveyViewModel.clickNo()
            noCount.text = surveyViewModel.noNumber.toString()
        }

        resetButton.setOnClickListener {  // onClickListener for the resetButton
            //surveyViewModel.reset()  // calls the reset function in SurveyViewModel
            //yesCount.text = ""  // sets yesCount TextView to empty string
            //noCount.text = ""  // sets noCount TextView to empty string
            //surveyViewModel.moveToNext()  // calls the moveToNext function in Survey ViewModel
            //updateQuestion()  // calls updateQuestion function
            passValues()
        }

        questionView.setOnClickListener {
            surveyViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()  // calls updateQuestion function
    }

    override fun onSaveInstanceState(outState: Bundle) {  // overrides onSaveInstanceState to write the listed values to the bundle using their keys
        super.onSaveInstanceState(outState)
        outState.putInt(SURVEY_QUESTION_KEY, surveyViewModel.currentIndex)  // writes the data from SurveyViewModel.currentIndex to bundle
        outState.putInt(YES_KEY, surveyViewModel.yesNumber)  // writes the data from SurveyViewModel.yesNumber to the bundle
        outState.putInt(NO_KEY, surveyViewModel.noNumber)  // writes the data from SurveyViewModel.noNumber to the bundle
    }

    private fun updateQuestion() {  // defines updateQuestion function
        val questionTextResID = surveyViewModel.currentQuestionText  // initializes questionTextResID variable that holds the currentQuestion in the SurveyViewModel
        questionView.setText(questionTextResID)  // sets the questionView TextView to current question
        yesCount.text = surveyViewModel.yesNumber.toString()  // sets the yesCount TextView to the yesNumber in SurveyViewModel after converting to a string
        noCount.text = surveyViewModel.noNumber.toString()  // sets the noCount TextView to the noNumber in the SurveyViewModel after converting to a string
    }

    private fun passValues() {  // defines passValues function
        val passValuesIntent = Intent(this, SurveyResultActivity::class.java)  // initializes passValuesIntent Intent that passes intent from this Activity to SurveyResultActivity
        passValuesIntent.putExtra(YES_DATA, surveyViewModel.yesNumber)  // packages the intent as an extra holding a key value pair that includes: key string and value from surveyViewModel.yesNumber
        passValuesIntent.putExtra(NO_DATA, surveyViewModel.noNumber)  // packages the intent as an extra holding a key value pair that includes: key string and value from surveyViewModel.noNumber
        startActivity(passValuesIntent)  // calls startActivity and passes the packaged intent from MainActivity to SurveyResultActivity
    }

    /*private fun handleSurveyResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val yes = intent?.getIntExtra(YES_RESULTS, 0) ?: 0
            val no = intent?.getIntExtra(NO_RESULTS, 0) ?: 0
            yesCount.text = yes.toString()
            noCount.text = no.toString()
            }*/


    }
