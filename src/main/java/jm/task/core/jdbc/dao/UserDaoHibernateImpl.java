package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session = Util.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS `mydbtest`.`new_table` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;\n";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DROP TABLE if exists mydbtest.new_table";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        User user = new User(name, lastName, age);
        user.setId(1L);

        try {
            session.save(user);
            System.out.printf("The user %s %s has been successfully added\n", name, lastName);
        } catch (Exception e) {
            System.out.printf("Trying to add user %s %s... FAIL!", name, lastName);
        }

        session.close();
    }


    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = String.format("DELETE FROM users WHERE ID = %d", id);
        Query query = session.createNativeQuery(sql).addEntity(User.class);

        try {
            query.executeUpdate();
            transaction.commit();
            System.out.printf("The user with id %d has been successfully deleted\n", id);
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            System.out.printf("Failed to remove user with id %d\n", id);
        }
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        String sql = "SELECT * FROM mydbtest.new_table";
        Query query = session.createNativeQuery(sql).addEntity(User.class);

        List <User> userList = query.list();
        session.close();

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DELETE FROM mydbtest.new_table";

        Query query = session.createNativeQuery(sql);

        try {
            query.executeUpdate();
            transaction.commit();
            System.out.println("The table is clear");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Failed to clear the table");
            e.printStackTrace();
        }
        session.close();
    }
}
