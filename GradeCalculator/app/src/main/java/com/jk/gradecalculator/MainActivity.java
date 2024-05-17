package com.jk.gradecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText subjectNameEditText, subjectMarksEditText;
    private Button addSubjectButton;
    private TextView totalMarksTextView, averagePercentageTextView, gradeTextView, gpaTextView, feedbackTextView;
    private int totalMarks = 0, numSubjects = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectNameEditText = findViewById(R.id.subjectNameEditText);
        subjectMarksEditText = findViewById(R.id.subjectMarksEditText);
        addSubjectButton = findViewById(R.id.addSubjectButton);
        totalMarksTextView = findViewById(R.id.totalMarksTextView);
        averagePercentageTextView = findViewById(R.id.averagePercentageTextView);
        gradeTextView = findViewById(R.id.gradeTextView);
        gpaTextView = findViewById(R.id.gpaTextView);
        feedbackTextView = findViewById(R.id.feedbackTextView);

        addSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubject();
            }
        });
    }

    private void addSubject() {
        String subjectName = subjectNameEditText.getText().toString();
        int marks = Integer.parseInt(subjectMarksEditText.getText().toString());

        // Update total marks and number of subjects
        totalMarks += marks;
        numSubjects++;

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Calculate GPA
        double gpa = averagePercentage / 10.0;

        // Determine grade
        String grade;
        if (averagePercentage >= 90) {
            grade = "O";
        } else if (averagePercentage >= 80) {
            grade = "A+";
        } else if (averagePercentage >= 70) {
            grade = "A";
        } else if (averagePercentage >= 60) {
            grade = "B+";
        } else if (averagePercentage >= 50) {
            grade = "B";
        } else {
            grade = "U";
        }

        // Provide feedback based on grade obtained
        String feedback;
        switch (grade) {
            case "O":
                feedback = "Excellent performance!";
                break;
            case "A+":
            case "A":
                feedback = "Great job!";
                break;
            case "B+":
                feedback = "Good effort!";
                break;
            case "B":
                feedback = "Keep it up!";
                break;
            case "U":
                feedback = "You can do better!";
                break;
            default:
                feedback = "Invalid grade.";
        }

        // Display results
        totalMarksTextView.setText("Total Marks: " + totalMarks);
        averagePercentageTextView.setText("Average Percentage: " + averagePercentage + "%");
        gradeTextView.setText("Grade: " + grade);
        gpaTextView.setText("GPA: " + gpa);
        feedbackTextView.setText("Feedback: " + feedback);

        // Clear input fields
        subjectNameEditText.setText("");
        subjectMarksEditText.setText("");
    }
}
