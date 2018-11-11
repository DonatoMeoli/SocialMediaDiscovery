package edu.uniba.di.lacam.kdde.donato.meoli.preprocessing.database.neo4j.repository.relationship;

import edu.uniba.di.lacam.kdde.donato.meoli.preprocessing.database.neo4j.domain.relationship.Link;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.Collection;

@NoRepositoryBean
public interface ILinkRepository<T extends Link> extends Neo4jRepository<T, Long> {

    @Query("MATCH ()-[r]->() RETURN min(r.utc)")
    LocalDateTime getFirstUtc();

    @Query("MATCH ()-[r]->() RETURN max(r.utc)")
    LocalDateTime getLastUtc();

    Collection<T> findByUtcGreaterThanEqualAndUtcLessThan(LocalDateTime startUtc, LocalDateTime endUtc);
}
