package by.spetr.web.model.service;

import by.spetr.web.model.exception.ServiceException;

public interface MediaService {
    String storePhoto(String filename) throws ServiceException;

    String getPreviewPhoto(String publicId);

    String getAlbumPhoto(String publicId);

    static CloudinaryMediaService getInstance() {
        return CloudinaryMediaService.getCloudinary();
    }
}
