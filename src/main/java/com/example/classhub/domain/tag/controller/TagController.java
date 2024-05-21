package com.example.classhub.domain.tag.controller;

import com.example.classhub.domain.tag.controller.request.TagRequest;
import com.example.classhub.domain.tag.controller.response.TagListResponse;
import com.example.classhub.domain.tag.controller.response.TagResponse;
import com.example.classhub.domain.tag.dto.TagDto;
import com.example.classhub.domain.tag.service.TagService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("/tag/tagForm")
    public String createTagForm(Model model){
        model.addAttribute("tag", new TagRequest());
        return "tagForm";
    }
    @PostMapping("/tag/saveTag")
    public String createTag(TagRequest request){
        tagService.createTag(TagDto.from(request), request.getLRoomId());
        return "redirect:/tag";
    }

    @GetMapping("/tag")
    public String findTagList(Model model){
        TagListResponse tagListResponse = tagService.getTagList();
        model.addAttribute("tags", tagListResponse.getTags());
        return "index";
    }

//    @GetMapping("/tag/updateForm/{tagId}")
//    public String updateTagForm(@PathVariable Long tagId, Model model){
//        TagDto tagDto = tagService.findByTagId(tagId);
//        model.addAttribute("tag", tagDto);
//        return "tagUpdate";
//    }

    @PostMapping("/tag/update/{tagId}")
    public String updateTag(@PathVariable Long tagId, @RequestParam String tagName, RedirectAttributes redirectAttributes) {
        Long lRoomIdByTag = tagService.findLRoomIdByTagId(tagId);
        String beforeTagName = tagService.findByTagId(tagId).getName();
        boolean isTagNameExists = tagService.isTagNameExists(tagName, lRoomIdByTag);

        if (isTagNameExists) {
            return "redirect:/data-detail/statistics/" + tagId;
        } else {
            // TagDto의 생성자를 이용하여 객체 생성 및 초기화
            TagDto tagDto = new TagDto(tagId, tagName);
            tagService.update(tagId, tagDto); // 태그 업데이트 로직 수행
            return "redirect:/data-detail/statistics/" + tagId;
        }
    }

    @PostMapping("/tag/delete/{tagId}")
    public String tagDelete(@PathVariable(value = "tagId") Long tagId){
        String lRoomId = tagService.findLRoomIdByTagId(tagId).toString();
        tagService.tagDelete(tagId);
        return "redirect:/lecture-room/detail/" + lRoomId;
    }
}
