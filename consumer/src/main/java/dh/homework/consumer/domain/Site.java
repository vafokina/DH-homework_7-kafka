package dh.homework.consumer.domain;

import lombok.Data;

@Data
public class Site {

    private String externalId;
    private String ident;
    private Status status;

    enum Status {
        ACTIVE, STOPPED
    }
}
