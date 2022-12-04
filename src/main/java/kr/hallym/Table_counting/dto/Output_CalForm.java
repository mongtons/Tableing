package kr.hallym.Table_counting.dto;

import kr.hallym.Table_counting.domain.entity.Output_Cal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Output_CalForm {
    private String time;
    private Integer in_area;
    private Float near_dist;
    public Output_Cal toEntity(){
        return new Output_Cal(time, in_area, near_dist);
    }
}
