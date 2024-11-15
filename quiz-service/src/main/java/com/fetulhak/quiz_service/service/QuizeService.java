package com.fetulhak.quiz_service.service;


import com.fetulhak.quiz_service.feign.QuizInterface;
import com.fetulhak.quiz_service.model.QuestionWrapper;
import com.fetulhak.quiz_service.model.Quiz;
import com.fetulhak.quiz_service.model.Response;
import com.fetulhak.quiz_service.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizeService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category,
                                             int numOfQuestions,
                                             String title) {
//        List<Integer> questions = questionRepo.findRandomQuestionsByCategory(
//                category,numOfQuestions
//        );
        //call the generate url from the auestion-service
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numOfQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizRepo.save(quiz);

        return ResponseEntity.ok("Quiz created");

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
            Quiz quiz = quizRepo.findById(id).get();
            List<Integer> questionIds = quiz.getQuestionsIds();
            ResponseEntity<List<QuestionWrapper>> questions=
                    quizInterface.getQuestionsFromId(questionIds);



            return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        return  quizInterface.getQuizScore(responses);

    }
}
