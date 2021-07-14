package by.spetr.web.command;

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
     * // todo: description
     *
     * @param pagePath path to page
     * @param routerType forward or redirect
     */
    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
        logger.debug("pagePath: {}, routerType: {}", pagePath, routerType);
    }

    /**
     * in this constructor Forward is predefined type
     *
     * @param pagePath path to page
     */
    public Router(String pagePath) {
        this.pagePath = pagePath;
        this.routerType = RouterType.FORWARD;
        logger.debug("pagePath: {}, routerType: {}", pagePath, routerType);
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }
}
