package ir.maktab.repository.impl;

import ir.maktab.data.entity.loans.EducationLoan;
import ir.maktab.data.entity.loans.HousingLoan;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.loans.TuitionLoan;
import ir.maktab.data.enums.CityType;
import ir.maktab.data.enums.DegreeGroup;
import ir.maktab.repository.EntityManagerFactoryProducer;
import ir.maktab.repository.IRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LoanRepo implements IRepository<Loan> {
    private static final LoanRepo loanRepo = new LoanRepo();

    private LoanRepo() {
    }

    public static LoanRepo getInstance() {
        return loanRepo;
    }

    public void save(Loan loan) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Loan loan) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(loan);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Loan loan) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Loan deleteLoan = em.find(Loan.class, loan.getId());
        em.remove(deleteLoan);
        em.getTransaction().commit();
        em.close();
    }

    public List<Loan> getAll() {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        List<Loan> loanList = em.createNamedQuery("getAllLoan").getResultList();
        em.getTransaction().commit();
        em.close();
        return loanList;
    }

    public EducationLoan getEducationLoanByDegree(DegreeGroup degree) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM EducationLoan e WHERE e.degreeGroup=:degree");
        EducationLoan loan = (EducationLoan) query.setParameter("degree", degree).getSingleResult();
        em.getTransaction().commit();
        em.close();
        return loan;
    }

    public TuitionLoan getTuitionLoanByDegree(DegreeGroup degree) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM TuitionLoan t WHERE t.degreeGroup=:degree");
        TuitionLoan loan = (TuitionLoan) query.setParameter("degree", degree).getSingleResult();
        em.getTransaction().commit();
        em.close();
        return loan;
    }

    public HousingLoan getHouseLoanByCity(CityType city) {
        EntityManager em = EntityManagerFactoryProducer.emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("FROM HousingLoan h WHERE h.cityType=:city");
        HousingLoan loan = (HousingLoan) query.setParameter("city", city).getSingleResult();
        em.getTransaction().commit();
        em.close();
        return loan;
    }
}
