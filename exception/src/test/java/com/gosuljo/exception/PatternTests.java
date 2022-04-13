package com.gosuljo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatternTests {

    @Test
    void build_test() {
        ConstructorObject co = ConstructorObject
                .SubConstructorObject
                .builder()
                .orderId(10)
//                .address("광주")
                .build().done();

        System.out.println(co);
    }

}

@AllArgsConstructor
@ToString
class ConstructorObject {
    private long orderId;
    private String address;
    private String productName;

    @Builder
    static class SubConstructorObject {
        private long orderId;
        @NonNull private String address;

        public ConstructorObject done() {
            return new ConstructorObject(this.orderId, this.address, "productName");
        }
    }
}