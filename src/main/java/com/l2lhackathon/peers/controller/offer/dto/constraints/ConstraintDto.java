package com.l2lhackathon.peers.controller.offer.dto.constraints;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.l2lhackathon.peers.domain.offer.constraints.ConstraintType;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringSelectorConstraintDto.class, name = ConstraintType.Names.SELECTOR_STRING),
        @JsonSubTypes.Type(value = IntegerGreaterOrLessConstraintDto.class, name =
                ConstraintType.Names.GREATER_OR_LESS_INTEGER),
})
public class ConstraintDto {
    private ConstraintTypeDto type;
}
