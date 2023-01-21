package com.rpx.bsmv2.domain.treatment;

import com.rpx.bsmv2.domain.IResourceConvert;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder(setterPrefix = "with")
@Getter
@Setter
public class TreatmentResource implements IResourceConvert<Treatment, TreatmentResource> , Serializable {

    private Long id;

    @NotBlank
    @Length(min = 2, max = 60)
    private String description;

    @NotNull
    @Digits(integer = 3, fraction = 2)
    @PositiveOrZero
    private BigDecimal price;

    @NotNull
    private Boolean isActive;

    @Override
    public Treatment convertFrom() {
        Treatment treatment = new Treatment();
        treatment.setId(this.id);
        treatment.setDescription(this.description);
        treatment.setPrice(this.price);
        treatment.setIsActive(this.isActive);
        return treatment;
    }

    @Override
    public TreatmentResource convertTo(final Treatment obj) {
        return TreatmentResource.builder()
                .withId(obj.getId())
                .withDescription(obj.getDescription())
                .withPrice(obj.getPrice())
                .withIsActive(obj.getIsActive())
                .build();
    }

    @Override
    public Treatment convertToUpdate(Treatment treatment) {
        treatment.setId(this.id);
        treatment.setDescription(this.description);
        treatment.setPrice(this.price);
        treatment.setIsActive(this.isActive);
        return treatment;
    }
}
