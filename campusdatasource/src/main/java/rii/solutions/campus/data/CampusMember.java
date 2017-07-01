package rii.solutions.campus.data;

/**
 * Holds all member info
 * {@link CampusCompany} may have couple of members
 *
 * Created by rimmer on 04.06.2017.
 */

import lombok.EqualsAndHashCode;
import lombok.Getter;

@SuppressWarnings({"WeakerAccess", "unused"})
@EqualsAndHashCode
public final class CampusMember extends BaseModel {
    @Getter private long id;
    @Getter private String name;
    @Getter private String companyName;
    @Getter private Image photo;
    @Getter private transient CampusCompany company;

    public CampusMember() {
        this.company = new CampusCompany();
        this.company.name = companyName;
    }

    @Override
    public String toString() {
        return name;
    }
}
