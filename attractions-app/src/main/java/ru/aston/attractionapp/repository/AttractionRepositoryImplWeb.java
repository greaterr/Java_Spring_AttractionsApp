/*package ru.aston.attractionapp.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.entity.City;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class AttractionRepositoryImplWeb {
    private final List<Attraction> attractions = Collections.synchronizedList(new LinkedList<>());

    public AttractionRepositoryImplWeb() {
        IntStream.range(1, 4)
                .forEach(i -> this.attractions.add(new Attraction(
                        (long) i,
                        "Достопримечательность №%d".formatted(i),
                        LocalDate.now(),
                        "Описание досторимечательности №%d".formatted(i),
                        AttractionType.PARK,
                        new City(),
                        new LinkedList<>()
                )));
    }

    @Override
    public Attraction findById(Long id) {
        return null;
    }

    @Override
    public Attraction save(Attraction attraction) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Attraction> findAllByOrderByNameAsc() {
        return Collections.unmodifiableList(this.attractions);
    }

    @Override
    public List<Attraction> findAllByCityId(Long cityId) {
        return null;
    }

    @Override
    public List<Attraction> findAllByType(AttractionType type) {
        return null;
    }
}
*/