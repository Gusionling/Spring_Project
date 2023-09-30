package com.hk.review.service.dto;

import com.hk.review.model.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

//응답하는 객체와 같이 생기게 되었다.
@Getter
@Builder
@AllArgsConstructor
public class ReviewDto {
    private Double avgScore;
    private List<ReviewEntity> reviews;
    private ReviewDtoPage page;


    @Getter
    @AllArgsConstructor
    @Builder
    public static class ReviewDtoPage{
        private Integer offset;

        private Integer limit;
    }
}
