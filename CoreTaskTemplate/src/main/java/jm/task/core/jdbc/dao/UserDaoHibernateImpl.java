package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.Table;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class UserDaoHibernateImpl implements UserDao {



    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction createTable = null;
        try (Session session = HibernateUtil.getConfiguration().openSession()) {
            createTable = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE users" +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255) NOT NULL, " +
                    " lastName VARCHAR (255) NOT NULL, " +
                    " age INT NOT NULL, " +" PRIMARY KEY (id));").executeUpdate();
            createTable.commit();
            System.out.println("Всё работает");
        } catch (Exception e) {
            if (createTable != null) {
                createTable.rollback();
                System.out.println("Не работает! :(");
            }
            e.printStackTrace();
        }

    }


    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        Session session = HibernateUtil.getConfiguration().openSession();

        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            transaction.commit();
            System.out.println("Всё ок");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Всё плохо");
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }




    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction saveuser = null;
        try(Session session = HibernateUtil.getConfiguration().openSession()){
            saveuser = session.beginTransaction();
            session.save(new User(name, lastName, age));
            saveuser.commit();
            System.out.println("User добавлен в БД");
        }catch (Exception e){
            if(saveuser != null){
                saveuser.rollback();
                System.out.println("юзер не добавляется");
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction removeuser = null;
        try (Session session = HibernateUtil.getConfiguration().openSession()){
            removeuser = session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM users where id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
            removeuser.commit();
            System.out.println("user удален");
        }catch (Exception e){
            if(removeuser != null){
                removeuser.rollback();
                System.out.println("Юзер не удаляется");
            }
            e.printStackTrace();
        }




    }

    @Override
    public List<User> getAllUsers() {
        Transaction getall = null;
        String sql = "SELECT * FROM users";
        try(Session session = HibernateUtil.getConfiguration().openSession()){
            getall = session.beginTransaction();
            Query query = session.createNativeQuery(sql).addEntity(User.class);
            List<User> userList = query.getResultList();
            getall.commit();
            System.out.println("Получены все юзеры");
            return userList;
        }catch (Exception e) {
            if (getall != null) {
                getall.rollback();
                System.out.println("Юзеры не получены");
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction cleantable = null;
        try(Session session = HibernateUtil.getConfiguration().openSession()){
            cleantable = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            cleantable.commit();
            System.out.println("Таблица пустая");
        }catch (Exception e){
            if(cleantable != null){
                cleantable.rollback();
                System.out.println("Таблица не очищена");
            }
            e.printStackTrace();
        }
    }
}
