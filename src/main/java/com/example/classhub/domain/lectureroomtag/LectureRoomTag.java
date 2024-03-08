package com.example.classhub.domain.lectureroomtag;

import com.example.classhub.domain.BaseEntity;
import com.example.classhub.domain.lectureroom.LectureRoom;
import com.example.classhub.domain.lectureroom.dto.LectureRoomDto;
import com.example.classhub.domain.lectureroomtag.dto.LectureRoomTagDto;
import com.example.classhub.domain.tag.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureRoomTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureRoomTagId;

    private float average;
    private float standardDeviation;

    @ManyToOne
    @JoinColumn(name = "tagId")
    private Tag tag;


    @ManyToOne
    @JoinColumn(name = "lectureRoomId")
    private LectureRoom lectureRoom;

}
