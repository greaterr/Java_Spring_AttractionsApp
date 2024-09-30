/*package ru.aston.attractionapp.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.AttractionType;

import java.util.List;

@Repository
@Primary
@AllArgsConstructor
public class AttractionRepositoryImplRest  {

    @Autowired
    private final SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Attraction findById(Long id) {
        return getCurrentSession().get(Attraction.class, id);
    }

    @Override
    public Attraction save(Attraction attraction) {
        getCurrentSession().saveOrUpdate(attraction);
        return attraction;
    }

    @Override
    public void deleteById(Long id) {
        Attraction attraction = findById(id);
        if (attraction != null) {
            getCurrentSession().delete(attraction);
        }
    }

    @Override
    public List<Attraction> findAllByOrderByNameAsc() {
        String sql = "FROM Attractions a ORDER BY a.name ASC";
        return getCurrentSession().createQuery(sql, Attraction.class).getResultList();
    }

    @Override
    public List<Attraction> findAllByType(AttractionType type) {
        String sql = "FROM Attractions a WHERE a.type = :type";
        return getCurrentSession().createQuery(sql, Attraction.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Attraction> findAllByCityId(Long cityId) {
        String sql = "FROM Attractions a WHERE a.city.id = :cityId";
        return getCurrentSession().createQuery(sql, Attraction.class)
                .setParameter("cityId", cityId)
                .getResultList();
    }
}
*/