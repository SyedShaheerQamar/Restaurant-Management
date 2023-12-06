package org.restaurant.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Tables {

    private Integer id;
    private Integer capacity;
    private String availability;
    private Integer r_id;

}
