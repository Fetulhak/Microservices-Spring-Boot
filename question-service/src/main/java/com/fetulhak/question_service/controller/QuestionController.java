package com.fetulhak.question_service.controller;



import com.fetulhak.question_service.model.Question;
import com.fetulhak.question_service.model.QuestionWrapper;
import com.fetulhak.question_service.model.Response;
import com.fetulhak.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

   @Autowired
    private QuestionService questionService;

   @Autowired
   Environment environment;



    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllAuestions(){
         return questionService.getAllAuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory( @PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
       return questionService.addQuestion(question);
    }


    @PostMapping("/addMultiple")
    public ResponseEntity<String> addQuestions(@RequestBody List<Question> questions) {
        for (Question question : questions) {
            questionService.addQuestion(question);
        }
        return ResponseEntity.ok("Questions added successfully!");
    }

    //generate questions based on question id

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam String category,
            @RequestParam int numQuestions){
        return questionService.getQuestionsForQuiz(category, numQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> ids){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(ids);

    }


    //getscore also must be in question service
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getQuizScore(@RequestBody List<Response> responses){

        return questionService.getQuizScore(responses);
    }




}
