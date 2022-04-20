package com.gukbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GukbitApplication {

  public static void main(String[] args) {
    SpringApplication.run(GukbitApplication.class, args);
  }


//  @Bean
//  public CommandLineRunner runner(AcademyRepository academyRepository) { //3. 명령 실행
//    Map<String, AcademyData> map = AcademyList.academylist();
//    return (args) -> {
//      map.forEach((key, value) ->
//              academyRepository.save(Academy.builder()
//                      .code(value.getAcademyId())
//                      .name(value.getAcademyName())
//                      .home_url(value.getHpAddr())
//                      .region(value.getRegion())
//                      .addr(value.getAddr())
//                      .tel(value.getTel())
//                      .build())
//      );
//    };
//  }
//
//  @Bean
//  public CommandLineRunner runner2(CourseRepository courseRepository) { //3. 명령 실행
//    Map<Integer, CourseData> map = CourseList.courselist();
//    return (args) -> {
//      map.forEach((key, value) ->
//              courseRepository.save(Course.builder()
//                      .academy_code(value.getAcademyId())
//                      .id(value.getCourseId())
//                      .session(Integer.parseInt(value.getSession()))
//                      .field_m(value.getField_m())
//                      .field_s(value.getField_s())
//                      .d_field_ss(value.getD_field_ss())
//                      .name(value.getTitle())
//                      .start(value.getStart())
//                      .end(value.getEnd())
//                      .build())
//      );
//    };
//  }
//
//  @Bean
//  public CommandLineRunner runner3(BoardRepository boardRepository) { //3. 명령 실행
//    return (args) -> {
//      IntStream.rangeClosed(1, 100).forEach(index ->
//          boardRepository.save(Board.builder()
//              .bid(index)
//              .author("이순신")
//              .date(LocalDateTime.now().minusDays(100-index).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString())
//              .view((int)index)
//              .title("국비학원 출신 10년차 개발자의 개인적인 의견입니다.")
//              .content("")
//              .b_academy_code("[멀티캠퍼스]")
//              .b_course_id("[웹개발 풀스택 과정]")
//              .visible(true)
//              .recommend(0)
//              .build())
//      );
//    };
//  }
}



