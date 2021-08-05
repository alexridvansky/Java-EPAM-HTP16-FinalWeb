package by.spetr.web.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Router {

    public enum RouterType {
        FORWARD,
        REDIRECT
    }

    private static final Logger logger = LogManager.getLogger();
    private final String pagePath;
    private final RouterType routerType;

    /**
     *
     * @param pagePath path to page
     */
    public Router(String pagePath) {
        logger.debug("pagePath: {}, routerType: {}", pagePath, RouterType.FORWARD);
        this.pagePath = pagePath;
        this.routerType = RouterType.FORWARD;
    }

    /**
     *
     * @param pagePath path to page
     * @param routerType forward or redirect
     */
    public Router(String pagePath, RouterType routerType) {
        logger.debug("pagePath: {}, routerType: {}", pagePath, routerType);
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }
}
