package by.spetr.web.model.service;

import by.spetr.web.model.dao.DefaultVehicleDao;
import by.spetr.web.model.dao.VehicleDao;
import by.spetr.web.model.dto.VehicleFullDto;
import by.spetr.web.model.dto.VehiclePreviewDto;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.entity.VehicleBuilder;
import by.spetr.web.model.entity.type.*;
import by.spetr.web.model.exception.DaoException;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.form.VehicleShortForm;
import by.spetr.web.telegrambot.InformerService;
import by.spetr.web.util.TagRemover;
import by.spetr.web.util.validator.VehicleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.FormattedMessage;

import java.math.BigDecimal;
import java.util.*;

public class DefaultVehicleService implements VehicleService {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserService.getInstance();
    private static final MediaService cloudinary = MediaService.getInstance();
    private static final VehicleDao vehicleDao = new DefaultVehicleDao();
    private static final AccessControlService accessControlService = AccessControlService.getInstance();
    private static final InformerService informerService = InformerService.getInstance();
    private static DefaultVehicleService instance;

    private DefaultVehicleService() {
    }

    public static DefaultVehicleService getInstance() {
        if (instance == null) {
            instance = new DefaultVehicleService();
        }
        return instance;
    }

    @Override
    public List<VehicleFullDto> getFullDtoVehicleList() throws ServiceException {
        try {
            List<Vehicle> vehicleList = vehicleDao.findAll();
            List<VehicleFullDto> vehicleFullDtoList = new ArrayList<>();

            for (Vehicle vehicle : vehicleList) {
                Optional<VehicleFullDto> optionalVehicleFullDto = convertToFullDto(vehicle);
                optionalVehicleFullDto.ifPresent(vehicleFullDtoList::add);
            }
            return vehicleFullDtoList;

        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public List<VehiclePreviewDto> getPublicVehicleList(int pageSize, int pageNumber) throws ServiceException {
        try {
            List<Vehicle> vehicleList = vehicleDao.findAllPublicVehicles(pageSize, pageNumber);
            List<VehiclePreviewDto> vehiclePreviewDtoList = new ArrayList<>();

            for (Vehicle vehicle : vehicleList) {
                VehiclePreviewDto vehiclePreviewDto = convertToPreviewDto(vehicle);
                vehiclePreviewDtoList.add(vehiclePreviewDto);
            }

            return vehiclePreviewDtoList;

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<VehiclePreviewDto> getModeratorVehicleList(int pageSize, int pageNumber) throws ServiceException {
        try {
            List<Vehicle> vehicleList = vehicleDao.findAllModeratorVehicles(pageSize, pageNumber);
            List<VehiclePreviewDto> vehiclePreviewDtoList = new ArrayList<>();

            for (Vehicle vehicle : vehicleList) {
                VehiclePreviewDto vehiclePreviewDto = convertToPreviewDto(vehicle);
                vehiclePreviewDtoList.add(vehiclePreviewDto);
            }

            return vehiclePreviewDtoList;

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int getPublicVehicleListSize() throws ServiceException {
        try {
            return vehicleDao.findPublicVehicleListSize();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int getModeratorVehicleListSize() throws ServiceException {
        try {
            return vehicleDao.findModeratorVehicleListSize();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<VehiclePreviewDto> getPersonalVehicleList(long userId) throws ServiceException {
        try {
            List<Vehicle> vehicleList = vehicleDao.findByUserId(userId);
            List<VehiclePreviewDto> vehiclePreviewDtoList = new ArrayList<>();

            for (Vehicle vehicle : vehicleList) {
                VehiclePreviewDto vehiclePreviewDto = convertToPreviewDto(vehicle);
                vehiclePreviewDtoList.add(vehiclePreviewDto);
            }
            return vehiclePreviewDtoList;
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public int getVehicleCountByUserId(long userId) throws ServiceException {
        try {
            return vehicleDao.findAdNumberByUserId(userId);

        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    /**
     * converts EntityVehicle to PreviewDtoVehicle
     *
     * @param vehicle Vehicle as Entity instance
     * @return VehiclePreviewDto vehicle instance
     */
    private VehiclePreviewDto convertToPreviewDto(Vehicle vehicle) throws ServiceException {
        VehiclePreviewDto vehiclePreviewDto = new VehiclePreviewDto();

        vehiclePreviewDto.setId(vehicle.getId());
        vehiclePreviewDto.setState(vehicle.getState());
        vehiclePreviewDto.setMake(vehicle.getModel().getMake().getValue());
        vehiclePreviewDto.setModel(vehicle.getModel().getValue());
        vehiclePreviewDto.setModelYear(vehicle.getModelYear());
        vehiclePreviewDto.setPrice(vehicle.getPrice());
        vehiclePreviewDto.setPowertrain(vehicle.getPowertrain());
        vehiclePreviewDto.setTransmission(vehicle.getTransmission());
        vehiclePreviewDto.setDrive(vehicle.getDrive());
        vehiclePreviewDto.setDisplacement(vehicle.getDisplacement());

        String previewPublicId = null;
        Optional<String> optionalPreviewPublicId;
        try {
            optionalPreviewPublicId = getPreviewImageById(vehicle.getId());
        } catch (ServiceException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }

        if (optionalPreviewPublicId.isPresent()) {
            previewPublicId = cloudinary.getPreviewPhoto(optionalPreviewPublicId.get());
        }

        vehiclePreviewDto.setPreviewImagePath(previewPublicId);

        return vehiclePreviewDto;
    }

    @Override
    public Optional<VehicleFullDto> getFullDtoVehicleById(long vehicleId) throws ServiceException {
        try {
            Optional<Vehicle> optionalVehicle = vehicleDao.findById(vehicleId);

            if (optionalVehicle.isPresent()) {
                return convertToFullDto(optionalVehicle.get());
            } else {
                return Optional.empty();
            }

        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    /**
     * converts EntityVehicle to FullDtoVehicle
     *
     * @param vehicle Vehicle as Entity instance
     * @return VehicleFullDto vehicle instance
     */
    private Optional<VehicleFullDto> convertToFullDto(Vehicle vehicle) throws ServiceException {
        long userId = vehicle.getOwnerId();
        long vehicleId = vehicle.getId();
        Optional<User> optionalUser = userService.getUserById(userId);

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        User user = optionalUser.get();

        VehicleFullDto vehicleFullDto = new VehicleFullDto();
        vehicleFullDto.setId(vehicle.getId());
        vehicleFullDto.setState(vehicle.getState());
        vehicleFullDto.setOwnerId(user.getUserId());
        vehicleFullDto.setOwner(user.getLogin());
        vehicleFullDto.setOwnerPhone(user.getPhone());
        vehicleFullDto.setMake(vehicle.getModel().getMake().getValue());
        vehicleFullDto.setModel(vehicle.getModel().getValue());
        vehicleFullDto.setModelYear(vehicle.getModelYear());
        vehicleFullDto.setMileage(vehicle.getMileage());
        vehicleFullDto.setColor(vehicle.getColor().getValue());
        vehicleFullDto.setPrice(vehicle.getPrice());
        vehicleFullDto.setPowertrain(vehicle.getPowertrain());
        vehicleFullDto.setTransmission(vehicle.getTransmission());
        vehicleFullDto.setDrive(vehicle.getDrive());
        vehicleFullDto.setDisplacement(vehicle.getDisplacement());
        vehicleFullDto.setPower(vehicle.getPower());
        vehicleFullDto.setComment(vehicle.getComment());
        vehicleFullDto.setDateCreated(vehicle.getDateCreated());

        try {

            List<String> publicIdList = vehicleDao.findAllPhotoById(vehicleId);
            List<String> album = new ArrayList<>();
            for (String publicId : publicIdList) {
                String imgPath = cloudinary.getAlbumPhoto(publicId);
                album.add(imgPath);
            }
            vehicleFullDto.setAlbum(album);

            List<VehicleOption> optionList = vehicleDao.findOptionByVehicleId(vehicleId);
            vehicleFullDto.setOptionList(optionList);

            return Optional.of(vehicleFullDto);

        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }


    @Override
    public Optional<Vehicle> getVehicleById(long vehicleId) throws ServiceException {
        try {
            return vehicleDao.findById(vehicleId);
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public List<VehicleMake> getMakeList() throws ServiceException {
        try {
            return vehicleDao.findMakeList();
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public void addMake(VehicleShortForm form) throws ServiceException {
        if (!VehicleValidator.validateMake(form.getMake())) {
            form.setFeedbackMsg("Parameter 'make' isn't valid");
        } else {
            try {
                if (vehicleDao.isMakeExist(form.getMake())) {
                    form.setFeedbackMsg("Make '<b>" + form.getMake() + "</b>' already exists");
                } else if (vehicleDao.insertMake(form.getMake())) {
                    form.setFeedbackMsg("New make '<b>" + form.getMake() + "</b>' successfully added");
                    form.setSuccess(true);
                }

            } catch (DaoException e) {
                throw new ServiceException("Error occurred on DAO layer", e);
            }
        }
    }

    @Override
    public List<VehicleModel> getModelList() throws ServiceException {
        try {
            return vehicleDao.findModelList();
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public void addModel(VehicleShortForm form) throws ServiceException {
        if (!VehicleValidator.validateModel(form.getModel())) {
            form.setFeedbackMsg("Parameter 'model' isn't valid");
        } else {
            try {
                if (!vehicleDao.isMakeExist(form.getMakeId())) {
                    form.setFeedbackMsg("An attempt of creating new model for not existing make_id " + form.getMakeId());
                } else if (vehicleDao.isModelExist(form.getModel())) {
                    form.setFeedbackMsg("Model '<b>" + form.getModel() + "</b>' already exists");
                } else if (vehicleDao.insertModel(form.getModel(), form.getMakeId())) {
                    form.setFeedbackMsg("New model '<b>" + form.getModel() + "</b>' successfully added");
                    form.setSuccess(true);
                }
            } catch (DaoException e) {
                throw new ServiceException("Error occurred on DAO layer", e);
            }
        }
    }

    @Override
    public Vehicle addVehicle(VehicleFullForm form) throws ServiceException {
        Vehicle vehicle = null;

        try {
            String description = form.getComment();
            description = TagRemover.doText(description);
            if (description.length() > 300) {
                description = description.substring(0, 300);
            }

            if (!vehicleDao.isMakeExist(form.getMakeId())) {
                form.setFeedbackMsg("MakeId '" + form.getMakeId() + "' doesn't exist");
            } else if (!vehicleDao.isModelExist(form.getModelId())) {
                form.setFeedbackMsg("ModelId '" + form.getModelId() + "' doesn't exist");
            } else if (!vehicleDao.isPowertrainExist(form.getPowertrainId())) {
                form.setFeedbackMsg("PowertrainId '" + form.getPowertrainId() + "' doesn't exist");
            } else if (!vehicleDao.isTransmissionExist(form.getTransmissionId())) {
                form.setFeedbackMsg("TransmissionId '" + form.getTransmissionId() + "' doesn't exist");
            } else if (!vehicleDao.isDriveExist(form.getDriveId())) {
                form.setFeedbackMsg("DriveId '" + form.getDriveId() + "' doesn't exist");
            } else if (!vehicleDao.isColorExist(form.getColorId())) {
                form.setFeedbackMsg("ColorId '" + form.getColorId() + "' doesn't exist");
            } else if (!VehicleValidator.validateModelYear(form.getModelYear().toString())) {
                form.setFeedbackMsg("ModelYear '" + form.getModelYear() + "' isn't valid");
                form.setModelYear(null);
            } else if (!VehicleValidator.validateDisplacement(form.getDisplacement())) {
                form.setFeedbackMsg("Displacement '" + form.getDisplacement() + "' isn't valid");
                form.setDisplacement(null);
            } else if (!VehicleValidator.validateMileage(form.getMileage())) {
                form.setFeedbackMsg("Mileage '" + form.getMileage() + "' isn't valid");
                form.setMileage(null);
            } else if (!VehicleValidator.validatePower(form.getPower())) {
                form.setFeedbackMsg("Power '" + form.getPower() + "' isn't valid");
                form.setPower(null);
            } else if (!VehicleValidator.validatePrice(form.getPrice())) {
                form.setFeedbackMsg("Price '" + form.getPrice() + "' isn't valid");
                form.setPrice(null);
            } else {
                VehicleBuilder vehicleBuilder = VehicleBuilder.getInstance();

                VehicleMake vehicleMake = new VehicleMake();
                vehicleMake.setMakeId(form.getMakeId());

                VehicleModel vehicleModel = new VehicleModel();
                vehicleModel.setModelId(form.getModelId());
                vehicleModel.setMake(vehicleMake);

                VehicleColor vehicleColor = new VehicleColor();
                vehicleColor.setColorId(form.getColorId());

                List<VehicleOption> options = new ArrayList<>();
                for (Long o : form.getOptionSet()) {
                    VehicleOption vehicleOption = new VehicleOption();
                    vehicleOption.setOptionId(o);
                    options.add(vehicleOption);
                }

                vehicle = vehicleBuilder
                        .state(VehicleStateType.ENABLED)
                        .ownerId(form.getOwnerId())
                        .model(vehicleModel)
                        .modelYear(form.getModelYear())
                        .mileage(form.getMileage())
                        .color(vehicleColor)
                        .price(BigDecimal.valueOf(form.getPrice()))
                        .powertrain(VehiclePowertrainType.getType(form.getPowertrainId()))
                        .transmission(VehicleTransmissionType.getType(form.getTransmissionId()))
                        .drive(VehicleDriveType.getType(form.getDriveId()))
                        .displacement(form.getDisplacement())
                        .power(form.getPower())
                        .comment(form.getComment())
                        .optionList(options)
                        .build();

                vehicle = vehicleDao.insertVehicle(vehicle);
                if (vehicle == null) {
                    throw new ServiceException("Vehicle can't be inserted or re-read");
                }

                boolean isUploaded = uploadVehiclePhoto(form);
                if (isUploaded) {
                    logger.debug("photo(s) uploaded");
                    form.setSuccess(true);
                } else {
                    logger.error("photo(s) not uploaded");
                }
                informOfVehicle(vehicle);

                return vehicle;
            }

            return null;

        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    /**
     * Method is used to sent notification when new vehicle ad added
     *
     * @param vehicle vehicle to be informed about
     */
    private void informOfVehicle(Vehicle vehicle) throws ServiceException {
        StringBuilder note = new StringBuilder();
        note.append("New ad just added:");
        note.append(" ").append(vehicle.getModel().getMake().getValue());
        note.append(" ").append(vehicle.getModel().getValue());
        note.append(" ").append(vehicle.getModelYear());
        note.append(" $").append(vehicle.getPrice());
        Optional<String> previewPath = getPreviewImageById(vehicle.getId());
        previewPath.ifPresent(s -> note.append("\n").append(s));

        informerService.sendPublicMessage(note.toString());
    }

    @Override
    public boolean updateVehicleState(VehicleFullForm form) throws ServiceException {
        try {
            if (!accessControlService.updateVehicleState(form)) {
                throw new ServiceException("Unauthorised access attempt");
            }

            if (vehicleDao.findById(form.getVehicleId()).isEmpty()) {
                FormattedMessage errorMsg = new FormattedMessage(
                        "vehicle by id '{}' not found", form.getVehicleId());
                throw new ServiceException(errorMsg.toString());
            }

            if (!vehicleDao.updateState(form.getVehicleId(), form.getState())) {
                form.setFeedbackMsg("Error changing vehicle state");
                return false;
            } else {
                switch (form.getState()) {
                    case ENABLED -> form.setFeedbackMsg("Ad enabled");
                    case DISABLED -> form.setFeedbackMsg("Ad disabled");
                    case MODERATION -> form.setFeedbackMsg("Ad blocked");
                    case ARCHIVED -> form.setFeedbackMsg("Ad removed");
                    default -> form.setFeedbackMsg("unknown action");
                }
                form.setSuccess(true);
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public boolean uploadVehiclePhoto(VehicleFullForm form) throws ServiceException {
        accessControlService.editVehicle(form);

        Set<String> cloudinaryPublicIds = new HashSet<>();
        for (String filename : form.getPhotoSet()) {
            if (filename == null || filename.isBlank()) {
                return false;
            } else {
                try {
                    String imgPath = cloudinary.storePhoto(filename);

                    if (imgPath != null && !imgPath.isBlank()) {
                        cloudinaryPublicIds.add(imgPath);
                    }
                } catch (ServiceException e) {
                    throw new ServiceException("Error on Cloudinary service. File(s) can't be uploaded", e);
                }
            }
        }

        try {
            vehicleDao.createPhoto(form.getVehicleId(), cloudinaryPublicIds);
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }

        return true;
    }

    @Override
    public boolean updateVehicleComment(VehicleFullForm form) throws ServiceException {
        accessControlService.editVehicle(form);

        String description = form.getComment();
        description = TagRemover.doText(description);
        if (description.length() > 300) {
            description = description.substring(0, 300);
        }

        try {
            boolean result = vehicleDao.updateComment(form.getVehicleId(), description);

            if (result) {
                form.setFeedbackMsg("Comment updated");
                form.setSuccess(true);

                return true;
            } else {
                form.setFeedbackMsg("Error updating comment");

                return false;
            }

        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public Optional<String> getPreviewImageById(long vehicleId) throws ServiceException {
        try {
            Optional<String> optionalPreview = vehicleDao.findPreviewById(vehicleId);
            String previewPath = null;

            if (optionalPreview.isPresent()) {
                previewPath = cloudinary.getPreviewPhoto(optionalPreview.get());
            }

            return Optional.ofNullable(previewPath);

        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }


    @Override
    public List<String> getAlbumById(long vehicleId) throws ServiceException {
        try {
            return vehicleDao.findAllPhotoById(vehicleId);
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public List<VehicleOption> getOptionList() throws ServiceException {
        try {
            return vehicleDao.findAllOption();
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public List<VehiclePowertrainType> getAllPowertrainTypeList() throws ServiceException {
        Object[] powertrainTypes = VehiclePowertrainType.values();
        List<VehiclePowertrainType> powertrainTypeList = new ArrayList<>();

        for (Object o : powertrainTypes) {
            powertrainTypeList.add((VehiclePowertrainType) o);
        }

        return powertrainTypeList;
    }

    @Override
    public List<VehicleTransmissionType> getAllTransmissionTypeList() throws ServiceException {
        Object[] transmissionTypes = VehicleTransmissionType.values();
        List<VehicleTransmissionType> transmissionTypeList = new ArrayList<>();

        for (Object o : transmissionTypes) {
            transmissionTypeList.add((VehicleTransmissionType) o);
        }

        return transmissionTypeList;
    }

    @Override
    public List<VehicleDriveType> getAllDriveTypeList() throws ServiceException {
        Object[] driveTypes = VehicleDriveType.values();
        List<VehicleDriveType> typeArrayList = new ArrayList<>();

        for (Object o : driveTypes) {
            typeArrayList.add((VehicleDriveType) o);
        }

        return typeArrayList;
    }

    @Override
    public List<VehicleColor> getAllColorList() throws ServiceException {
        try {
            return vehicleDao.findAllColors();
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }

    @Override
    public VehicleColor addColor(VehicleShortForm form) throws ServiceException {
        try {
            if (!VehicleValidator.validateColor(form.getColor())) {
                throw new ServiceException("Color entered doesn't match requirements");

            } else if (vehicleDao.isColorExist(form.getColor())) {
                throw new ServiceException("Color with name '" + form.getColor() + "' already exists");
            }
            VehicleColor vehicleColor = vehicleDao.insertColor(form.getColor());
            if (vehicleColor != null) {
                form.setSuccess(true);
                form.setFeedbackMsg("Color '" + vehicleColor.getValue() + "' has been created successfully");
                return vehicleColor;
            } else {
                throw new ServiceException("New color entry '" + form.getColor() + "' hasn't been created");
            }
        } catch (DaoException e) {
            throw new ServiceException("Error occurred on DAO layer", e);
        }
    }
}
