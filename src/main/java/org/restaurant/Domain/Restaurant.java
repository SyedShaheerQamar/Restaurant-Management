package org.restaurant.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Restaurant {

    private Integer id;
    private String r_name;
    private String location;
    private Integer u_id;

}
