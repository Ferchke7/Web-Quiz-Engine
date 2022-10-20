package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final List<Quiz> quizzes = new ArrayList<>();
    private int counter = 1;

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizzes.stream().filter(d -> d.getId() == id).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public List<Quiz> getAllQuizzes() {
        return quizzes.isEmpty() ? List.of() : quizzes;
    }

    @PostMapping()
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        quiz.setId(counter++);
        quizzes.add(quiz);
        return quiz;
    }

    @PostMapping("/{id}/solve")
    public QuizResult answerQuiz(@PathVariable int id,@RequestBody Answer answer) {
        int[] x = null;
        if(Arrays.equals(x, answer.getAnswer())) {
            answer.setAnswer(new int[0]);
        }
        var quiz = quizzes.stream().filter(q -> q.getId() == id).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var find = quiz.getAnswer(answer.getAnswer());
        return find ?
                new QuizResult(true, "Congratulations, you're right!")
                : new QuizResult(false, "Wrong answer! Please, try again.");
    }
}
