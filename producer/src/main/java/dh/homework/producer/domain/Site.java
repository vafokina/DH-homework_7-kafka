package dh.homework.producer.domain;

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
