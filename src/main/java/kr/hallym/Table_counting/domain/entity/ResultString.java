package kr.hallym.Table_counting.domain.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultString {
    private Tableing table;
    private String result;
    private String imgSrc;
    public ResultString(Tableing table){
        this.table=table;
        if(table.getPk().getKey_value()==0) {
            if (table.getPk().getMinor() == 1)
                result = "1번 그린룸은 현재 사용 중이 아닙니다.";
            else if (table.getPk().getMinor() == 2)
                result = "2번 그린룸은 현재 사용 중이 아닙니다.";
            else if (table.getPk().getMinor() == 3)
                result = "3번 그린룸은 현재 사용 중이 아닙니다.";
            else if (table.getPk().getMinor() == 4)
                result = "4번 그린룸은 현재 사용 중이 아닙니다.";
            imgSrc = "./images/table.png";
        }else{
            if (table.getPk().getMinor() == 1)
                result = "1번 그린룸은 현재 사용 중 입니다.";
            else if (table.getPk().getMinor() == 2)
                result = "2번 그린룸은 현재 사용 중 입니다.";
            else if (table.getPk().getMinor() == 3)
                result = "3번 그린룸은 현재 사용 중 입니다.";
            else if (table.getPk().getMinor() == 4)
                result = "4번 그린룸은 현재 사용 중 입니다.";
            imgSrc = "./images/meeting.png";
        }
    }
}
