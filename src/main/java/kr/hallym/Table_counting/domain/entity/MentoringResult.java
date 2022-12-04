package kr.hallym.Table_counting.domain.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MentoringResult {
    private Tableing table;
    private String result;
    private String imgSrc;
    public MentoringResult(Tableing table){
        this.table=table;
        if(table.getPk().getKey_value()==0) {
            if (table.getPk().getMinor() == 11)
                result = "1번 멘토링실은 현재 사용 중이 아닙니다.";
            else if (table.getPk().getMinor() == 12)
                result = "2번 멘토링실은 현재 사용 중이 아닙니다.";
            else if (table.getPk().getMinor() == 13)
                result = "3번 멘토링실은 현재 사용 중이 아닙니다.";
            else if (table.getPk().getMinor() == 14)
                result = "4번 멘토링실은 현재 사용 중이 아닙니다.";
            imgSrc = "./images/table.png";
        }else{
            if (table.getPk().getMinor() == 11)
                result = "1번 멘토링실은 현재 사용 중 입니다.";
            else if (table.getPk().getMinor() == 12)
                result = "2번 멘토링실은 현재 사용 중 입니다.";
            else if (table.getPk().getMinor() == 13)
                result = "3번 멘토링실은 현재 사용 중 입니다.";
            else if (table.getPk().getMinor() == 14)
                result = "4번 멘토링실은 현재 사용 중 입니다.";
            imgSrc = "./images/meeting.png";
        }
    }
}
