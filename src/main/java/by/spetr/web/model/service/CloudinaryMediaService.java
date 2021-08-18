package by.spetr.web.model.service;

import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.pool.ConnectionPool;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 *  Class includes individual service settings and performs interaction with Cloudinary service
 */
public class CloudinaryMediaService implements MediaService{
        private static final Logger logger = LogManager.getLogger();
        private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
        private static final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "autoschrott",
                "api_key", "884862746761739",
                "api_secret", "_L51jlX9czVguOaBVTnpS_LLrvw",
                "secure", true));
        private static CloudinaryMediaService instance;

        private CloudinaryMediaService() {}

        static CloudinaryMediaService getCloudinary() {
                if (instance == null) {
                        if (isInitialized.compareAndSet(false,true)) {
                                instance = new CloudinaryMediaService();
                        }
                }
                return instance;
        }

        public String store(String filename) throws ServiceException {
                try {
                        Map<String, String> upload = cloudinary.uploader().upload(
                                filename,
                                ObjectUtils.emptyMap()
                        );

                        return upload.get("public_id");

                } catch (IOException e) {
                        logger.error("File couldn't be stored through Cloudinary service", e);
                        throw new ServiceException("File couldn't be stored through Cloudinary service", e);
                }
        }

        public String getPreview(String publicId) {
                return cloudinary.url().transformation(new Transformation().width(420).height(225).crop("fill").gravity("center")).generate(publicId);
        }

        public String getAlbumPhoto(String publicId) {
                return cloudinary.url().transformation(new Transformation().width(648).height(347).crop("fill").gravity("center")).generate(publicId);
        }
}