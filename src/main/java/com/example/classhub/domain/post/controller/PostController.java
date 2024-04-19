package com.example.classhub.domain.post.controller;

import com.example.classhub.domain.post.controller.request.PostCheckRequest;
import com.example.classhub.domain.post.controller.request.PostCreateRequest;
import com.example.classhub.domain.post.controller.response.PostListResponse;
import com.example.classhub.domain.post.dto.PostDto;
import com.example.classhub.domain.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostController {
    private final PostService postService;

    @GetMapping("/post/postForm")
    public String createPostForm(Model model) {
        model.addAttribute("post", new PostCreateRequest());
        return "post/postForm";
    }

    @PostMapping("/post/postForm")
    public String postForm(@ModelAttribute("postForm") PostCreateRequest request,
                           @RequestParam("file") MultipartFile file,
                           HttpSession session) throws IOException {

        String tempDir = System.getProperty("java.io.tmpdir");
        File tempFile = new File(tempDir, file.getOriginalFilename());
        file.transferTo(tempFile);

        session.setAttribute("filePath", tempFile.getAbsolutePath());
        session.setAttribute("postForm", request);

        List<String> headers = postService.checkHeader(tempFile);
        session.setAttribute("headers", headers);
        return "redirect:/post/postHeaderCheckForm";
    }

    @GetMapping("/post/postHeaderCheckForm")
    public String postHeaderCheckForm(HttpSession session, Model model) {
        List<String> headers = (List<String>) session.getAttribute("headers");
        model.addAttribute("headers", headers);
        return "post/postHeaderCheckForm";
    }

    @PostMapping("/post/postHeaderCheckForm")
    public String savePost(@ModelAttribute PostCheckRequest postCheckRequest,
                           HttpSession session) throws IOException {

        String filePath = (String) session.getAttribute("filePath");
        File file = new File(filePath);

        PostCreateRequest postCreateRequest = (PostCreateRequest) session.getAttribute("postForm");
        postService.createPost(PostDto.from(postCreateRequest, postCheckRequest), file);

        Long lRoomId = postCreateRequest.getLRoomId();

        file.delete();
        return "redirect:/lecture-room/detail/" + lRoomId;
    }


    @GetMapping("/post")
    public String findPostList(Model model,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "5") int size) {
        PostListResponse postListResponse = postService.getPostList(page, size);
        model.addAttribute("posts", postListResponse.getPosts());
        model.addAttribute("totalPages", postListResponse.getTotalPages());
        model.addAttribute("currentPage", postListResponse.getCurrentPage());
        return "post/postList"; // Thymeleaf 파일 경로 수정
    }
    @GetMapping("/post/{lRoomId}")
    public String findPostListByLectureRoomId(@PathVariable Long lRoomId, Model model) {
        List<PostDto> postList = postService.getPostListByLectureRoomId(lRoomId);
        model.addAttribute("posts", postList);
        return "post/postList"; // Thymeleaf 파일 경로 수정
    }


    @GetMapping("/post/updateForm/{postId}")
    public String updateForm(@ModelAttribute("postId") Long postId, Model model) {
        PostDto postDto = postService.findByPostId(postId);
        model.addAttribute("post", postDto);
        return "post/postUpdateForm";
    }

    @PostMapping("/post/updateForm/{postId}")
    public String update(@ModelAttribute("postId") Long postId, @ModelAttribute("post") PostCreateRequest request) {
        postService.update(postId, PostDto.from(request));
        return "redirect:/post";
    }

    @GetMapping("/post/delete/{postId}")
    public String delete(@ModelAttribute("postId") Long postId) {
        postService.delete(postId);
        return "redirect:/post";
    }
}
