package com.fetulhak.quiz_service.feign;

import com.fetulhak.quiz_service.model.QuestionWrapper;
import com.fetulhak.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")

public interface QuizInterface {
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam String category,
            @RequestParam int numQuestions);

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(
            @RequestBody List<Integer> ids);


    //getscore also must be in question service
    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getQuizScore(@RequestBody List<Response> responses);
}
