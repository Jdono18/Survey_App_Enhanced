package com.example.survey_app_enhanced

import android.content.Intent  // imports the following
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class SurveyResultActivity : AppCompatActivity() {

    private lateinit var resultsTxt: EditText  // initializes the variables and the listed UI elements
    private lateinit var yesAnswersTxt: EditText
    private lateinit var noAnswersTxt: EditText
    private lateinit var yesCount: TextView
    private lateinit var noCount: TextView
    private lateinit var continueSurvey: Button
    private lateinit var resetButton: Button

    private val surveyViewModel: SurveyViewModel  // initializes the surveyViewModel variable that holds the SurveyViewModel
        get() = ViewModelProvider(this).get(SurveyViewModel::class.java)  // returns an instance of the SurveyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_result)

        resultsTxt = findViewById(R.id.results_Txt)  // ties listed UI element variables to their resource ids
        yesAnswersTxt = findViewById(R.id.yes_Answers_Text)
        noAnswersTxt = findViewById(R.id.no_Answers_Text)
        yesCount = findViewById(R.id.yesCount)
        noCount = findViewById(R.id.noCount)
        continueSurvey = findViewById(R.id.back_Button)
        resetButton = findViewById(R.id.resetValuesButton)

        val yesData = intent.getIntExtra(YES_DATA, 2)  // initializes yesData variable to hold packaged IntExtra intent holding YES_DATA key const and default value
        val noData = intent.getIntExtra(NO_DATA, 2)  // initializes noData variable to hold packaged IntExtra intent holding NO_DATA key const and default value)

        yesCount.text = yesData.toString()  // sets yesCount TextView text value to yesData int value converted to string
        noCount.text = noData.toString()  // sets noCount TextView text value to noData int value converted to string

        continueSurvey.setOnClickListener {  // calls finish SurveyResultActivity when user clicks continueSurvey button
            finish()
        }

        resetButton.setOnClickListener {  // resetButton onClickListener that calls surveyViewModel.moveToNext function and packages an intent to move from SurveyResultActivity to MainActivity
            surveyViewModel.moveToNext()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)  // passes intent in startActivity call
        }
    }
}