package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    QuizRepository quizRepository;

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll().isEmpty() ? List.of() : quizRepository.findAll();
    }

    @PostMapping()
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        quizRepository.save(quiz);
        return quiz;
    }

    @PostMapping("/{id}/solve")
    public QuizResult answerQuiz(@PathVariable int id,@RequestBody Answer answer) {

        var quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var find = quiz.getAnswer(answer.getAnswer());
        return find ?
                new QuizResult(true, "Congratulations, you're right!")
                : new QuizResult(false, "Wrong answer! Please, try again.");
    }
}
