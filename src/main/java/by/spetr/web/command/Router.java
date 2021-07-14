package by.spetr.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Router {

    public enum RouterType {
        FORWARD,
        REDIRECT
    }

    private static final Logger logger = LogManager.getLogger();
    private static String lastPagePath;
    private static RouterType lastRouterType;
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
        lastPagePath = pagePath;
        this.routerType = routerType;
        lastRouterType = routerType;
        logger.debug("pagePath: {}, routerType: {}", pagePath, routerType);
    }

    /**
     * in this constructor Forward is predefined type
     *
     * @param pagePath path to page
     */
    public Router(String pagePath) {
        this.pagePath = pagePath;
        lastPagePath = pagePath;
        this.routerType = RouterType.FORWARD;
        lastRouterType = routerType;
        logger.debug("pagePath: {}, routerType: {}", pagePath, routerType);
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public static Router getLastRoute(){
        if (lastPagePath != null && lastRouterType != null) {
            return new Router(lastPagePath, lastRouterType);
        } else {
            return new Router(PagePath.INDEX_PAGE, RouterType.FORWARD);
        }
    }
}
