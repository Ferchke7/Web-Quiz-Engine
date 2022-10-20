package engine;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Quiz {

   private int id;
   @NotNull
   private String title;
   @NotNull
   private String text;
   @NotNull
   @Size(min = 2)
   private String[] options;
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   private int[] answer;

   @Autowired
   public boolean getAnswer(int[] answer){
      boolean result = false;
      if(Arrays.equals(null,this.answer)){
         setAnswer(new int[0]);
      }
      if(answer.length == 0 && this.answer.length == 0) {
         result = true;
      }

      else if(this.answer.length == 1 && answer.length == 1) {
         int r = answer[0];
         for (int j : this.answer) {

            if (r == j) {
               result = true;
               break;
            }

         }
      }
      else if(answer.length >= 2) {
         Arrays.sort(answer);
         Arrays.sort(this.answer);
         if(Arrays.equals(answer,this.answer)) {
            result = true;
         }

      }

      return result;
   }
}
