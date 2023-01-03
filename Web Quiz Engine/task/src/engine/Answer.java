package engine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Answer {

    private List<Integer> answer;

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
