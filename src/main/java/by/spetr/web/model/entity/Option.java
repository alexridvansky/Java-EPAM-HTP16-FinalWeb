package by.spetr.web.model.entity;

import java.io.Serializable;

public class Option extends AbstractEntity implements Serializable {
    private int optionId;
    private String description;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;

        Option option = (Option) o;

        if (optionId != option.optionId) return false;
        return description != null ? description.equals(option.description) : option.description == null;
    }

    @Override
    public int hashCode() {
        int result = optionId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Option{");
        sb.append("optionId=").append(optionId);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
