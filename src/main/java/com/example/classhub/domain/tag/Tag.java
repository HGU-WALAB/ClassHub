package com.example.classhub.domain.tag;

import com.example.classhub.domain.BaseEntity;
import com.example.classhub.domain.lectureroom.LectureRoom;
import com.example.classhub.domain.tag.dto.TagDto;
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
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "lectureRoomId")
    private LectureRoom lectureRoom;

//    public void changeLecture(LectureRoom lectureRoom){ // Tag에서 lectureRoom을 변경해줄 때 해당 lectureRoom에서의 값을 변경
//        lectureRoom.getTags().add(this);
//        this.lectureRoom = lectureRoom;
//    }

    public static Tag from(TagDto tagDto, LectureRoom lectureRoom) {
        return Tag.builder()
                .name(tagDto.getName())
                .lectureRoom(lectureRoom)
                .build();
    }

    public static Tag from(TagDto tagDto) {
        return Tag.builder()
                .name(tagDto.getName())
                .build();
    }

    public void update(TagDto tagDto) {
        this.name = tagDto.getName();
    }
}

