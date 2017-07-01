package rii.solutions.campus.data;

import java.util.List;

/**
 * A wrapper for results list
 * The list is paginated and has some
 * additional params like count, next, previous
 *
 * Created by rimmer on 05.06.2017.
 */

public final class ResponseWrapperModel<T extends List<? extends BaseModel>> {
    public T results;
    public int count;
}
