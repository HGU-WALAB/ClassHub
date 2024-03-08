package com.example.classhub.domain.lectureroom.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class LectureRoomCreateRequest {
    private String lectureRoomName;
    private boolean OnOff;

    // 태그에 필요한 것들을 매칭
    private String tagName;
}
