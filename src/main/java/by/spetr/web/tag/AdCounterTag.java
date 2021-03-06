package by.spetr.web.tag;

import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.service.VehicleService;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

public class AdCounterTag extends TagSupport {
    private static final VehicleService vehicleService = VehicleService.getInstance();
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            int counter = vehicleService.getVehicleCountByUserId(userId);
            out.write(String.valueOf(counter));
        } catch (IOException | ServiceException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
