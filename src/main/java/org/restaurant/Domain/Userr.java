package org.restaurant.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Userr {

    private Integer id;
    private String u_name;
    private String email;
    private String pass;
    private String u_type;

}
