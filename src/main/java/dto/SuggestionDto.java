package dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SuggestionDto {
    private Long code;
    private Long pos;
    private Long row;
    private Long col;
    private Long len;
    private String word;
    private List<String> s;
}