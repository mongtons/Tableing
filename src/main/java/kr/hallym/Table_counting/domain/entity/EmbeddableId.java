package kr.hallym.Table_counting.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmbeddableId implements Serializable {
    @Column(length = 11)
    public int key_value;
    @Column(length = 11)
    public int minor;
}
