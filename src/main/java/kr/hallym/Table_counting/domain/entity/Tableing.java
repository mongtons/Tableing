package kr.hallym.Table_counting.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tableing")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Tableing {
    @EmbeddedId
    public EmbeddableId pk;
    @Column(length = 11)
    public int major;
    @Column(length = 20)
    public String time;
}
