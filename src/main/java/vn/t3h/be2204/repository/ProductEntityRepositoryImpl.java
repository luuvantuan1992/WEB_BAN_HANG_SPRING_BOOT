package vn.t3h.be2204.repository;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import vn.t3h.be2204.entities.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.transform.Transformer;
import java.util.List;

public class ProductEntityRepositoryImpl implements ProductEntityCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductEntity> findTop10() {
        String query = "select p.ID id , p.NAME name , c.NUMBER number  from product_cart c " +
                "join  product p on p.ID  = c.PRODUCT_ID " +
                "GROUP BY  c.PRODUCT_ID DESC count(c.PRODUCT_ID) limit 10 ";
        entityManager.unwrap(Session.class).createNativeQuery(query)
                .addScalar("id", LongType.INSTANCE)
                .addScalar("name", StringType.INSTANCE)
                .addScalar("number",LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ProductEntity.class)).list();
        return null;
    }
}
