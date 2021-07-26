package by.spetr.web.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message extends AbstractEntity implements Serializable {
    private long messageId;
    private String topic;
    private String message;
    private long senderId;
    private long receiverId;
    private LocalDateTime dateSent;
    private LocalDateTime dateRead;

}
