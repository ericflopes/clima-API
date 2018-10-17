package com.example.fromzerotohero.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.fromzerotohero.entidades.ClimaEntidade;

@Repository
public class ClimaPersistencia {

	@PersistenceContext
	EntityManager em;

	public void salvarClimas(ClimaEntidade clima) {
		em.persist(clima);
	}

	public boolean isNoBanco(String cidade, Date date) {
		StringBuilder str = new StringBuilder();
		str.append("FROM ").append(ClimaEntidade.class.getName()).append(" c WHERE c.cidade = :cidade")
				.append(" AND c.data >= :data");
		List<ClimaEntidade> entidades = em.createQuery(str.toString(), ClimaEntidade.class).setParameter("data", date)
				.setParameter("cidade", cidade).getResultList();
		if (entidades.size() >= 5) {
			return true;
		} else {
			StringBuilder str2 = new StringBuilder();
			str2.append("FROM ").append(ClimaEntidade.class.getName()).append(" c WHERE c.cidade = :cidade");
			entidades = em.createQuery(str2.toString(), ClimaEntidade.class).setParameter("cidade", cidade).getResultList();
			for (ClimaEntidade c : entidades) {
				em.remove(c);
			}
			return false;
		}
	}

	public List<ClimaEntidade> buscarTodos(String cidade) {
		StringBuilder str = new StringBuilder();
		str.append("FROM ").append(ClimaEntidade.class.getName()).append(" c WHERE c.cidade = :cidade");

		return em.createQuery(str.toString(), ClimaEntidade.class).setParameter("cidade", cidade).getResultList();
	}

}
