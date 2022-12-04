package kr.hallym.Table_counting.dto;

import kr.hallym.Table_counting.domain.entity.EmbeddableId;
import kr.hallym.Table_counting.domain.entity.Tableing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class TableingForm {
    private EmbeddableId pk;
    private Integer major;
    private String time;
    public TableingForm(int key_value, int minor, int major){
        pk=new EmbeddableId(key_value, minor);
        this.major=major;
        this.time=time;
    }
    public Tableing toEntity(){
        return new Tableing(pk, major, time);
    }
}
