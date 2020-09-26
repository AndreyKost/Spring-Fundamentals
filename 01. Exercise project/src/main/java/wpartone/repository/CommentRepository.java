package wpartone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wpartone.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {
}
