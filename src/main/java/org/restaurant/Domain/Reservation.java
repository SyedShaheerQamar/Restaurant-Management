package org.restaurant.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Reservation {

    private Integer id;
    private String resDate;
    private String resTime;
    private Integer c_id;
    private Integer t_id;
    private Integer r_id;
    private Integer token;

}
