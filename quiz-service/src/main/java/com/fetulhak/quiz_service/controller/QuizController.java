package com.fetulhak.quiz_service.controller;


import com.fetulhak.quiz_service.dto.QuizDto;
import com.fetulhak.quiz_service.model.QuestionWrapper;
import com.fetulhak.quiz_service.model.Response;
import com.fetulhak.quiz_service.service.QuizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

   @Autowired
    private QuizeService quizeService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizeService.createQuiz(quizDto.getCategoryName(),
                quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizeService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,
                                              @RequestBody List<Response> responses) {

        return quizeService.calculateResult(id,responses);
    }


}
