package by.spetr.web.model.service;

import by.spetr.web.model.exception.ServiceException;

public interface MediaService {
    String store(String filename) throws ServiceException;

    String getPreview(String publicId);

    String getAlbumPhoto(String publicId);

    static CloudinaryMediaService getInstance() {
        return CloudinaryMediaService.getCloudinary();
    }
}
