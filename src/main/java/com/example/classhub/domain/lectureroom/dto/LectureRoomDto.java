package com.example.classhub.domain.lectureroom.dto;

import com.example.classhub.domain.lectureroom.LectureRoom;
import com.example.classhub.domain.lectureroom.controller.request.LectureRoomCreateRequest;
import com.example.classhub.domain.lectureroom.controller.request.LectureRoomUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LectureRoomDto {
    private Long lectureRoomId;
    private String lectureRoomName;
    private String taInviteCode;
    private String stInviteCode;
    private boolean onOff;
    private String tagName;

    public static LectureRoomDto from(LectureRoomCreateRequest request) {
        return LectureRoomDto.builder()
                .lectureRoomName(request.getLectureRoomName())
                .onOff(request.isOnOff())
//                .tagName(request.getTagName())
                .build();
    }
    public static LectureRoomDto from(LectureRoomUpdateRequest request) {
        return LectureRoomDto.builder()
                .lectureRoomName(request.getLectureRoomName())
                .onOff(request.isOnOff())
                .build();
    }

    public static LectureRoomDto from(LectureRoom lectureRoom){
        return LectureRoomDto.builder()
                .lectureRoomId(lectureRoom.getLectureRoomId())
                .lectureRoomName(lectureRoom.getLectureRoomName())
                .taInviteCode(lectureRoom.getTaInviteCode())
                .stInviteCode(lectureRoom.getStInviteCode())
                .onOff(lectureRoom.isOnOff())
                .build();
    }
}
