package com.l2lhackathon.peers.controller.offer.dto.constraints;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class IntegerGreaterOrLessConstraintDto extends ConstraintDto {
    private List<PairDto<GreaterOrLessKeyDto, Integer>> values;
}
