package com.example.classhub.domain.datadetail.controller;

import com.example.classhub.domain.datadetail.controller.request.DataDetailCreateRequest;
import com.example.classhub.domain.datadetail.controller.response.DataDetailListResponse;
import com.example.classhub.domain.datadetail.dto.DataDetailDto;
import com.example.classhub.domain.datadetail.service.DataDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class DataDetailController {
    private final DataDetailService dataDetailService;

    @GetMapping("/data-detail/dataDetailForm")
    public String createDataDetailForm(Model model){
        model.addAttribute("dataDetail", new DataDetailCreateRequest());
        return "dataDetailForm";
    }
    @PostMapping("/data-detail/dataDetailForm")
    public String dataDetailForm(@ModelAttribute("dataDetailForm") DataDetailCreateRequest request){
        dataDetailService.saveDataDetail(DataDetailDto.from(request));
        return "redirect:/data-detail/dataDetailList";
    }

    @GetMapping("/data-detail/dataDetailList")
    public String findDataDetailList(Model model){
        DataDetailListResponse dataDetailListResponse = dataDetailService.getDataDetailList();
        model.addAttribute("dataDetails", dataDetailListResponse.getDataDetails());
        return "dataDetailList";
    }

    @GetMapping("/data-detail/updateForm/{dataDetailId}")
    public String updateForm(@ModelAttribute("dataDetailId") Long dataDetailId, Model model){
        DataDetailDto dataDetailDto = dataDetailService.findByDataDetailId(dataDetailId);
        model.addAttribute("dataDetail", dataDetailDto);
        return "dataDetailUpdateForm";
    }

    @PostMapping("/data-detail/updateForm/{dataDetailId}")
    public String update(@ModelAttribute("dataDetailId") Long dataDetailId, @ModelAttribute("dataDetail") DataDetailCreateRequest request){
        dataDetailService.update(dataDetailId, DataDetailDto.from(request));
        return "redirect:/data-detail/dataDetailList";
    }

    @GetMapping("/data-detail/delete/{dataDetailId}")
    public String delete(@ModelAttribute("dataDetailId") Long dataDetailId){
        dataDetailService.delete(dataDetailId);
        return "redirect:/data-detail/dataDetailList";
    }
}