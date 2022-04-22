package com.gukbit.controller;

import com.gukbit.domain.*;
import com.gukbit.dto.AcademyDto;
import com.gukbit.service.AcademyService;
import com.gukbit.service.CourseService;
import com.gukbit.service.RateService;
import com.gukbit.session.SessionConst;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/academy")
public class AcademyController {

    private final AcademyService academyService;
    private final RateService rateService;
    private final CourseService courseService;

    public AcademyController(AcademyService academyService, RateService rateService, CourseService courseService) {
        this.academyService = academyService;
        this.rateService = rateService;
        this.courseService =  courseService;
    }


    //리뷰 탭
    @GetMapping("/review")
    String academyMapping(@RequestParam("code") String code,
                          @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
                          @Qualifier("review") Pageable pageable1, @Qualifier("expected") Pageable pageable2, Model model) {

//   @GetMapping({"", "/", })
//   String academyMapping(@RequestParam ("code") String code,
//       @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
//       Model model) {

        /* 평가 리뷰출력 페이지 데이터 */
        List<String> items = new ArrayList<>();
        items.add("강사진");
        items.add("커리큘럼");
        items.add("취업 연계");

        items.add("학원 내 문화");
        items.add("운영 및 시설");
        model.addAttribute("items", items);


        /* 학원 정보 출력 */
        Academy academy_info = academyService.getAcademyInfo(code);
        model.addAttribute("academy_info", academy_info);

        /* 해당 학원의 과정 정보 출력 */

        List<Course> course_list =  courseService.getCourseList(code);
        Page<Rate> page2 = academyService.reviewCoursePageList(course_list,pageable1);




        System.out.println("page2 = " + page2);
        Page<Course> page = academyService.expectedCoursePageList(code, pageable2);
        model.addAttribute("expectedCoursePageList", page);
        model.addAttribute("reviewCoursePageList", page2);
        model.addAttribute("link1", "academy/review?code="+code);
        model.addAttribute("link2", "academy/expected?code="+code);
        model.addAttribute("expectedSelect",false);




        /* 로그인 유저 관련 정보 전달 */
        try {
            String userId = loginUser.getUserId();
            AuthUserData authUserData = rateService.getAuthUserData(userId);
            model.addAttribute("authUserData", authUserData);

        } catch (NullPointerException e) {
            AuthUserData authUserData = null;
            model.addAttribute("authUserData", authUserData);
        }
        return "/view/academy";
    }

    //모집중인 과정 탭
    @GetMapping("/expected")
    String expectedMapping(@RequestParam("code") String code, @Qualifier("review") Pageable pageable1, @Qualifier("expected") Pageable pageable2, Model model) {
        List<String> items = new ArrayList<>();
        items.add("강사진");
        items.add("커리큘럼");
        items.add("취업 연계");

        items.add("학원 내 문화");
        items.add("운영 및 시설");
        model.addAttribute("items", items);




        Page<Course> page = academyService.expectedCoursePageList(code, pageable2);
        System.out.println("page = " + page);
        model.addAttribute("expectedCoursePageList", page);
        model.addAttribute("link1", "academy/review?code=" + code);
        model.addAttribute("link2", "academy/expected?code=" + code);
        model.addAttribute("expectedSelect", true);




        Academy academy_info = academyService.getAcademyInfo(code);
        model.addAttribute("academy_info", academy_info);

        return "/view/academy";
    }

    @GetMapping("/search")
    public String searchAcademy(@RequestParam(value = "keyword") String keyword, Model model) {
        List<AcademyDto> academyDtoList = academyService.searchAcademy(keyword);
        model.addAttribute("academyList", academyDtoList);
        model.addAttribute("keyword", keyword);
        return "/view/searchAcademy";
    }
}
