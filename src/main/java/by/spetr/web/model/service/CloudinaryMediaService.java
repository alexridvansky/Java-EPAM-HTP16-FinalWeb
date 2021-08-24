package by.spetr.web.model.service;

import by.spetr.web.model.exception.ServiceException;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Class includes individual service settings and performs interaction with Cloudinary service
 */
public class CloudinaryMediaService implements MediaService {
    private static final Logger logger = LogManager.getLogger();
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final Cloudinary cloudinary = new Cloudinary();
    private static CloudinaryMediaService instance;

    private CloudinaryMediaService() {
    }

    static CloudinaryMediaService getCloudinary() {
        if (instance == null) {
            if (isInitialized.compareAndSet(false, true)) {
                instance = new CloudinaryMediaService();
            }
        }
        return instance;
    }

    public String storePhoto(String filename) throws ServiceException {

            return ("public_id"); //test

    }

    public String getPreviewPhoto(String publicId) {
        return cloudinary.url().transformation(new Transformation().width(420).height(225).crop("fill").gravity("center")).generate(publicId);
    }

    public String getAlbumPhoto(String publicId) {
        return cloudinary.url().transformation(new Transformation().width(648).height(347).crop("fill").gravity("center")).generate(publicId);
    }
}