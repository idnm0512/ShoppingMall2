package com.thisfit.shoppingmall.user.join.repository.datasource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
public class UserId implements Serializable {

    @Column
    private int no;

    private String id;

}
