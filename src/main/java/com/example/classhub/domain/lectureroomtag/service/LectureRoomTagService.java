package com.example.classhub.domain.lectureroomtag.service;

import com.example.classhub.domain.lectureroomtag.LectureRoomTag;
import com.example.classhub.domain.lectureroomtag.dto.LectureRoomTagDto;
import com.example.classhub.domain.lectureroomtag.repository.LectureRoomTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureRoomTagService {
    private final LectureRoomTagRepository lectureRoomTagRepository;


}
