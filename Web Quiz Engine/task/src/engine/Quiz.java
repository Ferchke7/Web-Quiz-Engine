package engine;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Quiz {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @NotNull
   private String title;
   @NotNull
   private String text;
   @NotNull
   @Size(min = 2)
   @ElementCollection
   private List<String> options;
   @ElementCollection
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   private List<Integer> answer;


   public boolean getAnswer(List<Integer> answer){
      boolean result = false;
      if(!answer.isEmpty()) {
         if(answer.size() == 1 && this.answer.size() == 1) {
            result = this.answer.contains(answer.get(0));
         }
         else {
            Collections.sort(this.answer);
            Collections.sort(answer);
            result = answer.equals(this.answer);
         }
      }
      else if (CollectionUtils.isEmpty(answer) && CollectionUtils.isEmpty(this.answer)) {
         result = true;
      }
      return result;
   }
}
