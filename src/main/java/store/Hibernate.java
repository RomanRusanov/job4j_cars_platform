package store;

import model.Category;
import model.Item;
import model.User;
import model.carType.BodyType;
import model.carType.Brand;
import model.carType.EngineType;
import model.carType.Transmission;
import model.carType.WheelDriveType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.function.Function;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 21.01.2021
 * email roman9628@gmail.com
 * The class describe DAO implementation.
 */
public class Hibernate implements AutoCloseable {
    /**
     * The instance with logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Hibernate.class.getName());
    /**
     * Registry for hibernate configuration.
     */
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    /**
     * Session factory for hibernate interaction.
     */
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    /**
     * The inner class guarantees that only one instance is initialized.
     */
    private static final class Lazy {
        private static final Hibernate INST = new Hibernate();
    }

    /**
     * Default private constructor.
     */
    private Hibernate() {
    }
    /**
     * The method create and get instance PsqlStore.
     * @return PsqlStore.
     */
    public static Hibernate instOf() {
        return Lazy.INST;
    }

    /**
     * The method implements in necessary AutoCloseable interface.
     * @throws Exception Exception
     */
    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    /**
     * The method execute query to DB.
     * @param command lambda with custom query.
     * @param <T> Expected type.
     * @return Instance created by hibernate.
     */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * The methods add model to DB.
     * @param model Instance model.
     * @param <T> Class model.
     * @return instance of model.
     */
    public <T> T persistModel(T model) {
        return this.tx(session -> {
            session.save(model);
            return model;
        });
    }

    /**
     * The method find in DB user with passed login.
     * @param login String to find.
     * @return If user exist return instance user,
     * otherwise return instance userName = -1 roleName = -1.
     */
    public User findUserByLogin(String login) {
        return this.tx(
                session -> {
                    User result = User.of("-1", "-1");
                    final Query query = session.createQuery(
                            "from User as user where user.name=:login");
                    query.setParameter("login", login);
                    User user = (User) query.uniqueResult();
                    if (user != null) {
                        result = user;
                    }
                    return result;
                }
        );
    }

    /**
     * The method get instance from db with specified id.
     * @param tClass Class instance
     * @param id id to find.
     * @param <T> type instance
     * @return Instance with data store in db for this id.
     */
    public <T> T getInstanceById(Class<T> tClass, Long id) {
        return this.tx(
                session -> session.get(tClass, id)
        );
    }

    /**
     * The Method return all items from db.
     * If Item#images instance use LAZY fetch type and user don't add any images
     * this query dont work with "join fetch i.images".
     * @return items list.
     */
    public List<Item> getAllItems() {
        return this.tx(
                session -> session.createQuery(
                        "select distinct i from Item i " +
                                "join fetch i.category " +
                                "join fetch i.user " +
                                "join fetch i.car c " +
                                "join fetch c.bodyType " +
                                "join fetch c.brand " +
                                "join fetch c.engineType " +
                                "join fetch c.transmission " +
                                "join fetch c.wheelDriveType"
                        , Item.class).list()
        );
    }

    /**
     * The method takes the id and find in DB item with this id
     * @param id Long id item to search
     * @return math item with id
     */
    public Item findItemById(Long id) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery(
                            "select i from Item i where i.id=:item_id");
                    query.setParameter("item_id", id);
                    return (Item) query.uniqueResult();
                }
        );
    }

    /**
     * The method change status field sold in item instance and update in DB.
     * @param id item id.
     */
    public void changeStatus(Long id) {
        Item item = this.findItemById(id);
        item.setSold(!item.getSold());
        this.tx(session -> {
            session.update(item);
            return item;
        });
    }

    /**
     * The method get all Brands from db.
     * @return List.
     */
    public List<Brand> getAllBrands() {
        return this.tx(
                session -> session.createQuery(
                        "select b from Brand b"
                        , Brand.class).list()
        );
    }

    /**
     * The method get all Transmission from db.
     * @return List.
     */
    public List<Transmission> getAllTransmissionTypes() {
        return this.tx(
                session -> session.createQuery(
                        "select t from Transmission t"
                        , Transmission.class).list()
        );
    }

    /**
     * The method get all BodyType from db.
     * @return List.
     */
    public List<BodyType> getAllBodyTypes() {
        return this.tx(
                session -> session.createQuery(
                        "select bt from BodyType bt"
                        , BodyType.class).list()
        );
    }

    /**
     * The method get all EngineType from db.
     * @return List.
     */
    public List<EngineType> getAllEngineTypes() {
        return this.tx(
                session -> session.createQuery(
                        "select et from EngineType et"
                        , EngineType.class).list()
        );
    }

    /**
     * The method get all EngineType from db.
     * @return WheelDriveType.
     */
    public List<WheelDriveType> getAllWheelDriveTypes() {
        return this.tx(
                session -> session.createQuery(
                        "select wdt from WheelDriveType wdt"
                        , WheelDriveType.class).list()
        );
    }

    /**
     * The method get all EngineType from db.
     * @return Category.
     */
    public List<Category> getAllCategories() {
        return this.tx(
                session -> session.createQuery(
                        "select c from Category c"
                        , Category.class).list()
        );
    }
}