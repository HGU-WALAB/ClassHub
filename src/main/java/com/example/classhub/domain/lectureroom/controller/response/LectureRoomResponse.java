package com.example.classhub.domain.lectureroom.controller.response;

import com.example.classhub.domain.lectureroom.LectureRoom;
import com.example.classhub.domain.lectureroom.dto.LectureRoomDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRoomResponse {
    private Long lectureRoomId;
    private String l_room_name;
    private String taInviteCode;
    private String stInviteCode;
    private boolean onOff;

    public LectureRoomResponse(LectureRoom lectureRoom){
        this.lectureRoomId = lectureRoom.getLectureRoomId();
        this.l_room_name = lectureRoom.getL_room_name();
        this.taInviteCode = lectureRoom.getTaInviteCode();
        this.stInviteCode = lectureRoom.getStInviteCode();
        this.onOff = lectureRoom.isOnOff();
    }

    public LectureRoomResponse(LectureRoomDto lectureRoomDto){
        this.lectureRoomId = lectureRoomDto.getLectureRoomId();
        this.l_room_name = lectureRoomDto.getL_room_name();
        this.taInviteCode = lectureRoomDto.getTaInviteCode();
        this.stInviteCode = lectureRoomDto.getStInviteCode();
        this.onOff = lectureRoomDto.isOnOff();
    }
}
