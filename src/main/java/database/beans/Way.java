package database.beans;

import beans.City;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @RequiredArgsConstructor
@ToString
public class Way {

    private @NonNull City originCity;

    private @NonNull City distanceCity;

    private Float distance;

}
