package by.spetr.web.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

public class Vehicle extends AbstractEntity implements Cloneable, Serializable {
    private long id;
    private byte adState;
    private long ownerId;
    private long modelId;
    private Year modelYear;
    private BigDecimal price;
    private byte powertrainId;
    private byte transmissionId;
    private byte driveId;
    private int displacement;
    private int power;
    private LocalDate dateCreated;

}
