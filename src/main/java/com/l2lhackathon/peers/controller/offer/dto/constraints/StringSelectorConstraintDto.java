package com.l2lhackathon.peers.controller.offer.dto.constraints;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StringSelectorConstraintDto extends ConstraintDto {
    private List<String> values;
}
