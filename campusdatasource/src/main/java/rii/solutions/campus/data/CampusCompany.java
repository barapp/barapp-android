package rii.solutions.campus.data;

/**
 * Represents a company
 *
 * Created by rimmer on 04.06.2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
@lombok.Data
public final class CampusCompany extends BaseModel {
    private long id;
    String name;
    private Image logo;
}
