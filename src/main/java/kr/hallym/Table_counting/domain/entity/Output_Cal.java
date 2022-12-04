package kr.hallym.Table_counting.domain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="output_cal")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Output_Cal {
    @Id
    public String time;
    @Column(length = 11)
    public Integer in_area;
    @Column
    public Float near_dist;
}
