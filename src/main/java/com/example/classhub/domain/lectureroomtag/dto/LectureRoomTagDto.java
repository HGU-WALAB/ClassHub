package com.example.classhub.domain.lectureroomtag.dto;

import com.example.classhub.domain.lectureroom.dto.LectureRoomDto;
import com.example.classhub.domain.lectureroomtag.LectureRoomTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LectureRoomTagDto {
    private Long lectureRoomTagId;
    private Long lectureRoomId;
    private Long tagId;

    //Todo: dto service 사용
    public static LectureRoomTagDto from(LectureRoomTag lectureRoomTag){
        return LectureRoomTagDto.builder()
                .lectureRoomTagId(lectureRoomTag.getLectureRoomTagId())
                .lectureRoomId(lectureRoomTag.getLectureRoom().getLectureRoomId())
                .tagId(lectureRoomTag.getTag().getTagId())
                .build();
    }
}
