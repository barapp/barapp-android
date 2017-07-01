package rii.solutions.campus.data;

/**
 * Class that holds any image, like photo or logo
 *
 * Created by rimmer on 04.06.2017.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@lombok.Data
public final class Image extends BaseModel {
    private String thumbnailUri;
    private String fullUri;
    private String vectorSvgUri;
}
