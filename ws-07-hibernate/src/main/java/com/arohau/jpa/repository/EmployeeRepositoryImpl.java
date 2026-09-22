package com.arohau.jpa.repository;

import com.arohau.jpa.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

//isolate the persistence logic for each entity using the Repository pattern
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private EntityManager entityManager_;
    private Session session;

    public EmployeeRepositoryImpl(EntityManager entityManager, Session session) {
        this.entityManager_ = entityManager;
        this.session = session;
    }

    @Override
    public Optional<Employee> save(Employee employee) {
        try {
//            entityManager.getTransaction().begin();
            session.beginTransaction();
            if (employee.getId() == null) {
                if (employee.getProfile() != null) {
//                    entityManager.persist(employee.getProfile());
                    session.persist(employee.getProfile());
                }
//                entityManager.persist(employee);
                session.persist(employee);
            } else {
//                employee = entityManager.merge(employee);
                employee = session.merge(employee);
            }
//            entityManager.getTransaction().commit();
            session.getTransaction().commit();

            return Optional.of(employee);
        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
            session.getTransaction().rollback();
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
//        Employee employee = entityManager.find(Employee.class, id);
        Employee employee = session.find(Employee.class, id);
        return employee != null ? Optional.of(employee) : Optional.empty();
    }

    @Override
    public void deleteEmployee(Employee employee) {
//        entityManager.getTransaction().begin();
//        if (entityManager.contains(employee)) {
//            entityManager.remove(employee);
//        } else {
//            entityManager.merge(employee);
//        }
//        entityManager.getTransaction().commit();

        session.beginTransaction();
        if (session.contains(employee)) {
            session.remove(employee);
        } else {
            session.merge(employee);
        }
        session.getTransaction().commit();

    }

    // JPQL query = jakarta (or JAVA) persistence query language
    @Override
    public List<Employee> getEmployeesByExperience(Integer yearsExperience) {
        Query jpqlQuery = session.createQuery("SELECT e FROM Employee as e WHERE e.yearsExperience > :yearsExperience ORDER BY e.lName");
        jpqlQuery.setParameter("yearsExperience", yearsExperience);
        List<Employee> employeesList = jpqlQuery.getResultList();

        return employeesList;
    }

    // Native Query
    @Override
    public List<Employee> getEmployeesByExperienceNativeQuery(Integer yearsExperience) {

        //Note: createNativeQuery is a native SQL query which will return the raw data from the database, not the Entity, need to include class name
        Query nativeQuery = session.createNativeQuery("SELECT * FROM employees WHERE yearsExperience > :yearsExperience ORDER BY lname", Employee.class);
        nativeQuery.setParameter("yearsExperience", yearsExperience);
        List<Employee> employeesList = nativeQuery.getResultList();

        return employeesList;
    }

    // Criteria Query
    @Override
    public List<Employee> getEmployeesByExperienceCriteriaQuery(Integer yearsExperience) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        List<Employee> employeesList = session
                .createQuery(criteriaQuery
                        .select(employeeRoot)
                        .where(criteriaBuilder.greaterThan(employeeRoot.get("yearsExperience"), yearsExperience)))
                .getResultList();

        return employeesList;
    }

}
