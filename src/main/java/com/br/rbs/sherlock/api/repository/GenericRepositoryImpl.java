package com.br.rbs.sherlock.api.repository;

import com.br.rbs.sherlock.api.domain.enums.PERSIST_OPTION;
import com.br.rbs.sherlock.api.exception.PersistenceException;
import com.br.rbs.sherlock.api.util.TextUtils;
import com.br.rbs.sherlock.api.util.ValidationUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.Collection;

/**
 * .
 * User: helmedeiros
 * Date: 8/28/13
 * Time: 10:11 PM
 */
public class GenericRepositoryImpl {
    protected Transaction tx = null;
    protected Session session;

    private boolean pageable;       //should repository be pageable.
    private int pageSize = 40;      // default page size is 40.

    protected ArrayList<String> orderBy;
    protected ArrayList<String> ascDesc;

    private int pageNum = 1; // the current page number.

    public GenericRepositoryImpl() {
        this.session = HibernateUtil.openSession();
    }

    /**
     * Search the object by the given primary key.
     */
    @SuppressWarnings("unchecked")
    public <T extends PersistDB> T findByPrimaryKey(int id, Class<T> classe) throws PersistenceException {
        try {
            T obj = (T) session.get(classe, new Integer(id));
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    /**
     * Method for performing persistence. Commit every invocation.
     * @param obj - The {@link PersistDB} object to be persisted.
     * @param option - The {@link PERSIST_OPTION} to be accomplished.
     * @throws PersistenceException
     */
    protected void changeOperation(final PersistDB obj, final PERSIST_OPTION option) throws PersistenceException {

        try {

            Transaction tx = getSession().beginTransaction();

            switch (option) {
                case INSERT:
                    getSession().save(obj);
                    break;
                case UPDATE:
                    getSession().update(obj);
                    break;
                case REMOVE:
                    getSession().delete(obj);
                    break;
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw new PersistenceException(e);
        }
    }

    /**
     * Search and return a {@link Collection} of the requested object.
     * @param classe - The class from each is requested the list.
     * @return - The list for the given search.
     * @throws PersistenceException
     */
    @SuppressWarnings("unchecked")
    public <T> Collection<T> findAll(Class<T> classe) throws PersistenceException {
        Criteria c = getCriteria(classe);
        c.setCacheable(true);
        return c.list();
    }

    /**
     * Create and return the criteria for that given class.
     * @param classe - The class to be used in the current criteria.
     * @return - The criteria for the configured and informed informations.
     * @throws PersistenceException
     */
    protected Criteria getCriteria(Class<?> classe) throws PersistenceException {

        Criteria criteria = getSession().createCriteria(classe);
        if (pageable) {
            criteria.setFirstResult(pageSize * (pageNum - 1));
            criteria.setMaxResults(pageSize);
        }
        
        if (orderBy != null) {
            for (int a = 0; a < orderBy.size(); a++) {
                String order = ascDesc.get(a);
                if (order.equals("desc")) {
                    criteria.addOrder(Order.desc(orderBy.get(a)));
                } else {
                    criteria.addOrder(Order.asc(orderBy.get(a)));
                }
            }
        }

        return criteria;

    }

    public Session getSession() { return session; }

    /**
     * Create and executes a new {@link org.hibernate.Query} based on the informed fields.
     *
     * @param classe - The {@link Object} class that should be answered.
     * @param field - The {@String name} to be used as restriction in the search.
     * @param value - The {@link Object} value to be used to restrict.
     * @return A {@link java.util.Collection} for the given classe.
     */
    public <T> T findByExactField(Class<T> classe, String field, Object value) {
        Collection<T> found = findWithQuery(classe, field, value, null);
        if(!ValidationUtil.isEmpty(found)) return found.iterator().next();
        return null;
    }

    /**
     * Create and executes a new {@link org.hibernate.Query} based on the informed fields.
     * @param classe - The {@link Object} class that should be answered.
     * @param field - The {@String name} to be used as restriction in the search.
     * @param value - The {@link Object} value to be used to restrict.
     * @param orderType - The {@link String} orderType to be used (ASC | DESC)
     * @param orderFields - The {@link String} order fields to be used.
     * @return A {@link java.util.Collection} for the given classe.
     */
    private <T> Collection<T> findWithQuery(Class<T> classe, String field, Object value, String orderType, String... orderFields) {
        String query = "from " + classe.getSimpleName() + " obj ";
        String orderQuery = "";

        if(value != null){
            if (value instanceof String || value instanceof Character) {
                value = "'" + TextUtils.doublequotes(value.toString()) + "'";
            }
            query += " where obj." + field + "=" + value;
        }


        if (orderType != null & orderFields.length > 0) {
            orderQuery += " order by ";
            for (String f : orderFields) {
                orderQuery += f + ",";
            }
            orderQuery = orderQuery.substring(0, orderQuery
                    .lastIndexOf(','));
            orderQuery += " " + orderType;

        }

        Query q = getSession().createQuery(query + orderQuery);

        return q.list();
    }
}
