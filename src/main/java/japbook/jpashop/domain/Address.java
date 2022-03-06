package japbook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 어딘가에 내장이 될 수 있다.
@Getter
public class Address {
//setter를 안주고 생성자로 처음에 값 주는게 좋은 설계
    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
