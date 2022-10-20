package engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    private int[] answer;

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
